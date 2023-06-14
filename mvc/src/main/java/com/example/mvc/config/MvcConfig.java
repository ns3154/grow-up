package com.example.mvc.config;

import com.example.mvc.config.bean.MyHandlerInterceptor;
import javax.annotation.PreDestroy;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * <p>
 *     desc
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/12/09 17:18
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer, Destroyable {

    //
    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        String[] include = {"/upload"};
        // q: 解释下下面的代码

        MappedInterceptor interceptor = new MappedInterceptor(include, null, new MyHandlerInterceptor());
        // 增加一个 数据转换
        registry.addInterceptor(interceptor);


//        registry.addInterceptor(interceptor);
    }



}
