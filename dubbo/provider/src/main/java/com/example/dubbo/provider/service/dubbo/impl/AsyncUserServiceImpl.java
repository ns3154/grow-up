package com.example.dubbo.provider.service.dubbo.impl;

import com.example.common.api.AsyncDubboServiceTestApi;
import com.example.common.model.ModelMessage;
import com.example.common.model.dto.UserDTO;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/26 11:29
 **/
@Service
public class AsyncUserServiceImpl implements AsyncDubboServiceTestApi {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public CompletableFuture<ModelMessage<UserDTO>> getUserIdByAsync(Long userId) {

        logger.info("*** invoker getUserIdByAsync start ****");
        CompletableFuture.runAsync(() ->{

        });
        try {
            Thread.sleep(10001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setCode(200);
        model.setMessage("ok");
        model.setData(UserDTO.newBuilder().withAge(1).withUserName("yang").withSex(3).build());
        logger.info("*** invoker getUserIdByAsync end ****");
        return CompletableFuture.completedFuture(model);
    }

    @Override
    public CompletableFuture<Void>  noVoidAsync(Long userId) {
        logger.info("*** invoker noVoidAsync start ****");
        try {
            Thread.sleep(10001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("*** invoker noVoidAsync end ****");
        return new CompletableFuture<>();
    }
}
