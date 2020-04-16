package com.example.observer;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 19:41
 **/
public class Test {

    public static void main(String[] args) {
        Subject subject = new Subject();
        XiaoMing xiaoMing = new XiaoMing(subject, "小明");
        XiaoHong xiaoHong = new XiaoHong(subject, "小红");
        System.out.println("***************** 发送通知 ************");
        subject.notifyAllObservers();
        subject.unRegister(xiaoMing);
        System.out.println("***************** 发送通知 ************");
        subject.notifyAllObservers();
        subject.unRegister(xiaoHong);
        subject.notifyAllObservers();

    }
}
