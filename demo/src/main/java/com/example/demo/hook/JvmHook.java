package com.example.demo.hook;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 15:13
 **/
public class JvmHook {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("2222222222222");
        }));
        System.out.println(111);
    }
}
