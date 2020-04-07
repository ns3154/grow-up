package com.example.dubbo.consumer;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.registry.RegistryFactory;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/02 16:14
 **/
public class Test {

    public static void main(String[] args) {
        RegistryFactory adaptiveExtension =
                ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();

        System.out.println(adaptiveExtension.hashCode());
    }
}
