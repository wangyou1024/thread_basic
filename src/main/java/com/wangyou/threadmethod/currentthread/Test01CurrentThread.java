package com.wangyou.threadmethod.currentthread;

/**
 * 测试当前线程
 * @author 王游
 * @date 2021/2/27 8:45
 */
public class Test01CurrentThread {
    public static void main(String[] args) {
        System.out.println("mian方法中打印当前线程：" + Thread.currentThread().getName());

        // 创建子线程，在main线程中调用构造方法，所以构造方法的当前线程是main线程
        SubThread1 t1 = new SubThread1();
        // 启动子线程，子线程调用run()方法，所以run()方法的当前线程是Thread-0子线程
        t1.start();
        // 在main线程中直接调用run()方法，此时run()方法的当前线程是main线程
        t1.run();
    }
}
