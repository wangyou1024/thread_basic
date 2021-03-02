package com.wangyou.volatilekw;

/**
 * @author 王游
 * @date 2021/3/1 17:00
 */
public class Test01 {
    public static void main(String[] args) {
        PrintString printString = new PrintString();

        // 在子线程中打印
        new Thread(printString::doSomething).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("在main线程中修改打印标志");
        printString.setContinuePrint(false);
        /*
        现象：子线程依然未结束（未添加volatile）
        原因：main线程修改了printString对象的打印标志后，子线程读不到
        解决办法：使用volatile关键字修饰printString对象的打印标志,
                volatile的作用可以强制线程从公共内存读取变量的值，而不是从工作内存中读取。
         */
    }

    static class PrintString{
        private volatile boolean continuePrint = true;

        public PrintString setContinuePrint(boolean continuePrint) {
            this.continuePrint = continuePrint;
            return this;
        }

        public void doSomething(){
            System.out.println("开始……");
            while (continuePrint){

            }
            System.out.println("结束……");
        }
    }
}
