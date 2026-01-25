package com.example.pay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/12/11 10:50
 **/
public class AliPay {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final static String APP_ID = "2021002116612187";
    private final static String PRIVATE_KEY =
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCI3FgNpA9VwH0ux7kEbJKe3XJip4SAMRwNf8ZUSfup1wAuKNEA56vqN+zVPyDGqYkWgvDvb8hAvZqCDm9F5zruiGI4rZ0toOZd5Kzfy+UbOts9pnwF1vF5RIV4mHiigayTdQ9EGwKnewF6dylM+C902lG94pztkZAQBKnbG9ZOnzkox8FuHq3LtPO77o2DDhX40pC3G92H8ADSWlAOW3LkiS1BdBK5VUm5N4q9Pbf0/9VPNRdIAvzl9eoe1Q/EgFmwD5DkTfr+V7ch+qdcULVOPFek5BKZGzHScaWX5xwr5PbXhZ7cAUxTUkgUnOHILv3f4bhLPT3jPOaRc8AoGysTAgMBAAECggEAb1XhA+fM2dOngqDTMHgqprY7UWo5bheFXK3vZ65v3nCbIvHtZLESt40bSzb5sONGNFv9KKtk2tXjlZ2sg89hr56fWMBTt0THYuaeg8jY7lfo9rQy/IUpF5/YfIx8xqZ6/9IgwyS9+gUk5L1/ia/02zinerOZOeJKsd05WJfCtV2771kA+U5fRyeaAvLD9lgVVmG2BZ9Tmhqyp1THvwXPBYWMzBQLFPuh/W513W+Wkzlt9H8XFBQ3fds638V82Wb7EGcf7An7W2v7rLa7Gc4R/nzH4it3FyJfCshum+mQ6g0uYfLBoYD744+LfDCAuULRsC7Xi7VR2IQvLA8v0aT0oQKBgQDPrfgwJj9R164ukljzcKQ+LIpkB4ioKFL3sLAlWxFzPa2fbf5ATEWp/yw39CLf765mVs9D4khiLEuGN2Hxig3jlz8KhPzKoRKds0fAhEs9bC2L4f2h48M0vflyiEYPWdfpVbNO2AR58K9P22XlzFRp51xaOsRUKxhU54Uy0TVXSQKBgQCotC/mAsJm4l77bdeaKaJrKUH/YTujWH7s1HSX6X5FKj14Ndp9XPFlqLK8EavgJDPvfnn+wGErUxPmcvK3cB8tfOua6w7QA/XxMzj3m+NqUfuO8pC7lXZ1p93Ez7oIzGnNFN/Jy8tvHIsja9W0zc6su7zsxPmmV5HxzZZ9H/FjewKBgQCsYZzGapjYWAwOQA9xUagiq1nbmZ1G5uGnEpPDDgo3+5Qn52bI/AY/Cr0rXIuuEQZTj+7OS/8E7ftqVoIHuS26IYqEMsqZboVDPNJr6+OAAlDz8QtT2vfmyWFKPe7ZQxvCZA5HfWqJxMvfnx6/VZEjrpSrmGfp7TNvFhUrjxeVcQKBgCMao2DkMBmCfysoVxWwFijgQ+hlDijWMEsNhllZ77lqqbHK2vT9EwoJSW/S6YXRYrEyCyaUV2PRfLGWN6UuAn7PoKLyyHlGabXP58m2OWMVPhgnpzAxUJ342S+r3dUY2+cdsddmvaUYNmefd6+Qp7HPuuc1sZEIZcj/85GDxRHjAoGAc7ZyXjPB3u57mS4LM3pDWsaSSdswIoIM+1k9A8+IsUjOO4Hp/OJ5JLN9mfiaCpaHUE3ZXQpkdBIt1sVyhZq9ApqhE20pXtmdCaz3zyyqj1OvfVbt+DoGHxF7knQKwor4v2bR3AUP6IsyzSQXrCx+gjjhw/3VDUh5Sqk60FtV7Lw=";

