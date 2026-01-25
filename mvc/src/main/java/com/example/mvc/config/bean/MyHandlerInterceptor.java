package com.example.mvc.config.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 *     desc
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/12/09 17:25
 **/
@Slf4j
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("MyHandlerInterceptor : preHander");

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle (HttpServletRequest request, HttpServletResponse response,
                            Object handler, ModelAndView modelAndView) throws Exception {
        log.info("MyHandlerInterceptor : postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler,
                                 Exception ex) throws Exception {
        log.info("MyHandlerInterceptor : afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
