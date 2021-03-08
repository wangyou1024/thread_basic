package com.wangyou.threadpool;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @author 王游
 * @date 2021/3/6 20:37
 */
public class Test06 {
    /**
     * 计算数列的和，需要返回结果，可以定义任务继承RecursiveTask
     */
    private static class CountTask extends RecursiveTask<Long> {
        /**
         * 当个任务量阀值
         */
        private static final int THRESHOLD = 10000;
        /**
         * 分解任务数
         */
        private static final int TASK_NUM = 100;

        private long start;
        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            if (end - start < THRESHOLD) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                // 拆分任务
                long step = (start + end) / 100;
                ArrayList<CountTask> subTaskList = new ArrayList<>();
                long pos = start;
                for (int i = 0; i < TASK_NUM; i++) {
                    long lastOne = pos + step;
                    if (lastOne > end) {
                        lastOne = end;
                    }
                    CountTask task = new CountTask(pos, lastOne);
                    subTaskList.add(task);
                    // 调用fork()提交子任务
                    task.fork();
                    pos += step + 1;
                }

                // 合并结果
                for (CountTask task :
                        subTaskList) {
                    sum += task.join();
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0L, 200000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        Long res = null;
        try {
            res = result.get();
            System.out.println("计算数列结果为：" + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
