package com.example.data.binder;

import com.example.data.binder.model.FilterUrlProperties;
import com.example.data.binder.model.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/09/23 15:43
 **/
public class DataBinderDemo {

    public static void main(String[] args) {
        easyBinder();
    }

    private static void easyBinder() {
        User user = new User();
        DataBinder dataBinder = new DataBinder(user, "user");
        Map<String, String> map = new HashMap<>();
        map.put("age", "11");
        map.put("name", "张三");
        map.put("isMember", "false");
        map.put("userSub.phone", "13691528262");

        PropertyValues propertyValues = new MutablePropertyValues(map);
        dataBinder.bind(propertyValues);
        System.out.println(user.toString());

    }
}
