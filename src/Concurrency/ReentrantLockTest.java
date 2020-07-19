package Concurrency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    @Test
    public void testMethod(){

        try {
            lock.lock();

            System.out.println("开始awit");
            condition.await();

            System.out.println("唤醒线程");
            condition.signal();

            for (int i = 0; i < 5; i++) {
                System.out.println("ThreadName=" + Thread.currentThread().getName()
                        +"(" + i + 1 + ")");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread extends Thread");
        }
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("MyRunnable implements Runnable");
        }
    }

    public static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("MyCallable implements Callable<Integer>");

            // 计算1-5的和
            int sum = 0;
            for (int i = 1; i <= 5; i++) {
                sum += i;
            }

            return sum;
        }
    }

    public static void main(String[] args) {

//        MyCallable myCallable = new MyCallable();
//        // 创建FutureTask对象，传入MyCallable对象，用于接收返回值
//        FutureTask<Integer> result = new FutureTask<>(myCallable);
//
//        Thread thread = new Thread(result);
//        // 开启线程
//        thread.start();
//
//        // 使用FutureTask.get()方法获取线程执行的结果返回值
//        Integer sum = null;
//        try {
//            sum = result.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(sum);


//        MyThread myThread = new MyThread();
//        myThread.start();
//
//        MyRunnable myRunnable = new MyRunnable();
//        Thread thread1 = new Thread(myRunnable);
//        thread1.start();

        // 设置线程数
        int threadNum = 5;
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(threadNum);

        MyRunnable myRunnable = new MyRunnable();
        // 开启线程
        pool.execute(myRunnable);
        // 关闭线程
        pool.shutdown();
    }

}
