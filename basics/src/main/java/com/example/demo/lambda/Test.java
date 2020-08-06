package com.example.demo.lambda;

import com.example.demo.Model;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 100, 1000, TimeUnit.SECONDS, new SynchronousQueue<>(),
            new BasicThreadFactory.Builder().namingPattern(Joiner.on("-").join("test-thread-pool", "%s")).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", true, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french", false, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season friot", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));


    @org.junit.Test
    public void test() {
        Map<Dish.Type, List<Dish>> collect = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(collect.size());
    }

    @org.junit.Test
    public void sort() {

        List<Integer> list = Arrays.asList(6, 21, 9, 3, 10);

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
        for (int i = 0; i < 100; i++) {
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

        for (int i = 0; i < 10; i++) {
            Apple apple = new Apple();
            apple.setWeight(0);
            apple.setName((char) (Math.random() * 26 + 'a') + "");
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
        logger.info("红色的并且重量>150,{},颜色:{}, 重量:{}", redAndHeavyApple.test(apple1), apple1.getColor(),
                apple1.getWeight());


    }

    @org.junit.Test
    public void menu() {

        BiFunction<Integer, Boolean, Boolean> bf = (i,b) -> i > 300 && b;
        List<String> collect = menu.stream().filter(d -> {
            System.out.println("---------------------");
            System.out.println("caloriees > 300:" + d.getName());

            return d.getCalories() > 300;
        }).filter(d -> {
            System.out.println("vegetarian:" + d.getName());
            return d.isVegetarian();
        }).map(d -> {
            System.out.println("map:" + d.getName());
            return d.getName();
        }).collect(Collectors.toList());
        System.out.println(collect);

        ;
        List<String> collect1 = menu.stream().filter(d -> bf.apply(d.getCalories(), d.isVegetarian())).map(d -> {
            System.out.println("map:" + d.getName());
            return d.getName();
        }).collect(Collectors.toList());
        System.out.println(collect1);
    }

    @org.junit.Test
    public void distinct() {
        List<String> list = Arrays.asList("2", "b", "c", "b", "2");
        list.stream().distinct().forEach(System.out::println);



    }

    @org.junit.Test
    public void groupBy() {
        Map<Dish.Type, Map<Boolean, List<Dish>>> collect = menu.stream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.groupingBy(Dish::isVegetarian)));
        System.out.println(collect.size());

        Map<Dish.Type, Dish> collect1 = menu.stream().collect(Collectors.toMap(Dish::getType, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(collect1.size());

        List<Dish> list = new ArrayList<>();
        ArrayList<Dish> collect2 = menu.stream().collect(Collectors.toCollection(ArrayList::new));
    }

    @org.junit.Test
    public void customCollector() {
        List<Dish> collect = menu.stream().collect(new ToListCollector<>());
        collect.forEach(System.out::println);
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        int a = 40;
        Runnable runnable = () -> {
//            int a = 2;
            System.out.println(a);
        };

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                int a = 3;
                System.out.println(a);
            }
        };
    }
}
