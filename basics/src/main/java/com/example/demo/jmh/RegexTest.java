package com.example.demo.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import java.net.URI;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/01/04 15:34
 **/
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 1 轮，每次 1s
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次1s
@Fork(1) // fork 1 个线程
@State(Scope.Benchmark)
@Threads(5)
public class RegexTest {

    private static final Map<String, Function<HttpRequest, String>> CAT_URL = new HashMap<>();

    private static final Map<String, Map<Pattern, String>> REGEX_URL = new HashMap<>();

    public static final Pattern PAYSCORE_COMPLETE = Pattern.compile("api.mch.weixin.qq.com/v3/payscore/serviceorder/" +
            ".*/complete");

    @Param({"api.mch.weixin.qq.com/v3/payscore/payafter-orders/234sdfsfd2342342342423424323423423sfd/complete", "api" +
            ".mch.weixin.qq.com/v3/payscore/payafter-orders/sadfsdf2342342342342342342342342sdf/sync", "api.mch" +
            ".weixin.qq.com/v3/payscore/serviceorder/23423423424234sfdsdfsdf234234234234/cancel","api.mch.weixin.qq" +
            ".com/v3/payscore/serviceorder/1231sadfsd3333333334234234234234234f/sync","api.mch.weixin.qq" +
            ".com/v3/payscore/serviceorder/sefsdfwer2345234saaefsdfsdfwe423423w34esf/modify","api.mch.weixin.qq" +
            ".com/v3/marketing/favor/users/234234sfsdfwer234wserfsdfserwe42334234234/coupons","api.mch.weixin.qq" +
            ".com/v3/pays2core/permissions/openid/2342sdfsdr234234sfsdfsrdwe42342fsdfwer2w3423", "api.mch.weixin.qq" +
            ".com/v3/certificates", "api.mch.weixin.qq.com/v3/payscore/serviceorder", "www.baidu.com"})
    public String url;

    public static HttpRequest request = new HttpRequest() {
        @Override
        public HttpMethod getMethod() {
            return HttpMethod.GET;
        }

        @Override
        public URI getURI() {
            return null;
        }

        @Override
        public HttpHeaders getHeaders() {
            return null;
        }

        @Override
        public java.util.Map<String, Object> getAttributes() {
            return new java.util.HashMap<>();
        }
    };

    public static void main(String[] args) throws RunnerException {
        CAT_URL.put("aip.baidubce.com/oauth/2.0/token", null);
        CAT_URL.put("wechat.baojia.com/zhanggui-java/auth/gongzhonghao/renmin", null);
        CAT_URL.put("wechat.baojia.com/zhanggui-java/auth/gongzhonghao/mifeng", null);
        CAT_URL.put("api.253.com/open/flashsdk/mobile-query-m", null);
        CAT_URL.put("api.253.com/open/flashsdk/mobile-query-u", null);
        CAT_URL.put("api.253.com/open/flashsdk/mobile-query-t", null);
        CAT_URL.put("restapi.amap.com/v3/geocode/regeo", null);
        CAT_URL.put("api.weixin.qq.com/sns/jscode2session", null);
        CAT_URL.put("api.253.com/open/flashsdk/mobile-query", null);
        CAT_URL.put("api.weixin.qq.com/wxa/getwxacodeunlimit", null);
        CAT_URL.put("api.weixin.qq.com/sns/oauth2/access_token", null);
        CAT_URL.put("api.mch.weixin.qq.com/v3/certificates", r -> "wx_certificates");

        CAT_URL.put("api.weixin.qq.com/cgi-bin/token", r -> "wx_access_token");
        CAT_URL.put("ek.caikaixin.cn/sendFpInfo", r -> "fp_sendFpInfo");
        CAT_URL.put("ek.caikaixin.cn/token", r -> "fp_token");
        // 创建支付订单
        CAT_URL.put("api.mch.weixin.qq.com/v3/payscore/serviceorder", r -> {
            if (null != r.getMethod() && HttpMethod.POST == r.getMethod()) {
                return "payscore_create";
            }
            return "payscore_select";
        });


        Map<Pattern, String> restUrls = new HashMap<>();

        // 完结支付分订单
        restUrls.put(PAYSCORE_COMPLETE, "payscore_complete");
        // 取消支付分订单
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/payscore/serviceorder/.*/cancel"), "payscore_cancel");
        // 同步服务订单
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/payscore/serviceorder/.*/sync"), "payscore_sync");
        // 修改订单金额
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/payscore/serviceorder/.*/modify"), "payscore_modify");
        // 查询与用户授权记录
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/payscore/permissions/openid/.*"),
                "payscore_permissions");
        // 根据商户号查用户的券
        restUrls.put(Pattern.compile("api.mch.weixin.qq.com/v3/marketing/favor/users/.*/coupons"), "wx_coupons");

        REGEX_URL.put("api.mch.weixin.qq.com", restUrls);



        Options opt = new OptionsBuilder()
                .include(RegexTest.class.getSimpleName())
                .output("C:\\Users\\Ns\\Desktop\\Benchmark-1.log")
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public String regex() {
        try {
            URI uri = request.getURI();
//            String remoteUrl = uri.getHost() + uri.getPath();
            String name;
            Function<HttpRequest, String> fun;
            if (null != (fun = CAT_URL.get(url)) && null != (name = fun.apply(request))) {
                return name;
            }

            Map<Pattern, String> regexs = REGEX_URL.get(uri.getHost());

            if (null != regexs) {
                Set<Map.Entry<Pattern, String>> entries = regexs.entrySet();
                for (Map.Entry<Pattern, String> entry : entries) {
                    if (entry.getKey().matcher(url).matches()) {
                        return entry.getValue();
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

}
