package com.wangyou.volatilekw;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 王游
 * @date 2021/3/2 11:18
 */
public class Test03 {
    public static void main(String[] args) throws InterruptedException {
        int threadNum = 100;
        for (int i = 0; i < threadNum; i++){
            new MyThread().start();
        }
        Thread.sleep(1000);
        System.out.println(MyThread.count.get());
    }

    static class MyThread extends Thread {

        private static AtomicInteger count = new AtomicInteger();

        public static void addCount(){
            int sum = 1000;
            for (int i = 0; i < sum; i++){
                count.getAndIncrement();
            }
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }

        @Override
        public void run() {
            addCount();
        }
    }
}
