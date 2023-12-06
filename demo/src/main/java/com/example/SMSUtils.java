package com.example;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 短信发送工具类
 */
public class SMSUtils {

    public static void main(String[] args) throws IOException {
        sendMessage("17610209982");
    }

    public static void sendMessage(String phone) throws IOException {
        String domain = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String appCode = "";
        String remoteUrl = domain + path;
        Map<String, Object> bodys = new HashMap<>();
        bodys.put("content", "code:" + generateCode());
        bodys.put("template_id", "CST_ptdie100");
        bodys.put("phone_number", phone);
        HttpRequest request = HttpUtil.createPost(remoteUrl).form(bodys)
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Authorization", "APPCODE " + appCode);
        HttpResponse execute = request.execute();

        if (execute.isOk()) {
            System.out.println("发送成功, 返回内容:" + execute.body());
        } else {
            System.out.println("发送失败, 返回内容:" + execute.body());
        }
    }

    public static Integer generateCode() {
        return ThreadLocalRandom.current().nextInt(0, 9999);
    }
}
