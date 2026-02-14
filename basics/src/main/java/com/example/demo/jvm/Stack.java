package com.example.demo.jvm;


public class Stack {



    public void param(Integer a) {

        System.out.println(a);
        a = Integer.valueOf(33);
        System.out.println(a);
    }


    public void test() {
        Integer a = Integer.valueOf(2);
        param(a);
        System.out.println("aaaa : " + a);

    }
}
