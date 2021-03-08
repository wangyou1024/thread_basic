package com.wangyou.threadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author 王游
 * @date 2021/3/6 15:35
 */
public class Test04 {
    public static void main(String[] args) {
        // 定义任务
        Runnable r = () -> {
            int num = new Random().nextInt(5);
            System.out.println(Thread.currentThread().getId()
                    + " -- " + System.currentTimeMillis() + "开始睡眠" + num + "秒");
            try {
                TimeUnit.SECONDS.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // 创建线程池,使用自定义线程工厂
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), r1 -> {
            Thread thread = new Thread(r1);
            // 设置为守护线程，当主线程运行结束，线程池中的线程会自动退出
            thread.setDaemon(true);
            System.out.println("创建了线程：" + thread);
            return thread;
        });

        // 向线程池提交若干任务，当任务大于5时，线程池执行默认的拒绝策略，抛出异常
        int threadNum = 5;
        for (int i = 0; i < threadNum; i++) {
            threadPoolExecutor.submit(r);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
