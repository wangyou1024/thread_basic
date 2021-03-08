package com.wangyou.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王游
 * @date 2021/3/3 21:08
 */
public class Test01 {
    /**
     * 定义显示锁
     */
    static Lock lock = new ReentrantLock();

    /**
     * 定义方法
     */
    public static void sm(){
        //先获得锁
        lock.lock();
        try{
            int num = 100;
            for (int i = 0; i < num; i++){
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable runnable = Test01::sm;
        // 启动三个线程
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
