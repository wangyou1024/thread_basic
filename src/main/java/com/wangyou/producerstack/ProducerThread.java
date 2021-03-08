package com.wangyou.producerstack;

/**
 * @author 王游
 * @date 2021/3/3 19:22
 */
public class ProducerThread extends Thread{
    private MyStack stack;

    public ProducerThread(MyStack stack){
        this.stack = stack;
    }

    @Override
    public void run() {
        while(true){
            stack.push();
        }
    }
}
