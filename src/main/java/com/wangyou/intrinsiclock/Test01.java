package com.wangyou.intrinsiclock;

/**
 * @author 王游
 * @date 2021/3/1 14:54
 */
public class Test01 {
    public static void main(String[] args) {
        Test01 obj = new Test01();

        // 当前的锁对象this就是obj对象
        new Thread(obj::doSomething).start();
        new Thread(obj::doSomething).start();
    }

    public void doSomething() {
        // 使用this当前对象作为锁对象
        synchronized (this) {
            int num = 100;
            for (int i = 1; i <= num; i++) {
                System.out.println(Thread.currentThread().getName() + " ->" + i);
            }
        }
    }
}
