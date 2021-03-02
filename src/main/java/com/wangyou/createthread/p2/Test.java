package com.wangyou.createthread.p2;

/**
 * @author 王游
 * @date 2021/2/26 16:57
 */
public class Test {
    public static void main(String[] args) {
        // 3) 创建Runnable接口的实现类对象
        MyRunnable runnable = new MyRunnable();
        // 4) 创建线程对象，也可以传递匿名内部类对象
        Thread thread = new Thread(runnable);
        // 5) 开启线程
        thread.start();

        // 当前是main线程
        int num = 100;
        for (int i = 1; i <= num; i++){
            System.out.println("main -> " + i);
        }
    }
}
