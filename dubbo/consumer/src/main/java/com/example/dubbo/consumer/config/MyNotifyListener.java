package com.example.dubbo.consumer.config;

import com.example.common.api.DubboTestServiceApi;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.registry.NotifyListener;
import org.apache.dubbo.registry.dubbo.DubboRegistryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/13 19:11
 **/
@Component
public class MyNotifyListener implements CommandLineRunner, NotifyListener {

    private static final Logger logger = LoggerFactory.getLogger(MyNotifyListener.class);

    private final ThreadPoolExecutor singleThreadExecutor =
            new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), new BasicThreadFactory.Builder().namingPattern(Joiner.on("-").join(
            "dubbo-notify-listener", "%s")).build());

    @Override
    public void notify(List<URL> urls) {
        if (urls.isEmpty()) {
            return;
        }
        singleThreadExecutor.execute(() -> DoubleExpand.notifiy(urls));
    }


    @Override
    public void run(String... args) {
        String host = System.getProperty("dubbo.protocol.host");
        if (StringUtils.isBlank(host)) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                host = inetAddress.getHostAddress();
            } catch (UnknownHostException e) {
                logger.error("*** 获取 ip地址错误:", e);
            }
            if (StringUtils.isBlank(host)) {
                host = "127.0.0.1";
            }
        }
        logger.error("**** proxyProtocol host:{} ****", host);
        DubboRegistryFactory.getRegistries().iterator().next().subscribe(createUrl(host), this);
    }

    private URL createUrl(String host) {
        return new URL("proxyProtocol", host, 0, "",
                "interface", DubboTestServiceApi.class.getName(), "group", "*", "version", "*",
                "category", "providers",
                "check", "false");
    }

}
