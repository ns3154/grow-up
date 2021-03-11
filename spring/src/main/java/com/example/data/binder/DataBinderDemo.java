package com.example.data.binder;

import com.alibaba.fastjson.JSON;
import com.example.data.binder.model.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.DataBinder;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
	    try {
		    easyBinder();
	    }
	    catch (IntrospectionException e) {
		    e.printStackTrace();
	    }
	    catch (IllegalAccessException e) {
		    e.printStackTrace();
	    }
	    catch (InvocationTargetException e) {
		    e.printStackTrace();
	    }
    }

    private static void easyBinder() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
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

	    FilterUrlProperties filterUrlProperties = new FilterUrlProperties();

	    Class<?> aClass = filterUrlProperties.getClass();
	    Field[] fields = aClass.getFields();
	    String endFix = "noLogin";
	    String value = "";
	    for (Field f : fields) {
	    	if (f.getName().equals(endFix)) {
			    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(endFix, aClass);
			    Method method = propertyDescriptor.getWriteMethod();
			    Class<?> type = f.getType();
			    Field field = JSON.parseObject(value, f.getClass());
				method.invoke(filterUrlProperties, field);
		    }

	    }


    }
}
