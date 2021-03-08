package com.wangyou.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的计划任务
 *
 * @author 王游
 * @date 2021/3/6 9:52
 */
public class Test02 {
    public static void main(String[] args) {
        int threadNum = 10;
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(threadNum);

        // 在延迟2秒后执行任务
        scheduledExecutorService.schedule(()->{
            System.out.println(Thread.currentThread().getId() + " -- " + System.currentTimeMillis());
        }, 2, TimeUnit.SECONDS);

        // 以固定的频率执行任务，开启任务的时间是固定的：在3秒后执行任务，以后每隔2秒重新执行一次
        scheduledExecutorService.scheduleAtFixedRate(
                ()-> {
                    System.out.println(Thread.currentThread().getId() + " -- " + System.currentTimeMillis());
                    try {
                        // 如果任务执行时长超过了时间时隔，则任务完成后立即开启下一个任务
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                3, 2, TimeUnit.SECONDS);

        // 在上次任务结束后，在固定延迟后再次执行该任务，不管执行任务耗时多长，总是在任务结束后的固定时间间隔后开启
        scheduledExecutorService.scheduleWithFixedDelay(
                ()-> {
                    System.out.println(Thread.currentThread().getId() + " -- " + System.currentTimeMillis());
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                3, 2, TimeUnit.SECONDS);
    }
}
