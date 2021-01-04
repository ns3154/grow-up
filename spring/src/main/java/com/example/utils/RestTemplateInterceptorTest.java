package com.example.utils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Pattern;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/12/31 15:36
 **/
public class RestTemplateInterceptorTest {

    RestTemplate restTemplate = new RestTemplate();
    String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit";

    @Before
    public void before() {
        restTemplate.getInterceptors().add(new RestTemplateInterceptor());
    }

    @Test
    public void urlParamAppend() {
        String u = url + "?id=323&nae=sdfs";
        String forObject = restTemplate.getForObject(u, String.class);
    }

    @Test
    public void urlParamHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("cat", "tttt");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("appId", "syAppId");
        params.add("accessToken", "syAccessToken");
        params.add("telecom", "syTelecom");
        params.add("timestamp", "syTimestamp");
        params.add("randoms", "syRandoms");
        params.add("version", "syVersion");
        params.add("device", "syDevice");
        params.add("sign", "sySign");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    @Test
    public void urlRest() {
        String u = url + "/ssdfs/3232";
        String forObject = restTemplate.getForObject(u, String.class);

    }

    @Test
    public void p() {
        String sss = "https://api.weixin.qq.com/wxa/getwxacodeunlimit/.*/.*/cannel";
        Pattern pattern = Pattern.compile(sss);
        String t = "https://api.weixin.qq.com/wxa/getwxacodeunlimit/5353/323/cannel/323";
        System.out.println(pattern.matcher(t).matches());
    }

}
