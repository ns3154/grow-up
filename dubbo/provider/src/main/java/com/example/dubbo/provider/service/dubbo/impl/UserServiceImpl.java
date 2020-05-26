package com.example.dubbo.provider.service.dubbo.impl;

import com.example.common.api.DubboTestServiceApi;
import com.example.common.model.ModelMessage;
import com.example.common.model.dto.UserDTO;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${num}")
    private Integer num;

    @Override
    public ModelMessage<UserDTO> userById(Long userId) {
        logger.error("*** invoker getUserById ****");
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setCode(200);
        model.setMessage("ok");
        model.setData(UserDTO.newBuilder().withAge(1).withUserName("yang").withSex(3).build());
        return model;
    }

    @Override
    public ModelMessage<UserDTO> byZero(Long userId) {
        logger.error("************ byZero: {}", userId);
        int i = 1 / 0;
//        if (failover == 1) {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setCode(200);
        model.setMessage("failover");
        model.setData(UserDTO.newBuilder().withAge(1).withUserName("failover").withSex(3).build());
        return model;
    }

    @Override
    public ModelMessage<UserDTO> timeOut(Long userId) {
        logger.error("************ byZero: {}", userId);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setCode(200);
        model.setMessage("failover");
        model.setData(UserDTO.newBuilder().withAge(1).withUserName("failover").withSex(3).build());
        return model;
    }

    @Override
    public ModelMessage<UserDTO> randomTimeOut(Long userId) {
        logger.error("************ randomTimeOut: {}", userId);

        if (num == 1) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setCode(200);
        model.setMessage("failover");
        model.setData(UserDTO.newBuilder().withAge(1).withUserName("failover").withSex(3).build());
        return model;
    }
}
