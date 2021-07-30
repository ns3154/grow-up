package com.example.wallpaper.lanren;

import com.alibaba.fastjson.JSON;
import com.example.wallpaper.netbian.NetBianHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/07/28 21:27
 **/
public class Main {

    public static void main (String[] args) {
        RestTemplate rest = new RestTemplate();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        CloseableHttpClient httpClient =
                HttpClientBuilder.create()
                        .setRedirectStrategy(new LaxRedirectStrategy())
                        .build();
        factory.setHttpClient(httpClient);
        rest.setRequestFactory(factory);
        String url = "https://www.lrts.me/ajax/playlist/2/5622/24";
        ResponseEntity<String> entity = rest.exchange(url, HttpMethod.GET,
                new HttpEntity<String>(httpHeaders()), String.class);

        String body = entity.getBody();
        Document root = Jsoup.parse(body);
        Elements li = root.getElementsByTag("li");
        List<String> list = new ArrayList<>();
        for (Element element : li) {
            String value = element.children().get(0).children().get(5).attributes().getIgnoreCase("value");
            list.add(value);
        }

        url = "https://www.lrts.me/ajax/path/4/5622/" + list.get(0);
        ResponseEntity<String> ss = rest.exchange(url, HttpMethod.GET,
                new HttpEntity<String>(httpHeaders()), String.class);
        Object data = JSON.parseObject(ss.getBody(), Map.class).get("data");

        ResponseEntity<byte[]> sss = rest.exchange(data.toString(), HttpMethod.GET,
                new HttpEntity<byte[]>(httpHeaders()), byte[].class);
        System.out.println(sss);

    }

    public static HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String cookie = "sid=67CACE922CD658E656A60B05403B9028F2829EAF3F6D10C2B830CDE44D19780C4AC9411DAA492AC5D9DD31DC4B8306C9531ED31B14F72F064CF86158CBA640A39B49F75B20BF34C76FE77E87053C3677; UM_distinctid=17add7eb9fda46-0f0a2878a0282e-34637600-13c680-17add7eb9fee3d; uid=16274783722308ecc97276603478bb3901fd1c36d8794; acw_tc=2760779816274850964035882e523e721c777526ba39d2934f347291989877; CNZZDATA1254668430=1562187957-1625401714-https%253A%252F%252Fwww.baidu.com%252F%7C1627480126; Hm_lvt_ada61571fd48bb3f905f5fd1d6ef0ec4=1627196990,1627213434,1627478373,1627485098; JSESSIONID=FDCD74DDF4A8122069F2157121FEE721; Hm_lpvt_ada61571fd48bb3f905f5fd1d6ef0ec4=1627485666";
        headers.put("accept",
                Arrays.asList("*/*"));
        headers.put("Connection", Arrays.asList("keep-alive"));
        headers.put("referer", Arrays.asList("https://www.lrts.me/playlis"));
        headers.put("Accept-Encoding", Arrays.asList("UTF-8"));
        headers.put("Accept-Language", Arrays.asList("zh-Hans-CN;q=1"));
        headers.put("User-Agent", Arrays.asList("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36"));
        headers.put("host", Arrays.asList("www.lrts.me"));
        headers.put("cookie", Arrays.asList(cookie));
        return headers;
    }


}
