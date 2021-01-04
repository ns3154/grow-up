package com.example.utils;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/12/29 17:04
 **/
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    static final Map<String, String> CAT_URL = new HashMap<>();

    static final Map<String, Map<Pattern, String>> REGEX_URL = new HashMap<>();

    static {
        CAT_URL.put("api.weixin.qq.com/cgi-bin/token", "");
        CAT_URL.put("aip.baidubce.com/oauth/2.0/token", "");
        CAT_URL.put("wechat.baojia.com/zhanggui-java/auth/gongzhonghao/renmin", "");
        CAT_URL.put("wechat.baojia.com/zhanggui-java/auth/gongzhonghao/mifeng", "");
        CAT_URL.put("api.253.com/open/flashsdk/mobile-query-m", "");
        CAT_URL.put("api.253.com/open/flashsdk/mobile-query-u", "");
        CAT_URL.put("api.253.com/open/flashsdk/mobile-query-t", "");
        CAT_URL.put("restapi.amap.com/v3/geocode/regeo", "");
        CAT_URL.put("api.weixin.qq.com/sns/jscode2session", "");
        CAT_URL.put("api.253.com/open/flashsdk/mobile-query", "");
        CAT_URL.put("api.weixin.qq.com/wxa/getwxacodeunlimit", "");
        CAT_URL.put("http://ek.caikaixin.cn/sendFpInfo", "caikaixin_sendFpInfo");
        CAT_URL.put("http://ek.caikaixin.cn/token", "caikaixin_token");
        CAT_URL.put("https://api.mch.weixin.qq.com/v3/payscore/permissions/openid/.*", "");
        CAT_URL.put("api.mch.weixin.qq.com/v3/payscore/permissions", "");
        CAT_URL.put("api.mch.weixin.qq.com/v3/payscore/serviceorder", "");
        CAT_URL.put("api.mch.weixin.qq.com/v3/certificates", "");
        CAT_URL.put("api.weixin.qq.com/sns/oauth2/access_token", "");
        CAT_URL.put("api.mch.weixin.qq.com/v3/payscore/payafter-orders", "");


        Map<Pattern, String> restUrls = new HashMap<>();
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/payscore/payafter-orders/.*/complete"), "");
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/payscore/serviceorder/.*/cancel"), "");
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/payscore/payafter-orders/.*/sync"), "");
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/payscore/serviceorder/.*/sync"), "");
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/payscore/serviceorder/.*/modify"), "");
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/marketing/favor/users/.*/coupons"), "");
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/payscore/permissions/openid/.*"), "");

        REGEX_URL.put("api.mch.weixin.qq.com", restUrls);
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String catTransactionName = getCatTransactionName(request.getURI());


        if (null == catTransactionName) {
            return execution.execute(request, body);
        }

        String catType = "HTTP_REMOTE_CALL";
        return execution.execute(request, body);
    }

    private String getCatTransactionName(URI uri) {
        String remoteUrl = uri.getHost() + uri.getPath();
        String name;
        if (null != (name = CAT_URL.get(remoteUrl))) {
            return name;
        }

        Map<Pattern, String> regexs = REGEX_URL.get(uri.getHost());

        if (null != regexs) {
            Set<Map.Entry<Pattern, String>> entries = regexs.entrySet();
            for (Map.Entry<Pattern, String> entry :entries) {
                if (entry.getKey().matcher(remoteUrl).matches()) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }


    public static void main(String[] args) {
        System.out.println(ss());
    }

    public static int ss() throws ArithmeticException{
        int i = 0;
        try {
             i = 11 / 0;
        } catch (ArithmeticException e) {
            throw e;
        }finally {

        }
        return i;
    }



}
