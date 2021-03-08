package com.wangyou.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 基本使用
 *
 * @author 王游
 * @date 2021/3/6 9:42
 */
public class Test01 {
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        // 向线程池中提交18个任务
        int taskNum = 18;
        for (int i = 0; i < taskNum; i++){
            fixedThreadPool.execute(()->{
                System.out.println(Thread.currentThread().getId() + "编号的任务在执行，开始时间：" + System.currentTimeMillis());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
