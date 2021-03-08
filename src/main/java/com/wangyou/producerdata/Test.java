package com.wangyou.producerdata;

/**
 * @author 王游
 * @date 2021/3/3 16:43
 */
public class Test {
    public static void main(String[] args) {
        ValueOP valueOP = new ValueOP();
        /*
         测试一生产，一消费的情况
            ProducerThread producerThread = new ProducerThread(valueOP);
            ConsumerThread consumerThread = new ConsumerThread(valueOP);

            producerThread.start();
            consumerThread.start();
         测试多个生产者，多个消费者：
            把等待条件的if改为while，避免重新唤醒消费者后直接消费空串；
            但是出现了假死现象：消费者消费空串时，重新进入等待状态，生产者又未被唤醒进行生产，所以需要把notify()改为notifyAll()。
            ProducerThread producerThread1 = new ProducerThread(valueOP);
            ProducerThread producerThread2 = new ProducerThread(valueOP);
            ProducerThread producerThread3 = new ProducerThread(valueOP);
            ConsumerThread consumerThread1 = new ConsumerThread(valueOP);
            ConsumerThread consumerThread2 = new ConsumerThread(valueOP);
            ConsumerThread consumerThread3 = new ConsumerThread(valueOP);
            producerThread1.start();
            producerThread2.start();
            producerThread3.start();
            consumerThread1.start();
            consumerThread2.start();
            consumerThread3.start();
         */
    }
}
