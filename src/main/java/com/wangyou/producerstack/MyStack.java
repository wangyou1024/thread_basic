package com.wangyou.producerstack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王游
 * @date 2021/3/3 19:13
 */
public class MyStack {
    private List list = new ArrayList();
    private static final int MAX = 1;

    /**
     * 模拟入栈
     */
    public synchronized void push(){
        // 当栈中数据已满，就等待
        if (list.size() >= MAX){
            System.out.println(Thread.currentThread().getName() + " begin wait...");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String data = "data--" + Math.random();
        System.out.println(Thread.currentThread().getName() + "添加了数据：" + data);
        list.add(data);
        this.notify();
    }

    /**
     * 模拟出栈
     */
    public synchronized void pop(){
        // 没有数据就等待
        if (list.size() == 0){
            System.out.println("begin wait...");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "出栈数据:" + list.remove(0));
        this.notify();
    }
}
