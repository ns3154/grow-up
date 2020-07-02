package com.example.demo.verify;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *   内存泄漏
 *   https://juejin.im/post/5ec77f69f265da76eb7ffc1b?utm_source=gold_browser_extension#heading-2
 *
 *   javap -c MapTest$1.class
 * </pre>
 * @author
 * @since 1.0
 * @date 2020/06/28 17:11
 **/
public class MapTest {

    public static void main(String[] args) {
//        Map<String, String> map = new HashMap() {{
//            put("map1", "value1");
//            put("map2", "value2");
//            put("map3", "value3");
//        }};
//        map.forEach((k, v) -> {
//            System.out.println("key:" + k + " value:" + v);
//        });
    }

    public Map createMap() {
        Map map = new HashMap() {{
            put("map1", "value1");
            put("map2", "value2");
            put("map3", "value3");
        }};
        return map;
    }

    public JSONObject creatJson() {
        JSONObject jsonObject = new JSONObject()  {{
            put("json1", "value1");
            put("json2", "value2");
            put("json3", "value3");
        }};

        return jsonObject;
    }
}
