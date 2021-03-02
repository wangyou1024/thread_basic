package com.wangyou.threadmethod.isalive;

/**
 * @author 王游
 * @date 2021/2/27 9:14
 */
public class SubThread extends Thread{

    @Override
    public void run() {
        System.out.println("run方法，isAlive = " + this.isAlive());
    }
}
