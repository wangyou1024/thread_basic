package com.wangyou.wait;

/**
 * @author 王游
 * @date 2021/3/3 9:54
 */
public class Test01 {
    public static void main(String[] args) {
        try{
            String text = "something";
            System.out.println("同步前的代码");
            synchronized (text){
                System.out.println("同步代码块开始……");
                text.wait();    // 线程等待，后面的内容不输出
                System.out.println("wait后的代码……");
            }
            System.out.println("同步代码块后面的代码");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main最后的代码");
    }
}
