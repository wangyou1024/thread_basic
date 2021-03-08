package com.wangyou.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王游
 * @date 2021/3/4 16:47
 */
public class Test02 {
    static class Service{
        private Lock lock = new ReentrantLock();
        public void serviceMethod() {
            // 被锁定后，即使调用线程的interrupt()方法也没有真正中断
            // lock.lock();
            try {
                lock.lockInterruptibly();
                try {

                    System.out.println(Thread.currentThread().getName() + " -- begin lock");

                    for (int i = 0; i < Integer.MAX_VALUE; i++){
                        new StringBuilder();
                    }

                    System.out.println(Thread.currentThread().getName() + " -- end lock");
                }finally {
                    lock.unlock(); // if lock.isHeldByCurrentThread
                    System.out.println(Thread.currentThread().getName() + " ** 释放锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service s = new Service();
        Runnable r = s::serviceMethod;

        Thread t1 = new Thread(r);
        t1.start();

        Thread.sleep(50);

        Thread t2 = new Thread(r);
        t2.start();
        Thread.sleep(50);
        t2.interrupt();
    }
}
