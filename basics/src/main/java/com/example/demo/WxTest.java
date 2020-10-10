package com.example.demo;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/09/24 21:10
 **/
public class WxTest {

    public static void main(String[] args) {
        String appId = "wx4f4bc4dec97d474b";
        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
        String encryptedData =
                "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM"+
                        "QmRzooG2xrDcvSnxIMXFufNstNGTyaGS"+
                        "9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+"+
                        "3hVbJSRgv+4lGOETKUQz6OYStslQ142d"+
                        "NCuabNPGBzlooOmB231qMM85d2/fV6Ch"+
                        "evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6"+
                        "/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw"+
                        "u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn"+
                        "/Hz7saL8xz+W//FRAUid1OksQaQx4CMs"+
                        "8LOddcQhULW4ucetDf96JcR3g0gfRK4P"+
                        "C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB"+
                        "6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns"+
                        "/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd"+
                        "lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV"+
                        "oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG"+
                        "20f0a04COwfneQAGGwd5oa+T8yO5hzuy"+
                        "Db/XcxxmK01EpqOyuxINew==";
        String ivStr = "r7BXXKkLb8qrSNn05n0qiA==";

        System.out.println(WxCryptUtil.decrypt(sessionKey, encryptedData, ivStr));
    }


}
