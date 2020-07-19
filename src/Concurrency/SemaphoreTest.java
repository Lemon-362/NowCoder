package Concurrency;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(); // -1
                System.out.println("工人" + this.num + "占用一台机器在生产");
                Thread.sleep(2000);
                System.out.println("工人" + this.num + "释放出机器");
                semaphore.release(); // +1
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 工人数
        int N = 8;
        // 机器数目，一台机器只能被一个工人使用
        Semaphore semaphore = new Semaphore(5);
        // 最多只能有有五个工人在同时占用5台机器，其余需要等待释放后才能使用
        for (int i = 0; i < N; i++) {
            new Worker(i, semaphore).start();
        }
    }

}
