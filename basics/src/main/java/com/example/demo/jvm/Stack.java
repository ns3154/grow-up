package com.example.demo.jvm;


public class Stack {



    public void param(Integer a) {

        System.out.println(a);
        a = new Integer(33);
        System.out.println(a);
    }


    public void test() {
        Integer a = new Integer(2);
        param(a);
        System.out.println("aaaa : " + a);

    }
}
