package com.example.mvc.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 16:19
 **/
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 拦截逻辑处理
     * @author 杨帮东
     * @since 1.0
     * @date 2020/3/23 16:20
     * @return boolean true 放行, false 停止进行向下处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView)  {
        // nothing
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet
     * 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        // nothing
    }
}
