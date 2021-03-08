package com.wangyou.atomics.atomicintegerfield;

/**
 * @author 王游
 * @date 2021/3/3 9:06
 */
public class Test {
    public static void main(String[] args) {
        User user = new User(1234, 10);

        int threadNum = 10;
        for (int i = 0; i < threadNum; i++){
            new SubThread(user).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }
}
