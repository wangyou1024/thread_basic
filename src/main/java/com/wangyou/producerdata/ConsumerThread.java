package com.wangyou.producerdata;

/**
 * 定义线程模拟消费者
 *
 * @author 王游
 * @date 2021/3/3 16:52
 */
public class ConsumerThread extends Thread{
    /**
     * 消费者消费数据，调用valueOP类的getValue方法消费产品
     */
    private ValueOP obj;

    public ConsumerThread(ValueOP obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            obj.getValue();
        }
    }
}
