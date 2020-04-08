package com.example.utils;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.example.model.vo.UserVO;
import com.example.mvc.exception.MyException;
import com.example.model.dto.UserDTO;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东 (qq:397827222)
 * @since 1.0
 * @date 2019/05/08 10:04
 **/

public class AesEncryptUtils {

    private static final String KEY = "xxxxxxxxxxxxxxxx";

    private static Logger logger = LoggerFactory.getLogger(AesEncryptUtils.class);

    /**
     * 参数分别代表 算法名称/加密模式/数据填充方式
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     * @param content 加密的字符串
     * @return
     */
    public static String encrypt(String content) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
            byte[] b = cipher.doFinal(content.getBytes(Constants.Public.CHARSET));
            // 采用base64算法进行转码,避免出现中文乱码
            return Base64.encodeBase64String(b);
        } catch (Exception e) {
            logger.error("加密错误:{0}", e);
            throw new MyException(Constants.ConstantsEnum.SERVER_EXCEPTION_CODE);
        }
    }

    /**
     * 解密
     * @param encryptStr 解密的字符串
     * @throws Exception
     */
    public static String decrypt(String encryptStr) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
            // 采用base64算法进行转码,避免出现中文乱码
            byte[] encryptBytes = Base64.decodeBase64(encryptStr);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes);
        } catch (Exception e) {
            logger.error("解密错误:{0}", e);
            throw new MyException(Constants.ConstantsEnum.SERVER_EXCEPTION_CODE);
        }
    }


    public static void main(String[] args) throws Exception {
        UserDTO user =  new UserDTO();
        UserVO vo = new UserVO();

        Map<String, Object> map = new HashMap<>(2);
        map.put("requestData", user);

        String s = encrypt(JSON.toJSONString(user));
        System.out.println(s);
        System.out.println("T9XS6B bLIaCoXHMhzX7gxsQ1NUJHvZUnzwrefSLgS0=");
        System.out.println(decrypt(s));

        System.out.println("--------------------------");

        System.out.println(encrypt(JSON.toJSONString(user)));
        System.out.println(JSON.toJSONString(map));

    }
}
