package com.example.demo.lambda;

import com.example.demo.Model;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/28 19:45
 **/
public class Variable {

    public static void main(String[] args) {
        int i = 10;
        Model model = new Model();
        model.setCode(i);
        Runnable r = () -> System.out.println(i);
//        Runnable r = () -> System.out.println(model.getCode());
        int t =  i;
        model.setCode(21);
        r.run();
    }
}
