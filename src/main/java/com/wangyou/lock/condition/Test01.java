package com.wangyou.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王游
 * @date 2021/3/4 20:16
 */
public class Test01 {

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    static class SubThread extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("method lock");
                condition.await();
                System.out.println("method await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("method unlock");
            }
        }
    }

    public static void main(String[] args) {
        SubThread t = new SubThread();
        t.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 主线程想要唤醒子线程，需要先持有锁，否则会出现异常
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
