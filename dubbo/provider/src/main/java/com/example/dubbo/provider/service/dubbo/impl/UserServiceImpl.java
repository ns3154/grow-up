package com.example.dubbo.provider.service.dubbo.impl;

import com.example.common.api.DubboTestServiceApi;
import com.example.common.model.ModelMessage;
import com.example.common.model.dto.UserDTO;
import org.apache.dubbo.config.annotation.Service;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 11:08
 **/
@Service
public class UserServiceImpl implements DubboTestServiceApi {

    @Override
    public ModelMessage<UserDTO> getUserById(Long userId) {
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setCode(200);
        model.setMessage("ok");
        model.setData(UserDTO.newBuilder().withAge(1).withUserName("yang").withSex(3).build());
        return model;
    }
}
