package com.example.observer;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 19:29
 **/
public class Subject {

    private final Set<Observer> observers = new HashSet<>();

    private final Lock lock = new ReentrantLock(true);

    public void register(Observer o) {
        lock.lock();
        try {
            boolean add = observers.add(o);
            if (add) {
                System.out.println(o.observerName + "加入监听....");
            }
        } finally {
            lock.unlock();
        }
    }

    public void unRegister(Observer o) {
        lock.lock();
        try {
            boolean remove = observers.remove(o);
            if (remove) {
                System.out.println(o.observerName + "离开了监听....");
            }
        } finally {
            lock.unlock();
        }
    }

    public void notifyAllObservers() {
        observers.forEach(Observer::update);
    }


}
