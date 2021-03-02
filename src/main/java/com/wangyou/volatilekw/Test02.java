package com.wangyou.volatilekw;

/**
 * @author 王游
 * @date 2021/3/2 11:18
 */
public class Test02 {
    public static void main(String[] args) {
        int threadNum = 100;
        for (int i = 0; i < threadNum; i++){
            // count != threadNum * sum;
            new MyThread().start();
        }
    }

    static class MyThread extends Thread {
        // volatile关键字仅仅表示所有线程从主内存中读取count变量的值
        volatile public static int count;

        // 这段代码不是线程安全的，需要添加synchronized关键字，它保证原子性的同时，还保证了可见性，也就不需要volatile
        public static void addCount(){
            int sum = 1000;
            for (int i = 0; i < sum; i++){
                // count++不是原子操作
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }

        @Override
        public void run() {
            addCount();
        }
    }
}
