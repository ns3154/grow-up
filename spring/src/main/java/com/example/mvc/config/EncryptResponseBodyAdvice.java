package com.example.mvc.config;

import com.alibaba.fastjson.JSON;
import com.example.mvc.annotation.Secret;
import com.example.model.ModelMessge;
import com.example.utils.AesEncryptUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东 (qq:397827222)
 * @since 1.0
 * @date 2019/05/08 14:02
 **/
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice {

    /**
     * Whether this component supports the given controller method return type
     * and the selected {@code HttpMessageConverter} type.
     * @param returnType the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked;
     * {@code false} otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Secret methodAnnotation = returnType.getMethodAnnotation(Secret.class);
        if (null != methodAnnotation) {
            return methodAnnotation.encrypt();
        }

        return false;
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     * @param body the body to be written
     * @param returnType the return type of the controller method
     * @param selectedContentType the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request the current request
     * @param response the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!(body instanceof ModelMessge)) {
            return body;
        }

        ModelMessge data = (ModelMessge) body;
        Object resp = data.getData();
        if (null == resp) {
            return body;
        }

        String respStr = JSON.toJSONString(resp);
        //noinspection unchecked
        data.setData(AesEncryptUtils.encrypt(respStr));

        return data;
    }
}
