package com.example.mcp.config;

import com.example.mcp.filter.ExecutionLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * 过滤器配置类
 * 注册执行日志过滤器和其他相关过滤器
 */
@Configuration
public class FilterConfig {

    /**
     * 注册执行日志过滤器
     * 设置过滤器顺序为最高优先级，确保能捕获所有请求和响应
     */
    @Bean
    public FilterRegistrationBean<ExecutionLogFilter> executionLogFilterRegistration(ExecutionLogFilter executionLogFilter) {
        FilterRegistrationBean<ExecutionLogFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(executionLogFilter);
        registration.addUrlPatterns("/*"); // 拦截所有请求
        registration.setName("executionLogFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE); // 最高优先级
        
        // 可以添加初始化参数
        registration.addInitParameter("logLevel", "INFO");
        registration.addInitParameter("includePayload", "true");
        
        return registration;
    }
}