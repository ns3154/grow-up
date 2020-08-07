package com.example.demo.type;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/07 18:12
 **/
public class TypeTest {

    @Test
    public void extendsTest() {
        // 上界<? extends T>不能往里存，只能往外取
        List<? extends Father> list = extendsList();

        System.out.println(list.size());

        Father father = list.get(0);
        Son son = (Son) father;
        System.out.println(father.getId());
    }

    private List<? extends Father> extendsList() {
        List<Father> list = new ArrayList<>();
        list.add(new Son());
        list.add(new XiaoMing());
        return list;
    }

    @Test
    public void superTest() {
        //super只能添加Father和Father的子类，不能添加Father的父类,读取出来的东西只能存放在Object类里
        List<? super Father> list = new ArrayList<>();
        list.add(new Son());
        list.add(new XiaoMing());

        Object object = list.get(1);
        XiaoMing xm = (XiaoMing) object;
        System.out.println(object.toString());
    }
}
