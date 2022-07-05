package com.example;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.HttpClientConfig;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.Test;


/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/06/03 22:54
 **/
public class AliyunSDKTest {

    static final DefaultAcsClient CLIENT;

    // 900s后过期
    private static final Long DURATION_SECONDS = 900L;

    /**
     * 缓存时间 DURATION_SECONDS - 100s
     */
    private static final Long CACHE_TIME = DURATION_SECONDS * 1000L - 100 * 1000L;


    /**
     * 缓存
     * 存在并发问题,此处忽略,无非是在缓存失效后,多个线程同时请求新的token,为了这个没必要加锁处理
     */
    final Cache cache = new Cache();

    static {
        DefaultProfile profile = DefaultProfile.getProfile("cn-xxxxx", "xxxxx", "xxxx");
        HttpClientConfig clientConfig = HttpClientConfig.getDefault();
        clientConfig.setMaxRequestsPerHost(6);
        clientConfig.setConnectionTimeoutMillis(3000L);
        clientConfig.setReadTimeoutMillis(3000L);
        clientConfig.setMaxIdleConnections(20);
        profile.setHttpClientConfig(clientConfig);
        CLIENT = new DefaultAcsClient(profile);
    }


    @Test
    public void test() {
        for (int i = 0;i < 10;i++) {
            System.out.println(JSON.toJSONString(response()));
        }



    }

    private AssumeRoleResponse response() {

        if (!cache.isExpired()) {
            return cache.getResponse();
        }

        long expirtTime = System.currentTimeMillis() + CACHE_TIME;
        cache.rest(expirtTime, reFresh());
        return cache.getResponse();
    }

    private AssumeRoleResponse reFresh() {
        AssumeRoleRequest request = new AssumeRoleRequest();
        request.setVersion("2015-04-01");
        request.setMethod(MethodType.POST);
        request.setProtocol(ProtocolType.HTTPS);
        request.setRoleArn("acs:ram::xxx:role/xxxx");
        request.setRoleSessionName("alice-001");
        request.setPolicy(null);
        request.setDurationSeconds(DURATION_SECONDS);

        try {
            System.out.println(222222222);
            return CLIENT.getAcsResponse(request);
        } catch (ClientException e) {
            // TODO 打日志 别 system
            throw new RuntimeException(e);
        }
    }

    static class Cache {

        public Cache () {
            this.expirtTime = -1;
        }

        public Cache rest (long expirtTime, AssumeRoleResponse response) {
            this.expirtTime = expirtTime;
            this.response = response;
            return this;
        }

        private long expirtTime;

        private AssumeRoleResponse response;


        public AssumeRoleResponse getResponse() {
            return response;
        }

        public boolean isExpired() {
            return expirtTime < 0L || System.currentTimeMillis() > expirtTime;
        }
    }


}
