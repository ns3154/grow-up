package com.example.dubbo.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.api.AsyncDubboServiceTestApi;
import com.example.common.api.DubboTestServiceApi;
import com.example.common.model.ModelMessage;
import com.example.common.model.dto.UserDTO;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 11:13
 **/
@RestController
@RequestMapping("consumer")
public class DubboController {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Reference(mock = "com.example.dubbo.consumer.mock.DubboServiceTestMock") 先调用远程 如果 失败 在执行 mock
//    @Reference(mock = "force:com.example.dubbo.consumer.mock.DubboServiceTestMock") // 直接强制执行 mock
    @Reference(methods = {@Method(name = "getUserById", cache = "lru")})
    private DubboTestServiceApi dubboTestServiceApi;

    @Reference(methods = {@Method(name = "noVoidAsync", async = true, isReturn = false)})
    private AsyncDubboServiceTestApi asyncDubboServiceTestApi;


    @GetMapping("getUserById")
    public ModelMessage<UserDTO> getUserById(Long userId) {
        return dubboTestServiceApi.getUserById(userId);
    }

    @GetMapping("byZero")
    public ModelMessage<UserDTO> byZero(Long userId) {
        ModelMessage<UserDTO> failover = dubboTestServiceApi.byZero(userId);
        logger.info("*** byZero result:{}", JSON.toJSONString(failover));
        return failover;
    }

    @GetMapping("timeOut")
    public ModelMessage<UserDTO> timeOut(Long userId) {
        ModelMessage<UserDTO> failover = dubboTestServiceApi.timeOut(userId);
        logger.info("*** timeOut result:{}", JSON.toJSONString(failover));
        return failover;
    }

    @GetMapping("randomTimeOut")
    public ModelMessage<UserDTO> randomTimeOut(Long userId) {
        ModelMessage<UserDTO> failover = dubboTestServiceApi.randomTimeOut(userId);
        logger.info("*** randomTimeOut result:{}", JSON.toJSONString(failover));
        return failover;
    }

    @GetMapping("getUserByAsync")
    public ModelMessage<UserDTO> getUserByAsync(Long userId) {
        CompletableFuture<ModelMessage<UserDTO>> userIdByAsync = asyncDubboServiceTestApi.getUserIdByAsync(userId);
        logger.info("**** getUserByAsync 执行完成 *********");
        try {
            ModelMessage<UserDTO> userDTOModelMessage = userIdByAsync.get();
            logger.info("*** getUserByAsync result:{}", JSON.toJSONString(userDTOModelMessage));
            return userDTOModelMessage;
        } catch (InterruptedException e) {
            logger.error("*** error : {}", e);
        } catch (ExecutionException e) {
            logger.error("*** error : {}", e);
        }

        return null;
    }

    @GetMapping("noVoidByAsync")
    public ModelMessage<UserDTO> noVoidByAsync(Long userId) {
        asyncDubboServiceTestApi.noVoidAsync(userId);
        logger.info("*** noVoidByAsync ok");
        return new ModelMessage<>();

    }

    @GetMapping("getUserByAsyncForMyThread")
    public ModelMessage<UserDTO> getUserByAsyncForMyThread(Long userId) throws ExecutionException, InterruptedException {
        CompletableFuture<ModelMessage<UserDTO>> userByAsyncForMyThread =
                asyncDubboServiceTestApi.getUserByAsyncForMyThread(userId);
        logger.info("*** getUserByAsyncForMyThread consumer start");
        return userByAsyncForMyThread.get();
    }


}
