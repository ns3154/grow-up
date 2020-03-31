package com.example.common.expand;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/31 14:31
 **/
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
public class MyFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        logger.info("*** 自定义扩展接口 start, 调用方法:{}#{}, 传递参数:{}*****",
                invocation.getServiceName(), invocation.getMethodName(), invocation.getArguments());
        Result result = invoker.invoke(invocation);
        logger.info("*** 自定义扩展接口 end *****");
        return result;
    }
}
