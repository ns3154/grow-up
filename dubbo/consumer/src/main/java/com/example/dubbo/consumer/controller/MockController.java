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
 * @date 2020/03/25 17:23
 **/
@RestController
@RequestMapping("mock")
public class MockController {


    /**
     * 先调用远程 如果 失败 在执行 mock
     */
    @Reference(mock = "com.example.dubbo.consumer.mock.DubboServiceTestMock")
    private DubboTestServiceApi dubboTestServiceApi;

    @GetMapping("getUserById")
    public ModelMessage<UserDTO> getUserById(Long userId) {
        return dubboTestServiceApi.getUserById(userId);
    }
}
