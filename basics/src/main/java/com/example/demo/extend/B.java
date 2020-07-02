package com.example.demo.extend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/01 20:56
 **/
public class B extends BaseTest<BModel> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public B(String name, Long time) {
        super(name, time);
    }

    public void sss() {
        logger.info("B, sss开始");
        writeLock.lock();
        try {
            logger.info("A, sss进入锁, 修改前first参数:{}", first);
            first = "B";
            sFirst = "B";
            list.add(new BModel(2));
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            logger.info("B, sss执行完毕,修改后first参数:{},sFirst:{}", first, sFirst);
        }
    }
}
