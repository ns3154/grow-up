package com.example.dubbo.provider.service.dubbo.impl;

import com.example.common.api.AsyncDubboServiceTestApi;
import com.example.common.api.DubboReferenceConfigCacheTestServcie;
import com.example.common.model.ModelMessage;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.ServiceBean;

import jakarta.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/06/04 21:45
 **/
@DubboService
public class DubboReferenceConfigCacheTestServcieImpl implements DubboReferenceConfigCacheTestServcie {


    @Override
    public ModelMessage<String> hello(String str) {
        return new ModelMessage<>(str + System.currentTimeMillis());
    }
}
