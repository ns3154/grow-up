package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.oracle.net.Sdp;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *
 * </p>
 *
 * @author Ns
 * @version 1.0
 * @date 2022/08/30 10:05
 **/
public class RestTemplateMain {

    //无需修改,用于格式化鉴权头域,给"X-WSSE"参数赋值
    private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";
    //无需修改,用于格式化鉴权头域,给"Authorization"参数赋值
    private static final String AUTH_HEADER_VALUE = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";

    public static void main(String[] args) {
        JSONArray arr = new JSONArray(Arrays.asList("sdsdf"));
        System.out.println(arr.toString());

        RestTemplate rest = new RestTemplate();

        String url = "https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1"; //APP接入地址(在控制台"应用管理"页面获取)+接口访问URI
        String appKey = "N0eRq20wPNGGXOZ1STr4k1clq73Z"; //APP_Key
        String appSecret = "ii0qsHGk0puTAzEbt9OTY5en4YOB"; //APP_Secret
        String sender = "1069368924410003205"; //国内短信签名通道号或国际/港澳台短信通
        String templateId = "21ab03fbafaf46e9b146cfe1e97f7a1d"; //模板ID
        String signature = "华为云短信测试"; //签名名称
        String receiver = "+8617610209982"; //短信接收人号码
//        String templateParas = "[\"369751\"]";
        String templateParas = "${369751}";
        String wsseHeader = buildWsseHeader(appKey, appSecret);
        String body = buildRequestBody(sender, receiver, templateId, templateParas, null, signature);
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", Arrays.asList("application/x-www-form-urlencoded"));
        headers.put("Authorization", Arrays.asList(AUTH_HEADER_VALUE));
        headers.put("X-WSSE", Arrays.asList(wsseHeader));
        HttpEntity<String> ectity = new HttpEntity<>(body, headers);
        ResponseEntity<HuaWeiResult> exchange = rest.exchange(url, HttpMethod.POST, ectity, HuaWeiResult.class);
        System.out.println(exchange);
    }

    static String buildWsseHeader(String appKey, String appSecret) {
        if (null == appKey || null == appSecret || appKey.isEmpty() || appSecret.isEmpty()) {
            System.out.println("buildWsseHeader(): appKey or appSecret is null.");
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date()); //Created
        String nonce = UUID.randomUUID().toString().replace("-", ""); //Nonce

        MessageDigest md;
        byte[] passwordDigest = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update((nonce + time + appSecret).getBytes());
            passwordDigest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //如果JDK版本是1.8,请加载原生Base64类,并使用如下代码
        String passwordDigestBase64Str = Base64.getEncoder().encodeToString(passwordDigest); //PasswordDigest
        //如果JDK版本低于1.8,请加载三方库提供Base64类,并使用如下代码
        //String passwordDigestBase64Str = Base64.encodeBase64String(passwordDigest); //PasswordDigest
        //若passwordDigestBase64Str中包含换行符,请执行如下代码进行修正
        //passwordDigestBase64Str = passwordDigestBase64Str.replaceAll("[\\s*\t\n\r]", "");
        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
    }


    public static String buildRequestBody(String sender, String receiver, String templateId, String templateParas,
        String statusCallBack, String signature) {
        if (null == sender || null == receiver || null == templateId || sender.isEmpty() || receiver.isEmpty()
            || templateId.isEmpty()) {
            System.out.println("buildRequestBody(): sender, receiver or templateId is null.");
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();

        map.put("from", sender);
        map.put("to", receiver);
        map.put("templateId", templateId);
        if (null != templateParas && !templateParas.isEmpty()) {
            map.put("templateParas", templateParas);
        }
        if (null != statusCallBack && !statusCallBack.isEmpty()) {
            map.put("statusCallback", statusCallBack);
        }
        if (null != signature && !signature.isEmpty()) {
            map.put("signature", signature);
        }

        StringBuilder sb = new StringBuilder();
        String temp = "";

        for (String s : map.keySet()) {
            try {
                temp = URLEncoder.encode(map.get(s), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            sb.append(s).append("=").append(temp).append("&");
        }

        return sb.deleteCharAt(sb.length()-1).toString();
    }
}
