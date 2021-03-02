package com.wangyou.atomics.atomiclong;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用原子变量类定义一个计数器
 * 该计数器在整个程序中都能使用，并且所有的地方都使用这一个计算器，这个计算器可以设计为单例
 * @author 王游
 * @date 2021/3/2 19:55
 */
public class Indicator {

    /**
     * 构造方法私有化
     */
    private Indicator(){}

    /**
     * 定义一个私有本类静态对象
     */
    private static final Indicator INSTANCE = new Indicator();

    /**
     * 提供一个公共静态方法返回该类唯一实例
     */
    public static Indicator getInstance(){
        return INSTANCE;
    }

    /**
     * 使用原子变量类保存请求总数、成功数、失败娄
     */
    private final AtomicLong requestCount = new AtomicLong(0);
    private final AtomicLong successCount = new AtomicLong(0);
    private final AtomicLong failureCount = new AtomicLong(0);

    /**
     * 有新的请求
     */
    public void newRequestReceive(){
        requestCount.incrementAndGet();
    }

    /**
     * 处理成功
     */
    public void requestProcessSuccess(){
        successCount.incrementAndGet();
    }

    /**
     * 处理失败
     */
    public void requestProcessFailure(){
        failureCount.incrementAndGet();
    }

    /**
     * 查看请求量
     */
    public long getRequestCount(){
        return requestCount.get();
    }

    /**
     * 查看成功量
     */
    public long getSuccessCount(){
        return successCount.get();
    }

    /**
     * 查看失败量
     */
    public long getFailureCount(){
        return failureCount.get();
    }
}
