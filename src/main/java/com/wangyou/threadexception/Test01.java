package com.wangyou.threadexception;

/**
 * @author 王游
 * @date 2021/3/5 19:05
 */
public class Test01 {
    /**
     * 输出：
     * Thread-0开始运行
     * Thread-1线程产生了异常：null
     * Thread-0线程产生了异常：/ by zero
     */
    public static void main(String[] args) {
        // 1) 设置线程全局回调接口
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // t参数接收发生异常的线程，e就是该线程中的异常
                System.out.println(t.getName() + "线程产生了异常：" + e.getMessage());
            }
        });

        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "开始运行");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(12/0);
        });
        t1.start();

        new Thread(()->{
            String text = null;
            System.out.println(text.length());
        }).start();
    }
}
