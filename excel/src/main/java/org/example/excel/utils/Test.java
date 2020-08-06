package org.example.excel.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/06/23 15:55
 **/
public class Test {

    public static void main(String[] args) {
        Long s =  System.currentTimeMillis() % 100000000000L;
        Long s1 =  System.currentTimeMillis() % 1000000000000L;

        System.out.println( s);
        System.out.println( s1);

        System.out.println("81978855".length());

        JSONObject data = new JSONObject();
        String frm = String.valueOf("sdfsdf");
        data.put("imei", "sdfsdf");
        data.put("key", "sdfsaf");
        data.put("frm", frm);
        data.put("rt", "sfsfd");
        data.put("serverTime", System.currentTimeMillis());
        data.put("type", "emit");


        System.out.println(data.getLongValue("serverTime"));
    }
}
