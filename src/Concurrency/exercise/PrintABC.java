package Concurrency.exercise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
    三个线程，一个打印a，一个打印b，一个打印c
    要求连续打印10个abc

 */
public class PrintABC {

    /*
    方法一： 使用一个同步锁ReentrantLock
        线程之间的唤醒操作可以通过Condition实现，且Condition可以有多个，
        每个condition.await阻塞只能通过该condition的signal/signalAll来唤醒

        通过三个Condition A B C，A -> B -> C, 依次唤醒
     */
    public static class RcSyncPrinter implements Runnable {

        // 打印次数
        private static final int PRINT_COUNT = 10;
        // 打印锁
        private final ReentrantLock reentrantLock;
        // 本线程打印所需的condition
        private final Condition thisCondition;
        // 下一个线程打印所需要的condition
        private final Condition nextCondition;
        // 打印字符
        private final char printChar;

        public RcSyncPrinter(ReentrantLock reentrantLock,
                             Condition thisCondition,
                             Condition nextCondition,
                             char printChar) {
            this.reentrantLock = reentrantLock;
            this.nextCondition = nextCondition;
            this.thisCondition = thisCondition;
            this.printChar = printChar;
        }

        @Override
        public void run() {
            // 获取打印锁 进入临界区
            reentrantLock.lock();
            try {
                // 连续打印PRINT_COUNT次
                for (int i = 0; i < PRINT_COUNT; i++) {
                    //打印字符
                    System.out.println(Thread.currentThread().getName() + ": " +
                            printChar);
                    // 使用nextCondition唤醒下一个线程
                    // 因为只有一个线程在等待，所以signal或者signalAll都可以
                    nextCondition.signal();
                    // 不是最后一次则通过thisCondition等待被唤醒
                    // 必须要加判断，不然虽然能够打印10次，但10次后就会直接死锁
                    if (i < PRINT_COUNT - 1) {
                        try {
                            // 本线程让出锁并等待唤醒
                            thisCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            } finally {
                // 释放打印锁
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 写锁
        ReentrantLock lock = new ReentrantLock();
        // 打印a线程的condition
        Condition conditionA = lock.newCondition();
        // 打印b线程的condition
        Condition conditionB = lock.newCondition();
        // 打印c线程的condition
        Condition conditionC = lock.newCondition();
        // 实例化A线程
        Thread printerA = new Thread(new RcSyncPrinter(lock, conditionA, conditionB,
                'A'));
        // 实例化B线程
        Thread printerB = new Thread(new RcSyncPrinter(lock, conditionB, conditionC,
                'B'));
        // 实例化C线程
        Thread printerC = new Thread(new RcSyncPrinter(lock, conditionC, conditionA,
                'C'));
        // 依次开始A B C线程
        printerA.start();
        Thread.sleep(100);
        printerB.start();
        Thread.sleep(100);
        printerC.start();
    }

}
