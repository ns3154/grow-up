package com.example.demo.date;

import org.junit.Test;

import java.time.*;
import java.util.Date;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/08 15:11
 **/
public class DateTest {


    @Test
    public void test() {
        LocalDate date = LocalDate.now(ZoneId.systemDefault());
        System.out.println(date.getYear());
        System.out.println(date.getMonth().getValue());
        System.out.println(date.getDayOfWeek().getValue());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfYear());

        long l = date.toEpochDay();

        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.of("+8"));
        System.out.println(instant.toEpochMilli());
        System.out.println(l * 24 * 60 * 60);
    }

    @Test
    public void period() {
        Period between = Period.between(LocalDate.of(2020, 8, 30), LocalDate.of(2020, 8, 31));
        System.out.println(between.getDays());
    }

    @Test
    public void convert() {
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        System.out.println(date.toString());
        System.out.println(localDateTime.toString());

        System.out.println("-----------------");
        LocalDateTime ldt = LocalDateTime.of(2021,3,7, 20, 30, 21);
        Date from = Date.from(ldt.toInstant(ZoneOffset.ofHours(8)));

        System.out.println(ldt.toString());
        System.out.println(from.toString());
    }
}
