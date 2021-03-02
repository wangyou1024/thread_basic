package com.wangyou.intrinsiclock;

/**
 * 死锁：在多线程中，同步时可能需要使用多个锁，如果获得锁的顺序不一致，可能导致死锁
 * @author 王游
 * @date 2021/3/1 16:43
 */
public class Test04 {

    public static void main(String[] args) {
        SubThread t1 = new SubThread();
        t1.setName(SubThread.thread1);
        t1.start();

        SubThread t2 = new SubThread();
        t2.setName(SubThread.thread2);
        t2.start();

    }

    static class SubThread extends Thread{
        private static final Object lock1 = new Object();
        private static final Object lock2 = new Object();

        private static String thread1 = "a";
        private static String thread2 = "b";

        @Override
        public void run() {
            if (thread1.equals(Thread.currentThread().getName())){
                synchronized (lock1) {
                    System.out.println("a线程获得了lock1锁，还需要获得lock2锁");
                    synchronized (lock2){
                        System.out.println("a线程获得lock1后又获得了lock2,可以想干任何想干的事");
                    }
                }
            }
            if (thread2.equals(Thread.currentThread().getName())){
                synchronized (lock2) {
                    System.out.println("b线程获得了lock2锁，还需要获得lock1锁");
                    synchronized (lock1){
                        System.out.println("b线程获得lock2后又获得了lock1,可以想干任何想干的事");
                    }
                }
            }
        }
    }
}
