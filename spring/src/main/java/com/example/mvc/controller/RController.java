package com.example.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/12/29 17:30
 **/
@RestController
@RequestMapping("mapstruct")
public class RController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("t")
    public String test() {
        String url = "http://restapi.amap.com/v3/geocode/regeo?output=JSON&location="
                + "116.741741" + "," + "39.988688" + "&key=" + "1ade8c3663433fd83607e226e2f70dbc" + "&radius=1000&extensions=base";
//        url = "https://api.mch.weixin.qq.com/v3/payscore/serviceorder/2412121212121212/cancel";
        return restTemplate.getForObject(url, String.class);
    }
}
