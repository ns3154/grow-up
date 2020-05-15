package com.example.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <pre>
 *      新增类型注解:ElementType.TYPE_USE 和ElementType.TYPE_PARAMETER（在Target上）
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 19:04
 **/
public class Annotations {

    @Retention( RetentionPolicy.RUNTIME )
    @Target( {ElementType.TYPE_USE, ElementType.TYPE_PARAMETER } )
    public @interface NonEmpty {
    }

    public static class Holder< @NonEmpty T > extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {
        }
    }

    public static void main(String[] args) {
        final Holder< String > holder = new @NonEmpty Holder< String >();
        @NonEmpty Collection< @NonEmpty String > strings = new ArrayList<>();
    }
}
