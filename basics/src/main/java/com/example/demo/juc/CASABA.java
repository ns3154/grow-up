package com.example.demo.juc;


import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/06/17 22:30
 **/
public class CASABA {


    public void atomicStampedReferenceTest () {

        Aba a1 = new Aba("张三", 1);

        AtomicStampedReference<Aba> stamped = new AtomicStampedReference<>(a1, 0);

        System.out.println(stamped.getReference().getAge());
        boolean bl = stamped.compareAndSet(a1, new Aba("历史", 3), 10, 1);
        System.out.println("替换结果:" + bl);
        bl = stamped.compareAndSet(a1, new Aba("历史", 3), stamped.getStamp(), 1);
        System.out.println("替换结果:" + bl);
        System.out.println(stamped.getReference().getAge());
    }

    static class Aba {

        private String name;

        private Integer age;

        public Aba(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
