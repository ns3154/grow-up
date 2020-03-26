package com.example.dubbo.consumer.controller;

import com.example.common.api.DubboTestServiceApi;
import com.example.common.model.ModelMessage;
import com.example.common.model.dto.UserDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/25 17:24
 **/
@RestController
@RequestMapping("force")
public class ForceMockController {

    /**
     * 直接强制执行 mock
     */
    @Reference(mock = "force:com.example.dubbo.consumer.mock.DubboServiceTestMock")
    private DubboTestServiceApi dubboTestServiceApi;

    @GetMapping("getUserById")
    public ModelMessage<UserDTO> getUserById(Long userId) {
        return dubboTestServiceApi.getUserById(userId);
    }
}
