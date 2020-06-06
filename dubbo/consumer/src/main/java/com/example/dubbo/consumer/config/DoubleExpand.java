package com.example.dubbo.consumer.config;

import com.example.dubbo.consumer.model.DubboNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.AbstractConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *     dubbo 不同分组动态选择工具
 * </pre>
 *
 * @author 杨帮东
 * @date 2020/01/10 11:32
 * @since 1.0
 **/
public class DoubleExpand {

    private static Logger logger = LoggerFactory.getLogger(DoubleExpand.class);

    private DoubleExpand() {
        // nothing
    }

    private static final Map<String, GenericService> GENERIC_SERVICE = new ConcurrentHashMap<>();

    private static final Map<String, Object> DUBBO_INTERFACE = new ConcurrentHashMap<>();

    /**
     *  key:com.meboth.gateway.container.api.TestService
     *      key: 172.16.1.57:28082:com.meboth.gateway.container.api.TestService
     */
    private static final Map<String, Map<String, ReferenceConfig<?>>> DUBBO_URL_INTERFACE = new ConcurrentHashMap<>();

    /**
     *  key:  com.meboth.gateway.container.api.TestService
     */
    private static final Map<String, List<URL>> DUBBO_PROVIDER_URL = new ConcurrentHashMap<>();

    private static final Object LOCK = new Object();


    public static <T> T getInterface(String group, Class<T> clz) {
        return getInterface(group, "3.0.0", clz);
    }

    /**
     * 文档:http://dubbo.apache.org/zh-cn/docs/user/demos/reference-config-cache.html
     * 基于cache 实现dubbo 动态分组功能
     *
     * @param group 接口所在分组
     * @param clz   class
     * @return T
     * @author 杨帮东
     * @date 2020/1/10 13:04
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInterface(String group, String version, Class<T> clz) {
        if (StringUtils.isBlank(group) || StringUtils.isBlank(version) || null == clz) {
            throw new IllegalArgumentException("该方法所有参数均为必要参数");
        }
        String key = group + "." + clz.getName();
        Object object = DUBBO_INTERFACE.get(key);
        if (null == object) {
            ReferenceConfig<T> reference = new ReferenceConfig<>();
            reference.setGroup(group);
            reference.setInterface(clz);
            reference.setVersion(version);
            reference.get();
            ReferenceConfigCache cache = ReferenceConfigCache.getCache();
            T o = cache.get(reference);
            DUBBO_INTERFACE.putIfAbsent(key, o);
            return o;
        }
        return (T) object;
    }


    public static final ReferenceConfigCache.KeyGenerator KEY_GENERATOR = AbstractConfig::getId;

    /**
     * 泛化通用类  获取GenericService 泛化
     * 文档:http://dubbo.apache.org/zh-cn/docs/user/demos/generic-reference.html
     * 可通过此方法 动态使用不同group的相同接口
     *
     * @param group 组名
     * @param clz   被泛化的dubbo接口
     * @return org.apache.dubbo.rpc.service.GenericService
     * @throws
     * @author 杨帮东
     * @date 2020/1/10 10:59
     * @since 1.0
     */
    public static GenericService genericService(String group, Class<?> clz) {
        String key = group + "." + clz.getName();
        GenericService cs = GENERIC_SERVICE.get(key);
        if (null == cs) {
            ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
            reference.setGroup(group);
            reference.setInterface(clz);
            reference.setGeneric(true);
            ReferenceConfigCache cache = ReferenceConfigCache.getCache();
            cs = cache.get(reference);
            GENERIC_SERVICE.putIfAbsent(key, cs);
        }
        return cs;
    }



    /**
     * 根据接口,获取所有dubbo 提供者节点
     * @author 杨帮东
     * @param interFace 例如:com.meboth.gateway.container.api.TestService
     * @since 1.0
     * @date 2020/3/16 14:09
     * @return com.meboth.gateway.common.model.dto.DubboNode
     */
    public static DubboNode getDubboNodesByInterFaceName(String interFace) {

        DubboNode dubboNode = new DubboNode();
        List<URL> urlList = DUBBO_PROVIDER_URL.get(interFace);
        if (CollectionUtils.isEmpty(urlList)) {
            return null;
        }
        dubboNode.setInterFace(interFace);
        String[] methods = null;
        List<String> remoteAddressList = new ArrayList<>();
        for (int i = 0,size = urlList.size(); i < size; i++) {
            URL url = urlList.get(i);
            if(null == methods) {
                methods = url.getParameter("methods").split(",");
            }
            remoteAddressList.add(url.getAddress());
        }

        dubboNode.setRemoteHosts(remoteAddressList);
        dubboNode.setMethods(methods);
        return dubboNode;
    }


