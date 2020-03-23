package com.example.common.api;


import com.example.common.model.ModelMessage;
import com.example.common.model.dto.UserDTO;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 11:01
 **/
public interface DubboTestServiceApi {

    /**
     * 获取用户数据
     * @param userId 用户id
     * @return
     */
    ModelMessage<UserDTO> getUserById(Long userId);


}
