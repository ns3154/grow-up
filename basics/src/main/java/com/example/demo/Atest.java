package com.example.demo;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/02 23:14
 **/
public class Atest {

    public static void main(String[] args) {
        int h = 3;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long time = System.currentTimeMillis() + 3 * 60 * 60 * 1000;
        Date date = new Date(time);
        String s = sdf.format(date);
        String speed = "UPDATE baojia_box.box_data_backup_201120 t SET t.gps_timestamp = '2020-11-20 01:18:34', t" +
                ".speed = 100 WHERE t.id = 571723;";
    }

    private boolean b = true;

    @Test
    public void t1() {
        ThreadLocal<String> t1 = new ThreadLocal<>();
        ThreadLocal<String> t2 = new ThreadLocal<>();
        t1.set("t1");
        t2.set("t2");
        String s = t1.get();
        System.out.println(s);
    }
}
