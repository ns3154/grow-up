package com.example.demo.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/15 14:13
 **/
public class QueueTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void linked() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        queue.add("a");
        boolean sss = queue.offer("b");
        logger.error("offer:{}", sss);

        try {
            queue.put("c");
            String take = queue.take();
            logger.error("take:{}", take);
            String poll = queue.poll();
            logger.error("poll:{}", poll);
            logger.error("queue:{}", queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void LinkedTranferQueue() {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        for (int i = 0; i < 10;i++) {
            queue.put("ss" + i);
        }

        System.out.println(111);
    }
}
