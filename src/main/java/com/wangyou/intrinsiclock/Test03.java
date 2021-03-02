package com.wangyou.intrinsiclock;

/**
 * @author 王游
 * @date 2021/3/1 14:54
 */
public class Test03 {
    public static void main(String[] args) {
        Test03 obj = new Test03();
        // 当前的锁对象是Test03.class
        new Thread(obj::doSomething1).start();
        new Thread(Test03::doSomething).start();
    }

    /**
     * 使用synchronized修饰静态方法，默认当前类的运行时类对象为锁对象
     * public static void doSomething(){
     *     synchronized(Test03.class){
     *        ......
     *     }
     * }
     */
    public synchronized static void doSomething() {
        int num = 100;
        for (int i = 1; i <= num; i++) {
            System.out.println(Thread.currentThread().getName() + " ->" + i);
        }
    }

    public void doSomething1(){
        synchronized(this){
            int num = 100;
            for (int i = 1; i <= num; i++) {
                System.out.println(Thread.currentThread().getName() + " ->" + i);
            }
        }
    }
}
