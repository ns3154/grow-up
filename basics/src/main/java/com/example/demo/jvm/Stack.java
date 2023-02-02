package com.example.demo.jvm;

import org.junit.jupiter.api.Test;

public class Stack {



    public void param(Integer a) {

        System.out.println(a);
        a = new Integer(33);
        System.out.println(a);
    }


    @Test
    public void test() {
        Integer a = new Integer(2);
        param(a);
        System.out.println("aaaa : " + a);

    }
}
