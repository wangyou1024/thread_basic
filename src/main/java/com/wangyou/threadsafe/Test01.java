package com.wangyou.threadsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 王游
 * @date 2021/2/27 16:06
 */
public class Test01 {
    public static void main(String[] args) {
        // MyInt myInt = new MyInt();
        MyIntThreadSafe myInt = new MyIntThreadSafe();
        int num = 2;
        for (int i = 1; i <= num; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(Thread.currentThread().getName()
                            + " -> " + myInt.getNum());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    static class MyInt {
        int num;

        public int getNum() {
            /*
                自增操作实现的步骤：
                    1. 读取num值
                    2. num自增
                    3. 把自增的值赋值给num变量
             */
            return num++;
        }
    }

    /**
     *  在Java中提供了一个线程安全的AtomicInteger类，保证了操作的原子性
     */
    static class MyIntThreadSafe {
        AtomicInteger num = new AtomicInteger();

        public int getNum() {
            return num.getAndIncrement();
        }
    }
}
