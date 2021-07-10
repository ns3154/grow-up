package com.example.wallpaper;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 原创:https://github.com/asche910/HttpProxy/blob/master/src/other/IPBean.java
 * @author Asche
 * @github: https://github.com/asche910
 * @date 2019年1月19日
 */
@Slf4j
public class ProxyUtils {

    /**
     * 设置全局代理
     * @param ipBean
     */
    public static void setGlobalProxy(IPBean ipBean){
        System.setProperty("proxyPort", String.valueOf(ipBean.getPort()));
        System.setProperty("proxyHost", ipBean.getIp());
        System.setProperty("proxySet", "true");
    }

    public static void go() {
        IPSpider spider = new IPSpider();
        List<IPBean> list = spider.crawlHttp(1);

        System.out.println("爬取数量：" + list.size());

        for (IPBean ipBean : list) {
            boolean valid = IPUtils.isValid(ipBean);
            if (valid){
                log.info(JSON.toJSONString(ipBean));
                setGlobalProxy(ipBean);
                break;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Start: ");

        IPSpider spider = new IPSpider();
        List<IPBean> list = spider.crawlHttp(3);

        System.out.println("爬取数量：" + list.size());

        for (IPBean ipBean : list) {
            log.info(JSON.toJSONString(ipBean));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean valid = IPUtils.isValid(ipBean);
                    if (valid){
                        IPList.add(ipBean);
                    }
                    IPList.increase();
                }
            }).start();
        }

        while (true){
            // 判断所有副线程是否完成
            if (IPList.getCount() == list.size()){
                System.out.println("有效数量：" + IPList.getSize());
                break;
            }
        }
    }

}
