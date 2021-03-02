package com.wangyou.threadmethod.isalive;

/**
 * @author 王游
 * @date 2021/2/27 9:15
 */
public class Test {
    public static void main(String[] args) {
        SubThread t1 = new SubThread();
        System.out.println("begin==" + t1.isAlive());
        t1.start();
        System.out.println("end==" + t1.isAlive());
    }
}
