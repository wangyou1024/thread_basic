package com.wangyou.atomics.atomicintegerfield;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author 王游
 * @date 2021/3/3 9:09
 */
public class SubThread extends Thread{
    private User user;
    /**
     * 创建age字段更新器
     */
    private AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    public SubThread(User user){
        this.user = user;
    }

    @Override
    public void run() {
        int age = 10;
        for (int i = 0; i < age; i++){
            System.out.println(updater.getAndIncrement(user));
        }
    }
}
