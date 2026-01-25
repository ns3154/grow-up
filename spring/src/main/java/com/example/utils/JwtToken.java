package com.example.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.model.dto.UserToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

/**
 * <pre>
 *     jwtToken 生成,解密
 * </pre>
 * @author 杨帮东
 * @date 2018-01-18 11:56
 * @version 1.0
 **/
@Component
public class JwtToken {

    private static final int TOKEN_SPLIT_LEN = 2;

    private Algorithm algorithm;

    @Value("${jwt.token.secret}")
    private String tokenSecret;


    @PostConstruct
    private void init() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(tokenSecret);
    }

    /**
     * 生成token
     * @author 杨帮东
     * @param key key值 建议使用用户id保证唯一性
     * @param value 封装类后的json
     * @return java.lang.String
     * @version 1.0
     * @date 2018/4/10 9:20
     */
    public String createToken(String key, String value) {
        String token;
        token = JWT.create().withClaim(key, value).sign(algorithm);
        return token + "&" + key;
    }

    /**
     * 解密
     * @author 杨帮东
     * @param token
     * @param key
     * @return java.lang.String
     * @throws
     * @version 1.0
     * @date 2018/4/10 9:22
     */
    public String decryptToken(String token, String key)
    {
        String str;
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            str = jwt.getClaim(key).asString();
        } catch (Exception exception) {
            str = null;
        }
        return str;
    }

    /**
     * @author 杨帮东
     * @param token token值
     * @return com.hdxy.beans.UserToken
     * @version 1.0
     * @date 2018/4/10 9:20
     */
    public UserToken conversion(String token)
    {
        String[] tokens = StringUtils.split(token, "&");

        if (tokens.length != TOKEN_SPLIT_LEN) {
            return null;
        }

        String tokenJson = decryptToken(tokens[0], tokens[1]);

        if (StringUtils.isEmpty(tokenJson)) {
            return null;
        }
        return JSON.parseObject(tokenJson, UserToken.class);
    }

    public String getUserId(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new NullPointerException("token is miss");
        }
        String[] tokens = StringUtils.split(token, "&");

        if (tokens.length != TOKEN_SPLIT_LEN) {
            return null;
        }

        return tokens[1];
    }


}
