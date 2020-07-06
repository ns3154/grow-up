package com.example.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.TypeUtils;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/03 20:50
 **/
public class Main {

    public static void main(String[] args) {
        String s = "{\"a5sBeforeMileage\":0.0,\"acc\":1,\"carId\":\"842533678900242\",\"cellId\":0,\"course\":0.0," +
                "\"dataFix\":0,\"dataModel\":0,\"defend\":1,\"gpsTime\":0,\"hdop\":0.0,\"inRail\":0," +
                "\"includedMileage\":1,\"lac\":0,\"latitude\":0.0,\"latitudeFilter\":0.0,\"locationType\":1," +
                "\"longitude\":0.0,\"longitudeFilter\":1.023423442,\"mcc\":0,\"mnc\":0,\"moving\":true," +
                "\"satellite\":0," +
                "\"speed\":0.0,\"voltage\":0.0}";


        SerializerFeature[] features = new SerializerFeature[] {
                SerializerFeature.WriteClassName,
                //SerializerFeature.SkipTransientField,
                //SerializerFeature.DisableCircularReferenceDetect
        };
        JSONObject jsonObject = JSONObject.parseObject(s);
        GpsStatusData cast = TypeUtils.cast(jsonObject, GpsStatusData.class, new ParserConfig());


        System.out.println(cast);
    }
}
