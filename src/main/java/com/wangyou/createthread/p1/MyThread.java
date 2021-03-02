package com.wangyou.createthread.p1;

/**
 * @author 百忧
 * @date 2021/2/26 10:57
 * 1) 定义类继承Thread
 */

public class MyThread extends Thread{

    /**
     * 2) 重写Thread父类中的run()
     * run()方法体中的代码是子线程要执行的任务
      */
    @Override
    public void run() {
        System.out.println("这是子线程打印的内容");
    }
}