    /**
     * dubbo 指定地址调用
     * @author 杨帮东
     * @param remoteAddress dubbo远程地址 例如:172.16.1.57:28083
     * @param clz 要掉用的接口
     * @since 1.0
     * @date 2020/3/16 13:46
     * @return T
     * @throws
     */
    @SuppressWarnings("unchecked")
    public static <T> T getPtpInterFace(String remoteAddress, Class<T> clz) {
        String interfaceName = clz.getName();
        String mKey = remoteAddress + ":" + interfaceName;
        Map<String, ReferenceConfig<?>> map = DUBBO_URL_INTERFACE.get(interfaceName);
        Object o = null;
        URL url;
        ReferenceConfig<?> referenceConfig;
        if (CollectionUtils.isEmpty(map) && null != (url = getUrlByRemoteAddressAndInterFaceName(remoteAddress,
                interfaceName))) {
            referenceConfig = new ReferenceConfig<>();
            ReferenceConfigCache cache = getReferenceConfigCache(clz, mKey, url, referenceConfig);
            o = cache.get(referenceConfig);
            Map<String, ReferenceConfig<?>> m = new HashMap<>();
            m.put(mKey, referenceConfig);
            DUBBO_URL_INTERFACE.put(interfaceName, m);
        } else {
            if (null != (referenceConfig = map.get(mKey))) {
                ReferenceConfigCache cache = ReferenceConfigCache.getCache(ReferenceConfigCache.DEFAULT_NAME, KEY_GENERATOR);
                o = cache.get(referenceConfig);
            } else {
                if (null != (url = getUrlByRemoteAddressAndInterFaceName(remoteAddress, interfaceName))) {
                    ReferenceConfig<T> reference = new ReferenceConfig<>();
                    ReferenceConfigCache cache = getReferenceConfigCache(clz, mKey, url, reference);
                    o = cache.get(reference);
                    map.put(mKey, reference);
                }
            }
        }

        return (T) o;
    }

    private static ReferenceConfigCache getReferenceConfigCache(Class<?> clz, String mKey, URL url,
                                                                ReferenceConfig<?> reference) {
        reference.setInterface(clz);
        reference.setUrl(url.toFullString());
        reference.setId(mKey);
        reference.setParameters(url.getParameters());
        return ReferenceConfigCache.getCache(ReferenceConfigCache.DEFAULT_NAME, KEY_GENERATOR);
    }

    /**
     * 根据 远程地址和接口获取URL
     * @author 杨帮东
     * @param remoteAddress  dubbo远程地址 例如:172.16.1.57:28083
     * @param interFaceName 接口
     * @since 1.0
     * @date 2020/3/16 14:06
     * @return org.apache.dubbo.common.URL
     */
    private static URL getUrlByRemoteAddressAndInterFaceName(String remoteAddress, String interFaceName) {
        List<URL> urlList = DUBBO_PROVIDER_URL.get(interFaceName);
        if (CollectionUtils.isEmpty(urlList)) {
            return null;
        }

        return urlList.stream().filter(url -> remoteAddress.equals(url.getAddress())).findFirst().orElse(null);
    }

    public static void notifiy(List<URL> urls) {
        synchronized (LOCK) {
            if (CollectionUtils.isEmpty(urls)) {
                return;
            }

            List<URL> list = new ArrayList<>();
            String interfaceName = null;
            for (URL url : urls) {
                if (StringUtils.isBlank(interfaceName)) {
                    interfaceName = url.getServiceInterface();
                }
                if ("empty".equals(url.getProtocol())) {
                    Map<String, ReferenceConfig<?>> referenceConfigMap = DUBBO_URL_INTERFACE.get(interfaceName);
                    if (!CollectionUtils.isEmpty(referenceConfigMap)) {
                        referenceConfigMap.forEach((k, v) ->  v.destroy());
                        logger.error("** 释放实例:{}", interfaceName);
                    }
                    DUBBO_URL_INTERFACE.remove(interfaceName);
                    DUBBO_PROVIDER_URL.remove(url.getServiceInterface());
                } else {
                    list.add(url);
                }
            }

            if (!CollectionUtils.isEmpty(list)) {
                DUBBO_PROVIDER_URL.put(interfaceName, list);
            }
            clearByNotifiy();
        }
    }

    private static void clearByNotifiy() {
        for (Map.Entry<String, Map<String, ReferenceConfig<?>>> entry : DUBBO_URL_INTERFACE.entrySet()) {
            if (!DUBBO_PROVIDER_URL.containsKey(entry.getKey())) {
                Map<String, ReferenceConfig<?>> referenceConfigMap = DUBBO_URL_INTERFACE.get(entry.getKey());
                referenceConfigMap.forEach((k,v) -> {
                    v.destroy();
                    logger.error("** 释放实例:{} **", entry.getKey());
                });
                DUBBO_URL_INTERFACE.remove(entry.getKey());
                continue;
            }
            List<URL> urlList = DUBBO_PROVIDER_URL.get(entry.getKey());
            if (CollectionUtils.isEmpty(urlList)) {
                continue;
            }

            Set<String> mKey = new HashSet<>();
            for (int i = 0,size = urlList.size(); i < size; i++) {
                URL url = urlList.get(i);
                String key = url.getAddress() + ":" + url.getServiceInterface();
                mKey.add(key);
            }

            Map<String, ReferenceConfig<?>> value = entry.getValue();

            Iterator<Map.Entry<String, ReferenceConfig<?>>> iterator = value.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, ReferenceConfig<?>> next = iterator.next();
                if (!mKey.contains(next.getKey())) {
                    logger.error("** 释放实例:{} *** ", next.getKey());
                    ReferenceConfigCache cache = ReferenceConfigCache.getCache();
                    cache.destroy(next.getValue());
                    iterator.remove();
                }
            }
        }
    }

}
