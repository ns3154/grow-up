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
public class A extends BaseTest<AModel> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public A(String name, Long time) {
        super(name, time);
    }

    public void sss() {
        logger.info("A, sss开始");
        writeLock.lock();
        try {
            logger.info("A, sss进入锁, 修改前first参数:{}", first);
            Thread.sleep(2000);
            first = "A";
            sFirst = "A";
            list.add(new AModel("a"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            logger.info("A, sss执行完毕,修改后first参数:{},sFirst:{}", first, sFirst);
        }
    }



}
