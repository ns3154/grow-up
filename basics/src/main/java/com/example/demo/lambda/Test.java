package com.example.demo.lambda;

import com.example.demo.Model;
import com.example.demo.extend.A;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/29 11:50
 **/
public class Test {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    ToIntFunction<String> intFunction = Integer::parseInt;

    BiPredicate<List<String>, String> biPredicate = List::contains;

    Function<Integer, Model> integerModelFunction = Model::new;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 100, 1000, TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new BasicThreadFactory.Builder()
                    .namingPattern(Joiner.on("-")
                            .join("test-thread-pool", "%s"))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @org.junit.Test
    public void sort() {

        List<Integer> list = Arrays.asList(6,21,9,3,10);

        list.sort((o1, o2) -> o2 - o1);
        list.forEach(System.out::println);
        String b = "32";
        int c = intFunction.applyAsInt(b);
        System.out.println(c);

        List<String> strList = Arrays.asList("s", "ds", ":40", "dsd");

        String str = "ds";
        System.out.println(biPredicate.test(strList, str));
        System.out.println(biPredicate.and((strings, s) -> "t".equals(s)).test(strList, str));

        List<Future<Model>> futures = new ArrayList<>(100);
        for (int i = 0;i < 100;i++) {
            int finalI = i;
            Future<Model> submit = executor.submit(() -> integerModelFunction.apply(finalI));
            futures.add(submit);
        }

        futures.stream().forEach(s -> {
            try {
                System.out.println(s.get().getCode());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        List<Apple> apples = new ArrayList<>();

        for (int i = 0;i < 10;i++) {
            Apple apple = new Apple();
            apple.setWeight(0);
            apple.setName((char)(Math.random() * 26 + 'a') + "");
            apples.add(apple);
        }

        apples.sort(Comparator.comparingInt(Apple::getWeight).reversed().thenComparing(Apple::getName));

        for (Apple apple : apples) {
            System.out.print(apple.getName());
            System.out.println(apple.getWeight());
        }

    }

    @org.junit.Test
    public void preDicate() {
        // 不是红色
        Predicate<Apple> notRedNegate = apple -> !apple.getColor().equals("RED");

        // 红色并且 weight > 150
        Predicate<Apple> redAndHeavyApple = notRedNegate.negate().and(a -> a.getWeight() > 150);

        // 红色或者weight > 150
        Predicate<Apple> redOrHeavyApple = notRedNegate.negate().or(a -> a.getWeight() > 150);

        Apple apple = new Apple();
        apple.setWeight(151);
        apple.setName("搜索");
        apple.setColor("GREEN");
        logger.info("红色的并且重量>150,{},颜色:{}, 重量:{}", redAndHeavyApple.test(apple), apple.getColor(), apple.getWeight());
        logger.info("不是红色:{}, 颜色:{}", notRedNegate.test(apple), apple.getColor());
        logger.info("红色或者是重量大于>150:{}, 颜色:{}, 重量:{}", redOrHeavyApple.test(apple), apple.getColor(), apple.getWeight());

        Apple apple1 = new Apple();
        apple1.setWeight(165);
        apple1.setName("搜索");
        apple1.setColor("RED");
        logger.info("红色的并且重量>150,{},颜色:{}, 重量:{}", redAndHeavyApple.test(apple1), apple1.getColor(), apple1.getWeight());




    }
}
