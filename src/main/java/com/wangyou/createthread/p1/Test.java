package com.wangyou.createthread.p1;

/**
 * @author 王游
 * @date 2021/2/26 15:57
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("JVM启动main线程，main线程执行main方法");
        // 3) 创建子线程对象
        MyThread thread = new MyThread();
        // 4) 启动线程
        thread.start();
        /* 调用线程的start()方法来启动线程，启动线程的实质是请求JVM运行
        相应的线程，这个线程具体在什么时候运行由线程高度器（Scheduler）决定。
        注意：1. start()方法调用结束并不意味着子线程开始运行；
             2. 新开启的线程会执行run()方法；
             3. 如果开启了多个线程，start()调用的顺序并不一定就是线程启动的顺序；
             4. 多线程运行结果与代码执行或者调用顺序无关。
         */
        System.out.println("main线程后面其他的代码……");
    }
}
