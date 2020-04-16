package com.example.observer;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 19:38
 **/
public class XiaoHong extends Observer {

    public XiaoHong(Subject subject, String name) {
        this.subject = subject;
        this.observerName = name;
        this.subject.register(this);
    }

    @Override
    public void update() {
        System.out.println(observerName + "收到了通知......");
    }



}
