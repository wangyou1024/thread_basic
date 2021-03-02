package com.wangyou.cas;

/**
 * @author 王游
 * @date 2021/3/2 15:11
 */
public class CASTest {
    public static void main(String[] args) {
        CASCounter casCounter = new CASCounter();

        long sum = 10000;
        for (int i = 0; i < sum; i++){
            new Thread(()->System.out.println(casCounter.incrementAndGet())).start();
        }
    }
}

class CASCounter {
    /**
     * 使用volatile修饰value值，使线程可见
     */
    volatile private long value;

    public long getValue() {
        return value;
    }

    /**
     * 定义compare and swap方法
     */
    private boolean compareAndSwap(long expectedValue, long newValue) {
        // 如果当前value的值与期望的expectedValue值一样，就把当前的value字段替换为newValue值
        synchronized (this) {
            if (value == expectedValue) {
                value = newValue;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 定义自增方法
     */
    public long incrementAndGet() {
        long oldValue;
        long newValue;
        do {
            oldValue = value;
            newValue = oldValue + 1;
        } while (!compareAndSwap(oldValue, newValue));
        return newValue;
    }
}
