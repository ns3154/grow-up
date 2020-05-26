package com.example.mvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * <pre>
 *      自定义拦截器
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/11 14:28
 **/
@Component
public class MyFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        logger.error("***MyFilter#doFilter");
        chain.doFilter(request, response);

    }
}
