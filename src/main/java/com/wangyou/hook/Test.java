package com.wangyou.hook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author 王游
 * @date 2021/3/5 19:21
 */
public class Test {
    public static void main(String[] args) {
        // 1) 注入Hook线程，在程序退出时删除.lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("JVM退出，会启动当前Hook线程，在Hook线程中删除.lock文件");
            getLockFile().toFile().delete();
        }));

        // 2) 程序运行时，检查lock文件是否存在，如果lock存在，则抛出异常
        if (getLockFile().toFile().exists()){
            throw new RuntimeException("程序已启动");
        } else {
            try {
                getLockFile().toFile().createNewFile();
                System.out.println("程序在启动时创建了lock文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int time = 10;
        for (int i = 0; i < time; i++) {
            System.out.println("程序正在运行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Path getLockFile(){
        return Paths.get("", "tmp.lock");
    }
}
