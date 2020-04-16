package com.example.observer;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 19:28
 **/
public abstract class Observer {

    public Subject subject;

    public String observerName;

    public abstract void update();
}
