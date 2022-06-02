package com.example.demo.reference;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/06/02 12:45
 **/
public class ReferenceTest {



    String s = new String("sdfsd'");
    Integer t = 324242423;

    /**
     * WeakReference是Java语言规范中为了区别直接的对象引用（程序中通过构造函数声明出来的对象引用）而定义的另外一种引用关系。
     * WeakReference标志性的特点是：reference实例不会影响到被引用对象的GC回收行为
     * （即只要对象被除WeakReference对象之外所有的对象解除引用后，该对象便可以被GC回收），
     * 只不过在被对象回收之后，reference实例想获得被应用的对象时程序会返回null
     *
     * 作者：沈渊
     * 链接：https://www.jianshu.com/p/3bb70ae81828
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    @Test
    public void weak () throws InterruptedException {
        // 1.如果弱引用的引用对象实在jvm缓存中或在常量池,那么弱引用的引用对象就不会被回收
        // 2.比如integer [-128, 127]是不会被回收的
        // 3.比如String "abc"是不会被回收的,但是new String("abc")是会被回收的(创建了1或2个对象) new的对象是被回收了
        WeakReference<Integer> cacheIntegerReference = new WeakReference<>(-1);
        WeakReference<Integer> integerReference = new WeakReference<>(6575657);
        WeakReference<String> stringReference = new WeakReference<>("abc");
        WeakReference<String> newStringReference = new WeakReference<>(new String("abc"));
        WeakReference<String> allnewStringReference = new WeakReference<>(s);
        WeakReference<Integer> allnewintegerReference = new WeakReference<>(t);
        System.out.println("直接使用弱引用 cacheIntegerReference gc前" + cacheIntegerReference.get());
        System.out.println("直接使用弱引用 integerReference gc前" + integerReference.get());
        System.out.println("直接使用弱引用 stringReference gc前" + stringReference.get());
        System.out.println("直接使用弱引用 newStringReference gc前" + newStringReference.get());
        System.out.println("直接使用弱引用 allnewStringReference gc前" + allnewStringReference.get());
        System.out.println("直接使用弱引用 allnewintegerReference gc前" + allnewintegerReference.get());
        System.gc();
        System.out.println("直接使用弱引用 cacheIntegerReference gc后" + cacheIntegerReference.get());
        System.out.println("直接使用弱引用 integerReference gc后" + integerReference.get());
        System.out.println("直接使用弱引用 stringReference gc后" + stringReference.get());
        System.out.println("直接使用弱引用 newStringReference gc后" + newStringReference.get());
        System.out.println("直接使用弱引用 allnewStringReference gc后" + allnewStringReference.get());
        System.out.println("直接使用弱引用 allnewintegerReference gc后" + allnewintegerReference.get());

        System.out.println("------------- end --------------------");


        /****************************************************************************************
         *  使用WeakReference修饰的对象被称为弱引用，只要发生垃圾回收，若这个对象只被弱引用指向，那么就会被回收
         ****************************************************************************************/
        ReferenceModel model = new ReferenceModel(1, "张三");

        HashMap<String, Object> map = new HashMap<>();
        map.put("key", model);
        WeakReference<Object> weakReference = new WeakReference<>(model);

        System.out.println("ReferenceModel gc前" + weakReference.get());
        System.gc();
        Thread.sleep(1000);
        System.out.println("WeakReference ReferenceModel gc后" + weakReference.get());
        System.out.println("HashMap ReferenceModel gc后" + weakReference.get());

        // ReferenceModel 制空 等待垃圾回收
        model = null;
        // model已经制空,但是map中还是引用了model的内存地址,此处将强引用断开
        // 就算model制空,如果不断开 model所有的强引用地址,弱引用不会被回收
        map.clear();
        System.gc();
        Thread.sleep(1000);
        System.out.println("ReferenceModel=null,hashmap的强引用去掉 gc后" + weakReference.get());
        System.out.println("此处代码说明一点,发生gc时,弱引用的对象只要不被强引用指向,就会被回收");
        System.out.println("------------- end --------------------");
    }

    static class WeakEntry<T> extends WeakReference<Object> {

        T value;

        public WeakEntry(Object key, T value) {
            super(key);
            this.value =  value;
        }

        public T getValue() {
            return value;
        }
    }

}
