package com.wangyou.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * wait条件发生变化
 * 定义一个集合
 * 定义一个线程向集合中添加数据，添加完数据会通知另外的线程从集合中取数据
 * 定义一个线程从集合中取数据，如果集合中没有数据就等待
 *
 * @author 王游
 * @date 2021/3/3 11:17
 */
public class Test05 {
    public static void main(String[] args) {
        ThreadAdd threadAdd = new ThreadAdd();
        ThreadSubtract threadSubtract = new ThreadSubtract();
        threadSubtract.setName("subtract 1");

        /*
        测试一：先开启添加数据的线程，再开启一个取数据的线程，大多数情况下正常
                threadAdd.start();
                threadSubtract.start();
        测试二：先开启取数据的线程，再开启添加数据线程
                threadSubtract.start();
                threadAdd.start();
        测试三：先开启两个取数据的线程，再开启添加数据的线程
                 ThreadSubtract threadSubtract2 = new ThreadSubtract();
                 threadSubtract2.setName("subtract 2");
                 threadSubtract.start();
                 threadSubtract2.start();
                 threadAdd.start();
               结果：同时唤醒后，先后取数据，一个取到，另一个取数据时再取时出现异常
                    subtract 1 begin wait...
                    subtract 2 begin wait...
                    subtract 2 end wait...
                    subtract 2从集合中取了data 后，集合中数据的数量：0
                    subtract 1 end wait...
               解决方案：被唤醒后依然要再判断，可将if改为while
         */
    }

    /**
     * 1） 定义List集合
     */
    static List<String> list = new ArrayList<>();

    /**
     * 2) 定义方法从集合中取数据
     */
    public static void subtract() {
        synchronized (list) {
            try {
                if (list.size() == 0) {
                    System.out.println(Thread.currentThread().getName() + " begin wait...");
                    list.wait();
                    System.out.println(Thread.currentThread().getName() + " end wait...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object data = list.remove(0);
            System.out.println(Thread.currentThread().getName()
                    + "从集合中取了" + data + " 后，集合中数据的数量：" + list.size());
        }
    }

    /**
     * 3) 定义方法向集合中添加数据后，通知等待的线程取数据
     */
    public static void add(){
        synchronized (list){
            list.add("data");
            list.notifyAll();
        }
    }

    /**
     * 4) 定义线程类调用subtract取数据
     */
    static class ThreadSubtract extends Thread {
        @Override
        public void run() {
            subtract();
        }
    }

    /**
     * 5) 定义线程类调用add添加数据
     */
    static class ThreadAdd extends Thread {
        @Override
        public void run() {
            add();
        }
    }
}
