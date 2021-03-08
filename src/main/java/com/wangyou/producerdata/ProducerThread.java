package com.wangyou.producerdata;

/**
 * 定义线程模拟生产者
 *
 * @author 王游
 * @date 2021/3/3 16:52
 */
public class ProducerThread extends Thread{
    /**
     * 生产者生产数据，调用valueOP类的setValue方法给value赋值
     */
    private ValueOP obj;

    public ProducerThread(ValueOP obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            obj.setValue();
        }
    }
}