    private final static String PUBLIC_KEY = "-----BEGIN CERTIFICATE-----\n" +
            "MIIEqzCCA5OgAwIBAgIQICASEAhq1ZNjh9LayLLVTDANBgkqhkiG9w0BAQsFADCBgjELMAkGA1UE\n" +
            "BhMCQ04xFjAUBgNVBAoMDUFudCBGaW5hbmNpYWwxIDAeBgNVBAsMF0NlcnRpZmljYXRpb24gQXV0\n" +
            "aG9yaXR5MTkwNwYDVQQDDDBBbnQgRmluYW5jaWFsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5IENs\n" +
            "YXNzIDEgUjEwHhcNMjAxMjEwMTAwMDI3WhcNMjIxMjEwMTAwMDI3WjBzMQswCQYDVQQGEwJDTjEn\n" +
            "MCUGA1UECgwe5YyX5Lqs5rKT5rKT56eR5oqA5pyJ6ZmQ5YWs5Y+4MQ8wDQYDVQQLDAZBbGlwYXkx\n" +
            "KjAoBgNVBAMMITIwODg1MzE3MjE1MjI2OTEtMjAyMTAwMjExNjYxMjE4NzCCASIwDQYJKoZIhvcN\n" +
            "AQEBBQADggEPADCCAQoCggEBAIjcWA2kD1XAfS7HuQRskp7dcmKnhIAxHA1/xlRJ+6nXAC4o0QDn\n" + "q+o37NU" +
            "/IMapiRaC8O9vyEC9moIOb0XnOu6IYjitnS2g5l3krN/L5Rs62z2mfAXW8XlEhXiYeKKB\n" +
            "rJN1D0QbAqd7AXp3KUz4L3TaUb3inO2RkBAEqdsb1k6fOSjHwW4ercu087vujYMOFfjSkLcb3Yfw\n" +
            "ANJaUA5bcuSJLUF0ErlVSbk3ir09t/T/1U81F0gC/OX16h7VD8SAWbAPkORN+v5XtyH6p1xQtU48\n" +
            "V6TkEpkbMdJxpZfnHCvk9teFntwBTFNSSBSc4cgu/d/huEs9PeM85pFzwCgbKxMCAwEAAaOCASkw\n" +
            "ggElMB8GA1UdIwQYMBaAFHEH4gRhFuTl8mXrMQ/J4PQ8mtWRMB0GA1UdDgQWBBSo5HVEFjZzf7l3\n" +
            "xxgqHw36pd0kAzBABgNVHSAEOTA3MDUGB2CBHAFuAQEwKjAoBggrBgEFBQcCARYcaHR0cDovL2Nh\n" +
            "LmFsaXBheS5jb20vY3BzLnBkZjAOBgNVHQ8BAf8EBAMCBsAwLwYDVR0fBCgwJjAkoCKgIIYeaHR0\n" +
            "cDovL2NhLmFsaXBheS5jb20vY3JsNDguY3JsMGAGCCsGAQUFBwEBBFQwUjAoBggrBgEFBQcwAoYc\n" +
            "aHR0cDovL2NhLmFsaXBheS5jb20vY2E2LmNlcjAmBggrBgEFBQcwAYYaaHR0cDovL2NhLmFsaXBh\n" +
            "eS5jb206ODM0MC8wDQYJKoZIhvcNAQELBQADggEBAKwpTDkRU15kjW10HJ/qFd2YlFHQhZE23wlr\n" +
            "u3tyU135xGwJJUvDrl05YYIiQFnTpSCtKMLWNFMayyEtz/70kZS4ZwEfupq6JMKPqd63Ehg7aOoE\n" + "S6zt9F" +
            "/KrWL1t6UNfiBlDAHj9PBEciApR8AFmENn2Hj5O+tu6m0hMsKHBPrvU3CxUNqj/F9J22QP\n" + "D8L1pquv7" +
            "/mq9HP4bPYEIrReR8u450TqZdgLBdm3YdPNDYSh6joumvocEjH469Flqkn3BcwVbJR1\n" + "NfOrMqyjCi0SX80JC61O7+isddXm" +
            "+naCLOKVtQJI/ynRb9NgwFuThIIjxgCM18lnPyf78m5ElTRe\n" + "gsk=\n" + "-----END CERTIFICATE-----";

    private final static String URL = "https://openapi.alipay.com/gateway.do";

    private final static String PATH = "C:\\Users\\Ns\\Desktop\\alipay\\";

    public void t() throws Exception {

        Factory.setOptions(getOptions());
        AlipayTradeQueryResponse query = Factory.Payment.Common().query("607675946867000125464113");
        AlipayTradeRefundResponse refund = Factory.Payment.Common().refund("607675946867000125464113", "1");
        System.out.println(query);


    }

    private Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";

        config.appId = APP_ID;

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = PRIVATE_KEY;

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        config.merchantCertPath = PATH + "appCertPublicKey_2021002116612187.crt";
        config.alipayCertPath = PATH + "alipayCertPublicKey_RSA2.crt";
        config.alipayRootCertPath = PATH + "alipayRootCert.crt";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        // config.alipayPublicKey = "<-- 请填写您的支付宝公钥，例如：MIIBIjANBg... -->";

        //可设置异步通知接收服务地址（可选）
//        config.notifyUrl = "<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->";

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
//        config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";

        return config;
    }
}
