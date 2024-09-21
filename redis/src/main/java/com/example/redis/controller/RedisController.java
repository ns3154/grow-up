package com.example.redis.controller;

import com.example.redis.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.util.SafeEncoder;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/03/18 17:43
 **/
@RestController
@RequestMapping("redisTemplate")
public class RedisController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private TestService testService;

    /**
     * 集
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Object }
     */
    @GetMapping("set")
    public Object set(String key, String value) {
        return redisTemplate.execute(connection ->
                connection.set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8)),
                true);
    }


    @GetMapping("get")
    public Object get(String key) {
        return redisTemplate.execute(connection -> {
            byte[] bytes = connection.get(key.getBytes(StandardCharsets.UTF_8));

            if (null != bytes) {
                return new String(bytes, StandardCharsets.UTF_8);
            }
            return null;
        }, true);
    }

    @GetMapping("transaction")
    public Object transaction(String key, String value) {
        List<Object> object = redisTemplate.execute(connection -> {
            connection.watch(key.getBytes(StandardCharsets.UTF_8));
            connection.multi();
            connection.set("abc".getBytes(StandardCharsets.UTF_8), "sdfs".getBytes(StandardCharsets.UTF_8));
            connection.set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
            connection.get(key.getBytes(StandardCharsets.UTF_8));
            return connection.exec();
        }, true);

        return object;
    }

    @GetMapping("transcantionAnnotionTest")
    public Object transcantionAnnotionTest(String key, String value, String key1, String value1, Integer e) {
        return testService.redisTranscantionTest(key, value, key1, value1, e);
    }

    @GetMapping("pipelineSet")
    public Object pipelineSet() {
        List<Object> objects = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i = 0;i < 100; i++) {
                    String key = "key" + i;
                    String value = "value" + i;
                    connection.set(key.getBytes(), value.getBytes());
                }
                return null;
            }
        }, redisTemplate.getStringSerializer());

        return null;
    }

    @GetMapping("pipelineGet")
    public Object pipelineGet() {
        List<Object> objects = redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i = 0;i < 100; i++) {
                    String key = "key" + i;
                    connection.get(key.getBytes());
                }
                return null;
            }
        }, redisTemplate.getStringSerializer());

        return objects;
    }

    @GetMapping("scan")
    public List<String> scan(String key) {
        List<String> result =  redisTemplate.execute((connection -> {
            Cursor<byte[]> scan = connection.scan(ScanOptions.scanOptions().count(1).match(key).build());
            List<String> list = new ArrayList<>();
            while (scan.hasNext()) {
                list.add(new String(scan.next(), StandardCharsets.UTF_8));
            }
            return list;
        }), true);

        System.out.println(result.size());
        return result;
    }

    @GetMapping("test")
    public void test() {
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("batteryBinding:" + "imei");
        Map map = new HashMap();
        Object batteryImei = null;
        Object feLi = null;
        Object batteryNo = null;
        Object r48a15h = "234";
        map.put("batteryImei", batteryImei);
        map.put("isFeLiCore", feLi);
        map.put("batteryNo", batteryNo);
        map.put("is48V15Ah", r48a15h);
        stringRedisTemplate.opsForHash().putAll("batteryBinding:" + "imei", map);
    }

    @GetMapping("zSet")
    public void zSet(String key, String value, Double score) {
        Long aLong = redisTemplate.opsForZSet().removeRangeByScore(key, score, score);


//        redisTemplate.opsForZSet().zCard("cacheZSetKey:842533678900242")
        logger.info("{}", aLong);
//        redisTemplate.opsForZSet().add(key, value, score);
//        Long len = redisTemplate.opsForZSet().zCard(key);
//
//        if (len > 10) {
//            Set<String> range = redisTemplate.opsForZSet().range(key, 0, -1);
//            redisTemplate.opsForZSet().remove(key, range);
//
//            for (String str: range) {
//                logger.info("计算:{}", str);
//            }
//        }
//
//        Long cur_len = redisTemplate.opsForZSet().zCard(key);
//        logger.info("当前长度:{}", cur_len);
    }

    @GetMapping("set/nx/ex")
    public Boolean nx(String key,String value,Long secondsTimer) {
        Boolean b = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
            RedisSerializer keySerializer = redisTemplate.getKeySerializer();
            Object obj = connection.execute("set", keySerializer.serialize(key),
                    valueSerializer.serialize(value),
                    SafeEncoder.encode("NX"),
                    SafeEncoder.encode("EX"),
                    Protocol.toByteArray(secondsTimer));
            return obj != null;
        });
        return b;
    }

    @GetMapping("rPush")
    public void ss() {
        final String key = "prod:boxxan";
        final String value = "{\"gsm\":0,\"latitude\":30.50298,\"gpsTime\":1595641091,\"speed\":0," +
                "\"carId\":\"861230042925561\",\"voltage\":\"50.092\",\"mode\":0,\"course\":0,\"cmd\":\"batteryGps\"," +
                "\"time\":1595648441736,\"longitude\":114.42896}";


        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.select(2);
            for (int i = 0; i < 999;i++) {
                connection.rPush(key.getBytes(StandardCharsets.UTF_8),
                        value.getBytes(StandardCharsets.UTF_8));
            }
            return null;
        });
    }







}
