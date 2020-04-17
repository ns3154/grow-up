package com.example.lifecycle;

import com.example.utils.TrackUtils;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/17 16:52
 **/
@Component
public class MySmartLifecycle implements SmartLifecycle {

    private volatile boolean runningFlag = false;

    /**
     * bean初始化完毕后，该方法会被执行
     */
    @Override
    public void start() {
        TrackUtils.printTrack("do start");
        runningFlag = true;
        System.out.println("MySmartLifecycle start....");
    }

    /**
     * 容器关闭后：
     * spring容器发现当前对象实现了SmartLifecycle，就调用stop(Runnable)，
     * 如果只是实现了Lifecycle，就调用stop()
     */
    @Override
    public void stop() {
        runningFlag = false;
        TrackUtils.printTrack("do stop....");
        System.out.println("MySmartLifecycle stop....");
    }

    /**
     * 当前状态
     * @return
     */
    @Override
    public boolean isRunning() {
        System.out.println("MySmartLifecycle isRunning....");
        return runningFlag;
    }

    /**
     * start方法被执行前先看此方法返回值，
     * 返回false就不执行start方法了
     * @return
     */
    @Override
    public boolean isAutoStartup() {
        System.out.println("MySmartLifecycle isAutoStartup....");
        return true;
    }

    /**
     * 容器关闭后：
     * spring容器发现当前对象实现了SmartLifecycle，就调用stop(Runnable)，
     * 如果只是实现了Lifecycle，就调用stop()
     * @param callback
     */
    @Override
    public void stop(Runnable callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                TrackUtils.printTrack("do stop with callback param");
                //设置为false，表示已经不在执行中了
                runningFlag = false;
                //callback中有个CountDownLatch实例，总数是SmartLifecycle对象的数量，
                //此方法被回调时CountDownLatch实例才会减一，初始化容器的线程一直在wait中；
                callback.run();
            }
        }).start();
        System.out.println("MySmartLifecycle callback stop....");
    }

    /**
     * 返回值决定start方法在众多Lifecycle实现类中的执行顺序(stop也是)
     * @return
     */
    @Override
    public int getPhase() {
        System.out.println("MySmartLifecycle getPhase....");
        return 0;
    }
}
