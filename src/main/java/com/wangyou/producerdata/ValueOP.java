package com.wangyou.producerdata;

/**
 * 模拟产品
 *
 * @author 王游
 * @date 2021/3/3 16:43
 */
public class ValueOP {
    private String value = "";

    /**
     * 生产过程
     */
    public void setValue() {
        synchronized (this){
            while (!"".equalsIgnoreCase(value)){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String value = System.currentTimeMillis() + " - " + System.nanoTime();
            System.out.println("set设置的值是：" + value);
            this.value = value;
            this.notifyAll();
        }
    }

    /**
     * 消费过程
     */
    public void getValue(){
        synchronized (this){
            while ("".equalsIgnoreCase(value)){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("get的值是：" + this.value);
            this.value = "";
            this.notifyAll();
        }
    }
}
