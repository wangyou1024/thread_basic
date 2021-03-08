package com.wangyou.producerstack;

/**
 * @author 王游
 * @date 2021/3/3 19:12
 */
public class Test {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        ProducerThread p1 = new ProducerThread(stack);
        ConsumerThread c1 = new ConsumerThread(stack);

        p1.start();
        c1.start();
    }
}
