package com.example.demo.optional;

import com.example.demo.lambda.Point;
import net.bytebuddy.agent.builder.LambdaFactory;
import org.apache.logging.log4j.util.LambdaUtil;
import org.junit.Test;

import java.util.Optional;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/08 11:24
 **/
public class OptionalTest {

    @Test
    public void test1() {
        Optional<People>  people = Optional.of(new People());
        Double aDouble = people
                .flatMap(People::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getPrice).orElse(32.3);
        System.out.println(aDouble);

        Optional<People> p1 = Optional.empty();
        Optional<Car> c1 = Optional.empty();

        Double aDouble1 = p1
                .flatMap(p -> p.getCar()
                        .map(c -> c.getInsurance()
                                .map(Insurance::getPrice)
                                .orElse(1.1)))
                .orElse(11.2);
        System.out.println(aDouble1);



        People p2 = new People();
        p2.setName("yagn");
        Optional.ofNullable(p2).filter(p -> "yagn".equals(p.getName())).ifPresent(s -> System.out.println("名字存在:" + s.getName()));
    }

    @Test
    public void get() {
        Integer integer = Optional.ofNullable(1).get();
        System.out.println(integer);
    }

    @Test
    public void orElse() {
        Object o = Optional.ofNullable(null).orElse(1);
        System.out.println(o);
    }

    @Test
    public void orElseGet() {
        Object o = Optional.ofNullable(null).orElseGet(() -> 1);
        Object o1 = Optional.ofNullable(0).orElseGet(() -> 1);
        System.out.println(o);
        System.out.println(o1);
    }

    @Test
    public void or() {
        Object o = Optional.ofNullable(null).orElseThrow(() -> new UnsupportedOperationException("111"));
        System.out.println(o);
    }

    @Test
    public void ifPresent() {
        Optional.ofNullable("3232").ifPresent(s -> System.out.println("值存在:" + s));
        Optional.ofNullable(null).ifPresent(s -> System.out.println("不做任何操作"));
    }

}
