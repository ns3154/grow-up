package com.example.observer;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 19:36
 **/
public class XiaoMing extends Observer {

    public XiaoMing(Subject subject, String name) {
        this.subject = subject;
        this.observerName = name;
        this.subject.register(this);
    }

    @Override
    public void update() {
        System.out.println(observerName + "收到了通知......");
    }


}
