package com.example.demo;

import com.example.demo.thread.SynchronizedModel;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东
 * @date 2020/04/17 14:01
 * @since 1.0
 **/
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);


    @org.junit.Test
    public void reference() {
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);
        SoftReference<Object> softReference = new SoftReference<>(o);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(softReference.get());
        o = null;
        System.out.println("---------------------");
        try {
            byte[] bytes = new byte[2 * 1024 * 1024];
            Thread.sleep(10000);
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("强引用" + o);
            System.out.println("软引用" + softReference.get());
            System.out.println("弱引用" + weakReference.get());

        }

    }

    @org.junit.Test
    public void hashMap() throws InterruptedException {
        HashMap<Key, String> map = new HashMap<>();
        Key k1 = new Key(1);
        Key k2 = new Key(1);
        Key k3 = new Key(1);
        Key k4 = new Key(1);
        Key k5 = new Key(1);
        Key k6 = new Key(1);
        Key k7 = new Key(1);
        Key k8 = new Key(1);
        Key k9 = new Key(1);
        Key k10 = new Key(1);
        Key k11 = new Key(1);
        map.put(k1, "k1");
        map.put(k2, "k2");
        map.put(k3, "k3");
        map.put(k4, "k4");
        map.put(k5, "k5");
        map.put(k6, "k6");
        map.put(k7, "k7");
        map.put(k8, "k8");
        map.put(k9, "k9");
        map.put(k10, "k10");
        map.put(k11, "k11");
        map.get(k1);
        map.remove(k1);
        map.size();

        Iterator<Map.Entry<Key, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }

        ConcurrentHashMap<String, Integer> map1 = new ConcurrentHashMap<>(16);
        map1.computeIfAbsent(
                "AaAa",
                key -> {
                    return map1.computeIfAbsent(
                            "BBB1B",
                            key2 -> 42);
                }
        );


    }

    @org.junit.Test
    public void hashmapTest() throws InterruptedException {
        HashMap<String, String> nMap = new HashMap<>(64);

        new Thread(() -> {
            int i = 0;
            for (; ; ) {
                nMap.put(String.valueOf(i), String.valueOf(i++));
                try {
                    Thread.sleep(500);
                    System.out.println("put:" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始迭代");
        Iterator<Map.Entry<String, String>> iterator1 = nMap.entrySet().iterator();
        new Thread(() -> {
            while (iterator1.hasNext()) {
                try {
                    Thread.sleep(1000);
                    System.out.println(iterator1.next().getKey() + "," + iterator1.next().getValue());
                } catch (InterruptedException | ConcurrentModificationException e) {
                    e.printStackTrace();
                }
                iterator1.remove();
            }
        }).start();


        Thread.sleep(10000000);
    }

    @org.junit.Test
    public void concurrentHashMap() {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1", "dsf222");
        concurrentHashMap.put("2", "dsf222");
        concurrentHashMap.put("3", "dsf222");
        concurrentHashMap.put("4", "dsf222");
        concurrentHashMap.put("5", "dsf222");
        concurrentHashMap.put("6", "dsf222");
        concurrentHashMap.put("7", "dsf222");
        concurrentHashMap.put("8", "dsf222");
        concurrentHashMap.put("9", "dsf222");
        concurrentHashMap.put("10", "dsf222");
        concurrentHashMap.put("11", "dsf222");
        concurrentHashMap.put("12", "dsf222");
        concurrentHashMap.put("13", "dsf222");
        concurrentHashMap.get("a");
        concurrentHashMap.remove("1");
        concurrentHashMap.size();
        concurrentHashMap.clear();


        Iterator<Map.Entry<String, String>> iterator = concurrentHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }


        int count = 1;
        for (; ; count++) {
            System.out.println(count);
            if (count > 1) {
                break;

            }
        }
        System.out.println(count);
    }

    @org.junit.Test
    public void linkedHashMap() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("aa", "22");
        linkedHashMap.get("aa");
        linkedHashMap.remove("aa");
        linkedHashMap.size();
    }

    @org.junit.Test
    public void threadPool() {
        ThreadFactory tf = new ThreadFactoryBuilder().setDaemon(true).setNameFormat("sss-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 6666, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), tf);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            if (i == 999) {
                threadPoolExecutor.execute(() -> {
                    int z = 1 / 0;
                });
                continue;
            }
            threadPoolExecutor.execute(() -> System.out.println(finalI));
        }
        threadPoolExecutor.shutdown();
    }

    @org.junit.Test
    public void test() {
        ConcurrentHashMap<String, String> m = new ConcurrentHashMap<>();
        for (int i = 0; i < 11111; i++) {
            m.put(i + "", "s");
        }
        int hash = 211111;
        int table = 16;
        System.out.println("hash 二进制:" + Integer.toBinaryString(hash));
        System.out.println("table 16 二进制:" + Integer.toBinaryString(table));

        System.out.println(hash & table);
        System.out.println("下标");
        System.out.println(hash & 15);
        System.out.println(hash & 31);
        System.out.println(hash & 63);


        int NCPU = Runtime.getRuntime().availableProcessors();
        int stride = 0;
        int n = 1024;
        int MIN_TRANSFER_STRIDE = 16;
        if ((stride = (NCPU > 1) ? (n >>> 3) / NCPU : n) < MIN_TRANSFER_STRIDE) {
            stride = MIN_TRANSFER_STRIDE; // subdivide range
        }
        System.out.println(stride);
        System.out.println(Integer.numberOfLeadingZeros(16));
    }

    @org.junit.Test
    public void thisEscape() {
        for (int i = 0; i < 111; i++) {
            new ThisEscape();
        }
    }

    @org.junit.Test
    public void sync() throws InterruptedException {
//		SynchronizedModel sm = new SynchronizedModel(3232323223232L, "张三", 2.3d, 'A');
//		List<Thread> list = new ArrayList<>();
//		for (int i = 0;i < 3; i++) {
//			list.add(new Thread(sm::f1));
//		}
//
//		for (Thread thread : list) {
//			thread.start();
//			Thread.sleep(1000);
//			// 查看对象内部信息
//			System.out.println(ClassLayout.parseInstance(sm).toPrintable());
//			// 查看对象外部信息
////			System.out.println(GraphLayout.parseInstance(sm).toPrintable());
//			// 获取对象总大小
////			System.out.println(GraphLayout.parseInstance(sm).totalSize());
//			System.out.println("------------------------------------------");
//		}
        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
        System.out.println(ClassLayout.parseInstance(new SynchronizedModel()).toPrintable());
    }

    @org.junit.Test
    public void ss() {
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        try {
            lock.lock();
            try {
                System.out.println("重入");
            } finally {
                lock.unlock();
            }
            System.out.println(111);
        } finally {
            lock.unlock();
        }


    }


    // -XX:-UseCompressedOops
    public static void main(String[] args) throws InterruptedException {
        SynchronizedModel sm = new SynchronizedModel(3232323223232L, "张三", 2.3d, 'A');
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            list.add(new Thread(sm::f1));
        }

        for (Thread thread : list) {
            thread.start();
            Thread.sleep(1000);
            // 查看对象内部信息
            System.out.println(ClassLayout.parseInstance(sm).toPrintable());
            // 查看对象外部信息
//			System.out.println(GraphLayout.parseInstance(sm).toPrintable());
            // 获取对象总大小
//			System.out.println(GraphLayout.parseInstance(sm).totalSize());
            System.out.println("------------------------------------------");
        }
    }

    @org.junit.Test
    public void isPalindrome() {
        int num = 1231321;
        int t = num;
        int z = 0;
        while (t >= 10) {
            z = z * 10 + t % 10;
            t /= 10;
        }
        z = z * 10 + t;

        System.out.println(z == num ? true : false);

    }

    @org.junit.Test
    public void findMedianSortedArrays() {
        int[] a1 = {1, 3, 5, 7};
        int[] a2 = {2, 4, 6, 8};

        int al1 = a1.length;
        int al2 = a2.length;
        int len = al1 + al2;
        Double z = (double) len / 2;
        int zi = z.intValue();
        boolean b = z > 0 ? false : true;
        int[] ns = new int[len];


        int index = 0;
        int ni = 0;
        for (int t = 0; t < al1; t++) {
            if (a1[t] < a2[index]) {
                ns[ni++] = a1[t];
                continue;
            } else if (a1[t] == a2[index]) {
                ns[ni++] = a1[t];
                ns[ni++] = a2[index++];
                continue;
            } else {
                do {
                    ns[ni++] = a2[index++];
                } while (a1[t] > a2[index]);
                ns[index++] = a1[t];
            }
        }
        System.out.println(Arrays.stream(ns).toArray());
    }

    @org.junit.Test
    public void arraySort() throws ExecutionException, InterruptedException {
        int[] aArray = {1, 3, 5, 7};
        int[] bArray = {2, 4, 6, 8};
        System.out.println(findMedianSortedArrays(aArray, bArray));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Object> submit = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return new Object();
            }
        });
        submit.get();

    }

    @org.junit.Test
    public void string() throws NoSuchFieldException, IllegalAccessException {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);

        Field value = s1.getClass().getDeclaredField("value");
        value.setAccessible(true);
        System.out.println("s1 hashcode:" + s1.hashCode());
        System.out.println("s2 hashcode:" + s2.hashCode());
        char[] chars = (char[]) value.get(s1);
        chars[0] = '1';
        chars[1] = '2';
        chars[2] = '3';

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
        System.out.println("s1 hashcode:" + s1.hashCode());
        System.out.println("s2 hashcode:" + s2.hashCode());
    }

    @org.junit.Test
    public void availableProcessors() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


    public void lock() {

    }

    @org.junit.Test
    public void byteTest () {
        final byte b1 = 1;
        final byte b2 = 3;
        byte b3 = b1 + b2;
        System.out.println(b3);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int alen = nums1.length;
        int blen = nums2.length;
        int aIndex = 0;
        int bIndex = 0;
        int len = alen + blen;
        int[] array = new int[len];

        int z = len / 2;
        boolean b = len % 2 == 0;
        for (int i = 0; i <= z; i++) {
            if (aIndex < alen && bIndex < blen) {
                if (nums1[aIndex] <= nums2[bIndex]) {
                    array[i] = nums1[aIndex++];
                } else {
                    array[i] = nums2[bIndex++];
                }
            } else if (aIndex < alen) {
                array[i] = nums1[aIndex++];
            } else {
                array[i] = nums2[bIndex++];
            }

            if (b) {
                if (i == z) {
                    return ((double) array[i - 1] + array[i]) / 2;
                }
            } else {
                if (i == z) {
                    return array[i];
                }
            }
        }
        return 0;
    }

    static class Key {

        private Integer num;

        public Key() {
            // nothing
        }

        public Key(Integer num) {
            this.num = num;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Key)) {
                return false;
            }

            Key key = (Key) o;

            return num != null ? num.equals(key.num) : key.num == null;
        }

        @Override
        public int hashCode() {
            return num != null ? num.hashCode() : 0;
        }
    }

    static class MyCallable<T> implements Callable<T> {
        @Override
        public T call() throws Exception {
            return null;
        }
    }

}
