package com.example.demo.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/06/28 17:00
 **/
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 1 轮，每次 1s
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Benchmark)
@Threads(100)
public class TryCatchTest {

    private static final int FOR_SIZE = 1000; // 循环次数

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试 要导入的测试类
        Options opt = new OptionsBuilder().include(TryCatchTest.class.getSimpleName())
                .build();
        // 执行测试
        new Runner(opt).run();
    }

    @Benchmark
    public int innerForeach() {
        int count = 0;
        for (int i = 0; i < FOR_SIZE; i++) {
            try {
                if (i == FOR_SIZE) {
                    throw new Exception("new Exception");
                }
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Benchmark
    public int outerForeach() {
        int count = 0;
        try {
            for (int i = 0; i < FOR_SIZE; i++) {
                if (i == FOR_SIZE) {
                    throw new Exception("new Exception");
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
