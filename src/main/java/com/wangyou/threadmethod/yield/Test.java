package com.wangyou.threadmethod.yield;

/**
 * @author 王游
 * @date 2021/2/27 9:46
 */
public class Test {
    public static void main(String[] args) {
        // 子线程计算累加
        SubThread t1 = new SubThread();
        t1.start();
        // main线程中计算累加
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <= 1e+6; i++){
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("main线程用时：" + (end - begin));
    }
}
