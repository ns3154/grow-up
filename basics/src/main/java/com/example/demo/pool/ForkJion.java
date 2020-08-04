package com.example.demo.pool;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/04 17:01
 **/
public class ForkJion {

    //Runtime.getRuntime().availableProcessors()

    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJion.class);

    ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
            ForkJoinPool.defaultForkJoinWorkerThreadFactory, (t, e) -> {
        LOGGER.error("thread:{}, error:", t.getName(), e);
    }, false);


    @Test
    public void test() {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
        Long invoke = forkJoinPool.invoke(new SumTask(numbers, 0, numbers.length - 1));
        System.out.println(invoke);


    }

    class SumTask extends RecursiveTask<Long> {
        private final long[] numbers;
        private final int from;
        private final int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {

            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            } else {
                int middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle + 1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }
}
