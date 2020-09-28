package com.example.wx;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 微信拓展类
 * 建议不要掺杂业务,只是进行关于微信的操作
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/09/28 15:56
 **/
@Component
public class WxSupport {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * 登录凭证校验 URL
     */
    public static final String AUTH_CODE_2_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}" +
            "&secret" +
            "={SECRET}&js_code={CODE}&grant_type=authorization_code";

    private static final String REDIS_WX_AUTH_SESSION_KEY = "wx:auth:session:";

    @Resource
    RestTemplate restTemplate;

    public WxAuthCode2SessionVO wxAuthCodeToSession(String appId, String secret, String code) {
        Map<String, String> qMap = new HashMap<>();
        qMap.put("APPID", appId);
        qMap.put("SECRET", secret);
        qMap.put("CODE", code);
        ResponseEntity<WxAuthCode2SessionVO> wxResponse;
        try {
            wxResponse = restTemplate.getForEntity(WxSupport.AUTH_CODE_2_SESSION_URL, WxAuthCode2SessionVO.class, qMap);
        } catch (RestClientException restClientException) {
            logger.error("wxAuthCode2Session error,param:code:{},appFrom{}", code, restClientException);
            return null;
        }

        if (wxResponse.getStatusCode() != HttpStatus.OK) {
            logger.error("wxAuthCode2Session HTTP ERROR:{}", JSON.toJSONString(wxResponse));
            return null;
        }

        return wxResponse.getBody();
    }


    public String authCodeToSeesionRedisKey(Integer appFrom) {
        return REDIS_WX_AUTH_SESSION_KEY + appFrom + ":";
    }

    /**
     * @author 杨帮东
     * @param sessionKey 会话密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param ivStr 加密算法的初始向量
     * @since 1.0
     * @date 2020/9/24 21:33
     * @return java.lang.String
     * @see
     * <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html">
     *     code2Session</a>
     * @see
     * <a href="https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/signature.html>
     *     加密数据解密算法</a>
     * @throws RuntimeException
     */
    public String decrypt(String sessionKey, String encryptedData, String ivStr) {
        try {
            AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
            params.init(new IvParameterSpec(Base64.decodeBase64(ivStr)));
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] bytes = base64Decoder.decodeBuffer(sessionKey);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(bytes, "AES"), params);
            return new String(decode(cipher.doFinal(base64Decoder.decodeBuffer(encryptedData))), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("AES 解密失败", e);
        }
    }

    private byte[] decode(byte[] decrypted) {
        int pad = decrypted[decrypted.length - 1];
        if (pad < 1 || pad > 32) {
            pad = 0;
        }
        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }




}
