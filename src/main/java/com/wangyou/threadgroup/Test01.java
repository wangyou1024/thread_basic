package com.wangyou.threadgroup;

/**
 * @author 王游
 * @date 2021/3/5 15:24
 */
public class Test01 {
    public static void main(String[] args) {
        // 返回当前main线程的线程组
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        // java.lang.ThreadGroup[name=main,maxpri=10]
        System.out.println(mainGroup);

        // 定义线程组，如果不指定所属线程组，刚自动归属当前线程所属的线程组中
        ThreadGroup group1 = new ThreadGroup("group1");
        // java.lang.ThreadGroup[name=group1,maxpri=10]
        System.out.println(group1);

        // 定义线程组，同时指定父线程组
        ThreadGroup group2 = new ThreadGroup(mainGroup, "group2");
        // true
        System.out.println(group1.getParent() == mainGroup);
        // true
        System.out.println(group2.getParent() == mainGroup);

        Runnable r = ()->System.out.println(Thread.currentThread());

        // 在创建线程时，如果没有指定线程组，则默认归属到父线程的线程组中
        Thread t1 = new Thread(r, "t1");
        // Thread[t1,5,main]
        System.out.println(t1);

        // 创建线程时，可以指定线程所属线程组
        Thread t2 = new Thread(group1, r, "t2");
        Thread t3 = new Thread(group2, r, "t3");
        // Thread[t2,5,group1]
        System.out.println(t2);
        // Thread[t3,5,group2]
        System.out.println(t3);
    }
}
