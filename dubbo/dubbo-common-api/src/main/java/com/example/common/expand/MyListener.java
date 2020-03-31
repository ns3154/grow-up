package com.example.common.expand;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.InvokerListener;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  当有服务引用时，触发该事件。
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/31 15:15
 **/
@Activate(group = {CommonConstants.PROVIDER})
public class MyListener implements InvokerListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        logger.info("********** referred interface:{}, url:{} ******", invoker.getInterface(), invoker.getUrl());
    }

    @Override
    public void destroyed(Invoker<?> invoker) {
        logger.info("********** destroyed interface:{}, url:{} ******", invoker.getInterface(), invoker.getUrl());
    }
}
