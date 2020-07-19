package Concurrency.exercise;

import java.util.concurrent.atomic.AtomicInteger;

public class TwoThreadPrint {
    /**
     * 两个线程循环打印奇数和偶数：
     *
     *  1. 将两个线程根据同一个类创建出来，锁住同一个对象或者类
     *
     *  2. 首先打印，然后递增，释放当前线程，并使得当前线程等待
     *
     */
    private static volatile int value = 0;

    private static final Object obj = new Object();

    public static class SolutionTask implements Runnable{

        @Override
        public void run() {
            // 可以用SolutionTask.class锁住类，也可以用obj锁住同一个对象
            synchronized (obj){
                while (value <= 100){
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                    value++;
                    // 释放当前线程
                    obj.notify();
                    try {
                        // 当前线程等待
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (value > 100){
                    // 0：正常退出
                    System.exit(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new SolutionTask(), "偶数").start();
        new Thread(new SolutionTask(), "奇数").start();

    }
}
