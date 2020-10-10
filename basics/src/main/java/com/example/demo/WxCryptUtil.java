package com.example.demo;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.util.Arrays;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/09/24 21:28
 **/
public class WxCryptUtil {

    private static final Charset CHARSET = StandardCharsets.UTF_8;

    private static final int BLOCK_SIZE = 32;

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
    public static String decrypt(String sessionKey, String encryptedData, String ivStr) {
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

    /**
     * 获得对明文进行补位填充的字节.
     * @param count 需要进行填充补位操作的明文字节个数
     * @return 补齐用的字节数组
     */
    private static byte[] encode(int count) {
        // 计算需要填充的位数
        int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
        // 获得补位所用的字符
        char padChr = chr(amountToPad);
        StringBuilder tmp = new StringBuilder();
        for (int index = 0; index < amountToPad; index++) {
            tmp.append(padChr);
        }
        return tmp.toString().getBytes(CHARSET);
    }

    /**
     * 删除解密后明文的补位字符.
     *
     * @param decrypted 解密后的明文
     * @return 删除补位字符后的明文
     */
    private static byte[] decode(byte[] decrypted) {
        int pad = decrypted[decrypted.length - 1];
        if (pad < 1 || pad > 32) {
            pad = 0;
        }
        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }

    /**
     * 将数字转化成ASCII码对应的字符，用于对明文进行补码.
     *
     * @param a 需要转化的数字
     * @return 转化得到的字符
     */
    private static char chr(int a) {
        byte target = (byte) (a & 0xFF);
        return (char) target;
    }
}
