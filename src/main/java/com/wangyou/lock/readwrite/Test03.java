package com.wangyou.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写互斥
 *
 * @author 王游
 * @date 2021/3/5 14:43
 */
public class Test03 {
    static class Service{
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

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

        public void read(){
            readWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName()
                        + "获得读锁，开始读取数据的时间--" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName()
                        + "读取数据结果的时间--" + System.currentTimeMillis());
                readWriteLock.readLock().unlock();
            }
        }
    }

    /**
     * Thread-0获得读锁，开始读取数据的时间--1614927579907
     * Thread-0读取数据结果的时间--1614927581910
     * Thread-1获得写锁，开始修改数据的时间--1614927581910
     * Thread-1修改数据结束的时间--1614927583910
     */
    public static void main(String[] args) {
        Service service = new Service();

        new Thread(service::read).start();
        new Thread(service::write).start();
    }
}
