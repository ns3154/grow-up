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
    ModelMessage<UserDTO> userById(Long userId);

    /**
     * 故障转移测试接口
     * @param userId
     * @return
     */
    ModelMessage<UserDTO> byZero(Long userId);

    /**
     * 故障转移测试接口
     * @param userId
     * @return
     */
    ModelMessage<UserDTO> timeOut(Long userId);

    /**
     * 故障转移测试接口
     * @param userId
     * @return
     */
    ModelMessage<UserDTO> randomTimeOut(Long userId);


    ModelMessage<UserDTO> timeWait(Long time);
}
