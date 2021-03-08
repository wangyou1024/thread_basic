package com.wangyou.wait;

/**
 * @author 王游
 * @date 2021/3/3 10:16
 */
public class Test03 {
    public static void main(String[] args) throws InterruptedException {
        SubThread subThread = new SubThread();
        subThread.start();

        Thread.sleep(2000);
        subThread.interrupt();
    }

    private static final Object LOCK = new Object();

    static class SubThread extends Thread {
        @Override
        public void run() {
            synchronized (LOCK) {
                System.out.println("begin wait...");
                try {
                    LOCK.wait();
                    System.out.println("end wait...");
                } catch (InterruptedException e) {
                    System.out.println("wait等待被中断");
                }
            }
        }
    }
}
