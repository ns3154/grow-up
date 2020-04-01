package com.example.mvc.config;

import com.example.mvc.exception.MyException;
import com.example.model.dto.UserToken;
import com.example.utils.Constants;
import com.example.utils.JwtToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 16:05
 **/
public class HeaderResolver implements HandlerMethodArgumentResolver {

    @Resource
    private JwtToken jwtToken;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String tokenStr = webRequest.getHeader("userToken");
        UserToken ut = null;
        if (StringUtils.isBlank(tokenStr) || null == (ut = jwtToken.conversion(tokenStr))) {
            throw new MyException(Constants.ConstantsEnum.LOGIN_EXPIRES_CODE);
        }

        return ut;
    }
}
