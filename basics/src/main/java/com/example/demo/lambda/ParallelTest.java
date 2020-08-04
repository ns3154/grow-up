package com.example.demo.lambda;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/04 15:50
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelTest {

    private static final long N = 10_000_000L;

    @Benchmark
    public long sequentialSum() {
//        return Stream.iterate(1L, i -> i + 1).limit(N).reduce(0L, Long::sum);
        return LongStream.rangeClosed(1L, N).parallel().reduce(0L, Long::sum);
    }

//    @TearDown(Level.Invocation)
//    public void tearDown() {
//        System.gc();
//    }

//    @Test
//    public void sum() {
//        Long reduce = Stream.iterate(1L, i -> {
//            logger.info(String.valueOf(i));
//            return i + 1;
//        }).limit(10).parallel().reduce(0L, Long::sum);
//        logger.error(reduce.toString());
//
//    }
}
