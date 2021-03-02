package com.wangyou.intrinsiclock;

/**
 * @author 王游
 * @date 2021/3/1 14:54
 */
public class Test02 {
    public static void main(String[] args) {
        Test02 obj = new Test02();

        // 当前的锁对象this就是obj对象
        new Thread(obj::doSomething).start();
        new Thread(obj::doSomething).start();
    }

    /**
     * 使用synchronized修饰实例方法同步实例方法，默认this作为锁对象
     */
    public synchronized void doSomething() {
        int num = 100;
        for (int i = 1; i <= num; i++) {
            System.out.println(Thread.currentThread().getName() + " ->" + i);
        }
    }
}
