package com.wangyou.threadmethod.currentthread;

/**
 * 定义一个线程类
 * @author 王游
 * @date 2021/2/27 8:46
 */
public class SubThread1 extends Thread{

    public SubThread1(){
        System.out.println("构造方法打印当前线程名称：" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run方法打印当前线程名称：" + Thread.currentThread().getName());
    }
}
