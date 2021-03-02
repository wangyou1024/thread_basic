package com.wangyou.atomics.atomiclong;

import java.util.Random;

/**
 * 模拟服务器的请求总数，处理成功/失败的数量
 *
 * @author 王游
 * @date 2021/3/2 19:46
 */
public class Test {
    public static void main(String[] args) {
        int threadNum = 10000;
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                Indicator.getInstance().newRequestReceive();
                int num = new Random().nextInt();
                if (num % 2 == 0) {
                    Indicator.getInstance().requestProcessSuccess();
                } else {
                    Indicator.getInstance().requestProcessFailure();
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("总数：" + Indicator.getInstance().getRequestCount());
        System.out.println("成功：" + Indicator.getInstance().getSuccessCount());
        System.out.println("失败：" + Indicator.getInstance().getFailureCount());
    }
}
