package com.example.wallpaper.jd;

import com.example.wallpaper.netbian.NetBianHttpClient;
import java.util.HashMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *
 * </p>
 *
 * @author Ns
 * @version 1.0
 * @date 2023/01/12 18:44
 **/
public class JdMain {

    static final String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E6%89%8B%E6%9C%BA&pvid=8858151673f941e9b1a4d2c7214b2b52";

    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();
        String object = rest.getForObject(url, String.class);
        System.out.println(object);
    }
}
