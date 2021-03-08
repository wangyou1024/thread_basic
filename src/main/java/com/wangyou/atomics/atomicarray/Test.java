package com.wangyou.atomics.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicIntegerArray的基本操作
 *
 * @author 王游
 * @date 2021/3/2 20:35
 */
public class Test {
    public static void main(String[] args) {
        // 1) 创建一个指定长度的原子数组：[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        System.out.println("1:" + atomicIntegerArray);
        // 2) 返回指定位置的元素：0
        System.out.println("2:" + atomicIntegerArray.get(0));
        // 3) 设置指定位置的元素
        atomicIntegerArray.set(0, 10);
        // 4) 设置新值时，返回数组元素的旧值：10
        System.out.println("4:" + atomicIntegerArray.getAndSet(0, 20));
        // 5) 修改数组元素的值，把数组元素加上某个值：42、42
        System.out.println("5:" + atomicIntegerArray.addAndGet(0, 22));
        System.out.println("5:" + atomicIntegerArray.getAndAdd(0, 33));
        // 6) CAS操作,如果数组中索引值为0的元素的值是32，就修改为222：true
        System.out.println("6:" + atomicIntegerArray.compareAndSet(0, 75, 222));
        // 6） 自增自减：1、1、1
        System.out.println("6:" + atomicIntegerArray.incrementAndGet(1));
        System.out.println("6:" + atomicIntegerArray.getAndIncrement(1));
        System.out.println("6:" + atomicIntegerArray.decrementAndGet(1));
    }
}
