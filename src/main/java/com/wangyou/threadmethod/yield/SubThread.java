package com.wangyou.threadmethod.yield;

/**
 * @author 王游
 * @date 2021/2/27 9:46
 */
public class SubThread extends Thread{
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <= 1e+6; i++){
            sum += i;
            Thread.yield(); //线程让步
        }
        long end = System.currentTimeMillis();
        System.out.println("子线程用时：" + (end - begin));
    }
}
