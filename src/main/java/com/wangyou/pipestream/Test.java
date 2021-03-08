package com.wangyou.pipestream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 使用PipedInputStream和PipedOutputStream管道字节流在线程之间传递数据
 *
 * @author 王游
 * @date 2021/3/3 19:45
 */
public class Test {
    public static void main(String[] args) {
        // 定义管道字节流
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();
        // 建立连接
        try {
            inputStream.connect(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> readData(inputStream)).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> writeData(outputStream)).start();
    }

    /**
     * 定义方法向管道流中写入数据
     */
    public static void writeData(PipedOutputStream out) {
        int sum = 100;
        try {
            for (int i = 0; i < sum; i++) {
                String data = "" + i;
                out.write(data.getBytes());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义方法从管道流中读取数据
     */
    public static void readData(PipedInputStream in) {
        byte[] bytes = new byte[1024];
        // 从管道中读取字节
        try {
            // This method blocks until input data is available, end of file is detected, or an exception is thrown.
            int len = in.read(bytes);
            while (len != -1) {
                System.out.println(new String(bytes, 0, len));
                len = in.read(bytes);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
