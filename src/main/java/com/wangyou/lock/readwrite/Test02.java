package com.wangyou.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 写写互斥
 *
 * @author 王游
 * @date 2021/3/5 14:43
 */
public class Test02 {
    static class Service{
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        /**
         * Thread-0获得写锁，开始修改数据的时间--1614927264280
         * Thread-0修改数据结束的时间--1614927266283
         * Thread-2获得写锁，开始修改数据的时间--1614927266283
         * Thread-2修改数据结束的时间--1614927268284
         * Thread-1获得写锁，开始修改数据的时间--1614927268284
         * Thread-1修改数据结束的时间--1614927270284
         * Thread-4获得写锁，开始修改数据的时间--1614927270284
         * Thread-4修改数据结束的时间--1614927272285
         * Thread-3获得写锁，开始修改数据的时间--1614927272285
         * Thread-3修改数据结束的时间--1614927274285
         */
        public void write(){
            readWriteLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName()
                        + "获得写锁，开始修改数据的时间--" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName()
                        + "修改数据结束的时间--" + System.currentTimeMillis());
                readWriteLock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();

        int threadNum = 5;
        for (int i = 0; i < threadNum; i++){
            new Thread(service::write).start();
        }
    }
}
