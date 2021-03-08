package com.wangyou.atomics.atomicintegerfield;

/**
 * @author 王游
 * @date 2021/3/3 9:07
 */
public class User {
    int id;
    /**
     * 使用AtomicIntegerFieldUpdater更新的字段必须使用volatile修饰
     */
    volatile int age;

    public User(int id, int age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}
