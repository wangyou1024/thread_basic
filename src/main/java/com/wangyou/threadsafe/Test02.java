package com.wangyou.threadsafe;

import java.util.Random;

/**
 * @author 王游
 * @date 2021/2/27 16:38
 */
public class Test02 {
    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        new Thread(task).start();

        Thread.sleep(1000);
        // 主线程1秒后取消主线程，可能会出现main线程对toCancel做了修改子线程看不到的情况
        task.cancel();
    }

    static class MyTask implements Runnable{

        private boolean toCancel = false;

        @Override
        public void run() {
            while (!toCancel){
                if (doSomething()){
                }
            }
            if (toCancel){
                System.out.println("任务被取消");
            } else {
                System.out.println("任务正常结束");
            }
        }

        private boolean doSomething(){
            System.out.println("执行某个任务……");
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        public void cancel(){
            toCancel = true;
            System.out.println("收到取消线程的消息");
        }
    }
}
