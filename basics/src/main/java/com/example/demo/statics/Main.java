package com.example.demo.statics;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/10/21 16:50
 **/
public class Main {

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.modify("abc", 1);
        Demo demo1 = new Demo();
        System.out.println(demo1.getSet());
        System.out.println(demo1.getInteger());
    }
}
