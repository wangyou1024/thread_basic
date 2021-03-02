package com.wangyou.createthread.p2;

/**
 * 当线程类已经有父类，就不能用继承Thread类的形式创建线程，可以使用实现Runnable接口的形式
 * 1) 定义类实现Runnable接口
 * @author 王游
 * @date 2021/2/26 17:01
 */
public class MyRunnable implements Runnable{
    /**
     * 2) 重写Runnable接口中的抽象方法run()，run()方法就是子线程要执行的代码
     */
    @Override
    public void run() {
        int num = 100;
        for (int i = 1; i <= num; i++){
            System.out.println("sub thread --> " + i);
        }
    }
}
