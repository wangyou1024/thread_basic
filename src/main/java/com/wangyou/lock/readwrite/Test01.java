package com.wangyou.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读读共享
 *
 * @author 王游
 * @date 2021/3/5 14:43
 */
public class Test01 {
    static class Service{
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        /**
         * 输出：
         * Thread-0获得读锁，开始读取数据的时间--1614927014969
         * Thread-3获得读锁，开始读取数据的时间--1614927014970
         * Thread-4获得读锁，开始读取数据的时间--1614927014970
         * Thread-2获得读锁，开始读取数据的时间--1614927014970
         * Thread-1获得读锁，开始读取数据的时间--1614927014970
         */
        public void read(){
            readWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName()
                        + "获得读锁，开始读取数据的时间--" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();

        int threadNum = 5;
        for (int i = 0; i < threadNum; i++){
            new Thread(service::read).start();
        }
    }
}
