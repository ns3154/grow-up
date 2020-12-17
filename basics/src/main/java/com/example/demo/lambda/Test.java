package com.example.demo.lambda;

import com.example.demo.Model;
import com.example.demo.extend.A;
import com.example.demo.model.*;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <pre>
 *      https://www.jianshu.com/p/461429a5edc9
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


    DoubleUnaryOperator a = (double x) -> x * 0.1 + 1;
    DoubleUnaryOperator b = (double x) -> x * 0.2 + 2;
    DoubleUnaryOperator c = (double x) -> x * 0.3 + 3;


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

    @org.junit.Test
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


    @org.junit.Test
    public void pointTest() {
        Point p1 = new Point(3, 45);
        Point p2 = new Point(3, 45);
        int compare = Point.COMPARATOR.compare(p1, p2);
        System.out.println(compare);

    }

    @org.junit.Test
    public void pointNullTest() {
        List<Point> list = Arrays.asList(new Point(1,2), new Point(3,4), null);
        list.stream()
                .map(Point::getX)
                .forEach(System.out::println);
    }

    @org.junit.Test
    public void peek() {
        BikeBatteryStandardModel normalStandard = new BikeBatteryStandardModel(45.0,
                LackPowerStatusEnum.normal.getCode());

        BikeBatteryStandardModel lowStandard = new BikeBatteryStandardModel(35.0,
                LackPowerStatusEnum.low.getCode());
        // brandsId = 5 蜜蜂
        BikeBatteryStandardModel lackmfStandard = new BikeBatteryStandardModel(25.0,
                LackPowerStatusEnum.lack.getCode());
        // 其他品牌
        BikeBatteryStandardModel lackStandard = new BikeBatteryStandardModel(15.0,
                LackPowerStatusEnum.lack.getCode());

        BikeBatteryStandardModel pinchStandard = new BikeBatteryStandardModel(0.0,
                LackPowerStatusEnum.pinch.getCode());

        BikeBatteryStandardModel zeroStandard = new BikeBatteryStandardModel(-1.0,
                LackPowerStatusEnum.zero.getCode());

        List<BikeBatteryStandardModel> standardMfTemplate = new ArrayList<>();
        standardMfTemplate.add(normalStandard);
        standardMfTemplate.add(lowStandard);
        standardMfTemplate.add(lackmfStandard);
        standardMfTemplate.add(pinchStandard);
        standardMfTemplate.add(zeroStandard);

        BikeBatteryCacheModel bikeBatteryCacheModel = new BikeBatteryCacheModel(54545L, standardMfTemplate, "45-35-25");

        double power = 78D;


        Function<BikeBatteryCacheModel, Integer> function = (bcm) -> bcm.getBikeBatteryStandards()
                .stream()
                .filter(d -> power > d.getPower())
                .peek(d -> System.out.println( "模版电量:"+d.getPower() + ",模版状态" + d.getBatteryStatus() + ",店里标准:" + bcm.getPowerStr()))
                .findFirst()
                .orElse(zeroStandard)
                .getBatteryStatus();


        System.out.println(function.apply(bikeBatteryCacheModel));

    }

    void ssss(List<Object> objects) {
        List<String> list = objects.stream().filter(o -> o instanceof String).map(o -> ((String) o).toLowerCase()).collect(Collectors.toList());
    }

    @org.junit.Test
    public void closeRange() {
        int reduce = IntStream.rangeClosed(1, 10)
                .peek(System.out::println)
                .reduce(1, (x, y) ->{
                    System.out.println("x:" + x + ",y:" + y);
                    return x * y;
                });
        System.out.println(reduce);
    }

    @org.junit.Test
    public void gao() {
        System.out.println(a.applyAsDouble(1234.0));
        System.out.println(b.applyAsDouble(1234.0));
        System.out.println(c.applyAsDouble(1234.0));
        System.out.println(a.andThen(b).andThen(c).applyAsDouble(1000));
    }


    @org.junit.Test
    public void tt() {
//        long z = tt(30000);
        long z = tt(1, 25000);
        System.out.println(z);
    }

    @org.junit.Test
    public void testConcat() {
        IntStream a = IntStream.range(10, 20);
        IntStream b = IntStream.range(40, 50);
        long count = IntStream.concat(a, b).count();
        // 两个流合并后总元素为20
        Assert.assertEquals(20, count);
    }

    @org.junit.Test
    public void testFlatMap() {
        // 这里根据上游的元素扩展出了更多的元素
        IntStream.of(1, 2, 3).flatMap(e -> IntStream.rangeClosed(0, e)).forEach(System.out::println);
    }

    @org.junit.Test
    public void t1() {
        Function<? super Animal, Boolean> f1 = animal -> animal.name.equals("狗");
        Function<? super Animal, Boolean> f2 = animal -> {
          if (animal instanceof Cat) {
              Cat cat = (Cat) animal;
              System.out.println(cat.cc());
          }
            return false;
        };
        List<Function<? super Animal, Boolean>> list = Arrays.asList(f1, f2);
        for (Function< ? super Animal, Boolean > f : list) {
            System.out.println(f.apply(new Dog()));
            System.out.println(f.apply(new Cat()));
            System.out.println(f.apply(new CatSun()));
        }

        List<? super Animal> supers = new ArrayList<>();
        supers.add(new Dog());
        supers.add(new Cat());
        supers.add(new CatSun());

        Object o = supers.get(0);




    }

    private long tt(long y) {
        return y == 1 ? 1 : y * tt(y - 1);
    }

    // 尾递  目前 jvm 不支持 下边代码 理论上占用一个栈帧
    private long tt(long x, long y) {
        return y == 1 ? x : tt(x * y, y - 1);
    }

}
