package com.wangyou.atomics.atomicreference;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 王游
 * @date 2021/3/3 9:18
 */
public class Test {
    /**
     * static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("abc", 0);
     * atomicStampedReference.compareAndSet("abc", "def", oldStamp(取值时的版本号), newStamp);
     */
    static AtomicReference<String> atomicReference = new AtomicReference<>("abc");

    public static void main(String[] args) {
        int threadNum = 100;
        for(int i = 0; i < threadNum; i++){
            new Thread(()->{
                if (atomicReference.compareAndSet("abc","def")){
                    System.out.println(Thread.currentThread().getName() + "把字符串abc更改为def");
                }
            }).start();
        }
        for (int i = 0; i < threadNum; i++){
            new Thread(()->{
                if (atomicReference.compareAndSet("def","abc")){
                    System.out.println(Thread.currentThread().getName() + "把字符串def更改为abc");
                }
            }).start();
        }

    }
}
