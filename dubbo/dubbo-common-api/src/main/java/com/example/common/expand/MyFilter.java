package com.example.common.expand;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/31 14:31
 **/
@Activate(group = {CommonConstants.CONSUMER})
public class MyFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ExecutorService executorService =  new ThreadPoolExecutor(5, 30,
            60, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(200),
            new BasicThreadFactory.Builder()
                    .namingPattern(Joiner.on("-")
                            .join("my-filter-handle", "%s"))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        logger.error("*** 自定义扩展接口 start, 调用方法:{}#{}, 传递参数:{}*****",
                invocation.getServiceName(), invocation.getMethodName(), invocation.getArguments());
        Result result = invoker.invoke(invocation);


        if ((InvokeMode.SYNC == ((RpcInvocation) invocation).getInvokeMode())) {
            logger.error("*** 自定义扩展接口 end, 返回体:{} *****", result.getValue());
        } else {
            AsyncRpcResult asyncRpcResult = (AsyncRpcResult) result;
            asyncRpcResult
                    .getResponseFuture()
                    .whenCompleteAsync((appResponse, throwable) ->
                            logger.error("*** 自定义扩展接口 end, 返回体:{} *****",
                                    appResponse.getValue()), executorService);
        }
        return result;
    }
}
