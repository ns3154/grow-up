package com.example;



public class MemorySync {
    static class ThreadDemo implements Runnable {

        private boolean flag = false;

        @Override
        public void run() {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("set flag:" + flag);
        }
        public boolean getFlag() {
            return flag;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo, "t1").start();
        new Thread(new Runnable() {
            @Override public void run() {
                while (true) {
                    if (threadDemo.getFlag()) {
                        System.out.println("end task");
                        break;
                    }
                }
            }
        }).start();
    }
}


