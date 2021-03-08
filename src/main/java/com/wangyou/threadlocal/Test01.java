package com.wangyou.threadlocal;

/**
 * @author 王游
 * @date 2021/3/3 20:34
 */
public class Test01 {
    static ThreadLocal threadLocal = new ThreadLocal();

    static class SubThread extends Thread {
        @Override
        public void run() {
            int num = 5;
            for (int i = 0; i < num; i++){
                // 设置线程关联的值
                threadLocal.set(Thread.currentThread().getName() + "-" + i);
                // 调用get()方法读取关联的值
                System.out.println(Thread.currentThread().getName() + "value = " + threadLocal.get());
            }
        }
    }

    public static void main(String[] args) {
        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();

        t1.start();
        t2.start();
        /*
        两个线程互不干扰
        Thread-0value = Thread-0-0
        Thread-1value = Thread-1-1
        Thread-1value = Thread-1-2
        Thread-1value = Thread-1-3
        Thread-1value = Thread-1-4
        Thread-0value = Thread-0-1
        Thread-0value = Thread-0-2
        Thread-0value = Thread-0-3
        Thread-0value = Thread-0-4
         */
    }
}
