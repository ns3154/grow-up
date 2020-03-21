package com.example.demo;

import java.lang.annotation.*;

/**
 * <pre>
 *      重复注解@Repeatable
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 19:03
 **/
public class RepeatingAnnotations {

    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {
        Filter[] value();
    }

    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )
    public @interface Filter {
        String value();
        String value2();
    };

    @Filter( value="filter1",value2="111" )
    @Filter( value="filter2", value2="222")
    public interface Filterable {
    }

    public static void main(String[] args) {
        //获取注解后遍历打印值
        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
            System.out.println( filter.value() +filter.value2());
        }
    }
}
