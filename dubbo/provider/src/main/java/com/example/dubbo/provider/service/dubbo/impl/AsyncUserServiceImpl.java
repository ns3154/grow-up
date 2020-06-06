package com.example.dubbo.provider.service.dubbo.impl;

import com.example.common.api.AsyncDubboServiceTestApi;
import com.example.common.model.ModelMessage;
import com.example.common.model.dto.UserDTO;
import com.example.dubbo.provider.bean.UserBean;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.concurrent.*;

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

    @Resource
    private UserBean userBean;

    private ExecutorService executorService =  new ThreadPoolExecutor(5, 30,
            60, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(200),
            new BasicThreadFactory.Builder()
                    .namingPattern(Joiner.on("-")
                            .join("my-dubbo-handle", "%s"))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public CompletableFuture<ModelMessage<UserDTO>> getUserIdByAsync(Long userId) {

        logger.error("*** invoker getUserIdByAsync start ****");
        if (null != userId) {
            userBean.test(String.valueOf(userId));
        }
        try {
            Thread.sleep(10001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ModelMessage<UserDTO> model = new ModelMessage<>();
        model.setCode(200);
        model.setMessage("ok");
        model.setData(UserDTO.newBuilder().withAge(1).withUserName("yang").withSex(3).build());
        logger.error("*** invoker getUserIdByAsync end ****");
        return CompletableFuture.completedFuture(model);
    }

    @Override
    public CompletableFuture<Void>  noVoidAsync(Long userId) {
        logger.error("*** invoker noVoidAsync start ****");
        try {
            Thread.sleep(10001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.error("*** invoker noVoidAsync end ****");
        return new CompletableFuture<>();
    }

    @Override
    public CompletableFuture<ModelMessage<UserDTO>> getUserByAsyncForMyThread(Long userId) {

        logger.error("*** invoker getUserByAsyncForMyThread start ****");
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ModelMessage<UserDTO> model = new ModelMessage<>();
            model.setCode(200);
            model.setMessage("ok");
            model.setData(UserDTO.newBuilder().withAge(1).withUserName("yang").withSex(3).build());
            logger.error("*** invoker getUserByAsyncForMyThread end ****");
            return model;
        }, executorService);
    }
}
