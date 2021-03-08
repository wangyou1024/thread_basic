package com.wangyou.wait;

/**
 * @author 王游
 * @date 2021/3/3 9:59
 */
public class Test02 {
    public static void main(String[] args) throws InterruptedException {
        /*
        线程1开始等待：1614737148963
        线程2开始唤醒：1614737151964
        线程2结束唤醒：1614737152964
        线程1结束等待：1614737152964
         */
        String lock = "something";
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("线程1开始等待：" + System.currentTimeMillis());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1结束等待：" + System.currentTimeMillis());
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("线程2开始唤醒：" + System.currentTimeMillis());
                lock.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程2结束唤醒：" + System.currentTimeMillis());
            }
        });

        t1.start();

        Thread.sleep(3000);

        t2.start();
    }
}
