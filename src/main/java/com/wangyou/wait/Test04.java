package com.wangyou.wait;

/**
 * @author 王游
 * @date 2021/3/3 10:52
 */
public class Test04 {
    public static void main(String[] args) {
        Object lock = new Object();
        SubThread t1 = new SubThread(lock);
        SubThread t2 = new SubThread(lock);
        SubThread t3 = new SubThread(lock);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock){
            /* 只能唤醒一个线程，这种情况称为信号丢失
            t1 -- begin wait...
            t3 -- begin wait...
            t2 -- begin wait...
            t1 -- end wait...
             */
            // lock.notify();
            /*
            t1 -- begin wait...
            t3 -- begin wait...
            t2 -- begin wait...
            t2 -- end wait...
            t3 -- end wait...
            t1 -- end wait...
             */
            lock.notifyAll();
        }

    }

    static class SubThread extends Thread {
        private Object lock;

        public SubThread(Object lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock){
                try{
                    System.out.println(Thread.currentThread().getName() + " -- begin wait...");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + " -- end wait...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
