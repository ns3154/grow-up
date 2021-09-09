package com.example.wallpaper.netbian;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/07/10 00:07
 **/
public class NetBianHttpClient {
    /**
     * http://pic.netbian.com/tupian/25153.html
     */
    private final static String PRE_URL = "http://pic.netbian.com/tupian/";

    private static volatile RestTemplate restTemplate;

    private static SimpleClientHttpRequestFactory reqfac = new SimpleClientHttpRequestFactory();

    private static AtomicInteger integer = new AtomicInteger(0);

    public static RestTemplate restTemplate () {
        if (null == restTemplate) {
            synchronized (NetBianHttpClient.class) {
                if (null == restTemplate) {
                    restTemplate = new RestTemplate();
                    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
                    factory.setConnectTimeout(2000);
                    factory.setReadTimeout(10000);
                    BasicCookieStore cookieStore = new BasicCookieStore();
                    BasicClientCookie[] cookies =  {new BasicClientCookie("__yjs_duid",
                            "1_11338b8881b5c8d5131098a936b3f4f91622823144171"),
                                                    new BasicClientCookie("Hm_lvt_526caf4e20c21f06a4e9209712d6a20e",
                                                            "1626509489,1626532436,1626538779,1626704350"),
                                                    new BasicClientCookie("PHPSESSID", "5icv9a47n5r6s05tl8clnrjvg4"),
                                                    new BasicClientCookie("zkhanmlusername", "MoonLight"),
                                                    new BasicClientCookie("zkhanmluserid", "154993"),
                                                    new BasicClientCookie("zkhanmlgroupid", "3"),
                                                    new BasicClientCookie("zkhanmlrnd", "0E59JUcrOTgoZlwLkyPD"),
                                                    new BasicClientCookie("zkhanmlauth",
                                                            "a9e87c9e8f2ce9d9d4a241db1edaacf8"),
                                                    new BasicClientCookie("zkhanecookieclassrecord", "%2C53%2C"),
                                                    new BasicClientCookie("Hm_lpvt_526caf4e20c21f06a4e9209712d6a20e", "1625853326")};
                    cookieStore.addCookies(cookies);
                    CloseableHttpClient httpClient =
                            HttpClientBuilder.create()
                                    .setRedirectStrategy(new LaxRedirectStrategy())
                                    .setDefaultCookieStore(cookieStore)
//                                    .setProxy(host())
                                    .build();
                    factory.setHttpClient(httpClient);
                    restTemplate.setRequestFactory(factory);
                    restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("gbk")));
                    restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("gbk")));
                }
            }
        }
        return restTemplate;
    }

    public static RestTemplate rest() {
        RestTemplate rest = new RestTemplate();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(200000);
        factory.setReadTimeout(100000);
        CloseableHttpClient httpClient =
                HttpClientBuilder.create()
                        .setRedirectStrategy(new LaxRedirectStrategy())
//                        .setProxy(host())
                        .build();
        factory.setHttpClient(httpClient);
        rest.setRequestFactory(factory);
        rest.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("gbk")));
        return rest;
    }

    public static HttpHost host() {
        return new HttpHost("112.65.166.69", 3128);
//        return null;
    }

    public static HttpHeaders getHttpHeaders (String id) {
        HttpHeaders headers = new HttpHeaders();
        String cookie = "__yjs_duid=1_11338b8881b5c8d5131098a936b3f4f91622823144171; " +
                "yjs_js_security_passport=bda95479c2a7aa709913b492f2f1efb3d3366557_1626704348_js; " +
                "Hm_lvt_526caf4e20c21f06a4e9209712d6a20e=1626509489,1626532436,1626538779,1626704350; " +
                "PHPSESSID=snncfc4u5bhh5qejlvp8bhc7v0; zkhanmlusername=MoonLight; zkhanmluserid=154993; " +
                "zkhanmlgroupid=3;" +
                " zkhanmlrnd=Be0gEzXkWhj2vjTdZvdP; " +
                "zkhanmlauth=c25052f40becad5cf1641dd57ee18762;" +
                " zkhanecookieclassrecord=%2C53%2C; " +
                "Hm_lpvt_526caf4e20c21f06a4e9209712d6a20e=1626704397" +
                "zkhandownid" + id + "=1;" +
                "HMVT=428b9db8f1c962a748953bc0b3a8c56c|1626704335|;" +
                "HMACCOUNT_BFESS=2535BF93037DC5A0;" +
                "PHPSESSID=snncfc4u5bhh5qejlvp8bhc7v0;" +
                "";

        headers.put("accept",
                Arrays.asList("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"));
        headers.put("accept-Encoding", Arrays.asList("gzip, deflate, br"));
        headers.put("accept-language", Arrays.asList("zh-CN,zh;q=0.9"));
        headers.put("Connection", Arrays.asList("keep-alive"));

        List<String> cookies = new ArrayList<>();
        cookies.add("zkhanmlauth");

        headers.put("cookie", Arrays.asList(cookie));
//        headers.put("Host", Arrays.asList("pic.netbian.com"));
        if (null == id) {
            headers.put("referer", Arrays.asList("https://pic.netbian.com/"));
        } else {
            headers.put("referer", Arrays.asList(PRE_URL + id + ".html"));
        }
        headers.put("sec-fetch-site",  Arrays.asList("same-origin"));
        headers.put("Upgrade-Insecure-Requests", Arrays.asList("1"));
        headers.put("User-Agent", Arrays.asList("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36"));
        headers.put("authority", Arrays.asList("pic.netbian.com"));



        return headers;
    }


}
