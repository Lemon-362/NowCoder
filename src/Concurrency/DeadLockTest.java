package Concurrency;

/*
死锁: 要有两个锁，两个线程互相竞争
 */
public class DeadLockTest {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockA){
                    try {
                        System.out.println(Thread.currentThread().getName() + "获得了锁A！");

                        System.out.println(Thread.currentThread().getName() + "休眠3秒");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "竞争锁B！");
                    synchronized (lockB){
                        System.out.println(Thread.currentThread().getName() + "获得了锁B！");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockB){
                    try {
                        System.out.println(Thread.currentThread().getName() + "获得了锁B！");

                        System.out.println(Thread.currentThread().getName() + "休眠3秒");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "竞争锁A！");
                    synchronized (lockA){
                        System.out.println(Thread.currentThread().getName() + "获得了锁A！");
                    }
                }
            }
        }).start();

    }
}
