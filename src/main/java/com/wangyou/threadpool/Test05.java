package com.wangyou.threadpool;

import java.util.concurrent.*;

/**
 * @author 王游
 * @date 2021/3/6 17:10
 */
public class Test05 {

    private static class MyTask implements Runnable {
        private String name;

        public MyTask(String name){
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + "任务正在被线程" + Thread.currentThread().getId() + "执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 定义扩展线程池，可以定义线程池继承ThreadPoolExecutor，在子类中重写beforeExecute()/afterExecute()方法
        // 也可以直接使用ThreadPoolExecutor的内部类
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(t.getId() + "线程准备执行任务：" + ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(((MyTask)r).name + "任务执行完毕");
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        int taskNum = 5;
        for (int i = 0; i < taskNum; i++) {
            MyTask task = new MyTask("task-" + i);
            executorService.execute(task);
        }

        // 关闭线程池，仅仅是说不再接收新的任务
        executorService.shutdown();
    }
}
