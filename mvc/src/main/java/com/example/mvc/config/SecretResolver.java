package com.example.mvc.config;

import com.example.mvc.annotation.Secret;
import com.example.mvc.model.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * <pre>
 *      controller 无 @requestBody 操作 解密 resolver
 *      例如:{@link com.example.mvc.controller.UserController#adviceGetTest(UserDTO)} 需 和注解
 *      {@link Secret} 配合使用
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 19:44
 **/
public class SecretResolver  implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Secret secret = parameter.getAnnotatedElement().getAnnotation(Secret.class);
        if (null != secret) {
            return secret.decrypt();
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        ModelAttributeMethodProcessor processor = new ModelAttributeMethodProcessor(true);
        return processor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }


}
