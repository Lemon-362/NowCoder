package Concurrency.exercise;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class NThreadPrint {

    /**
     * N个线程循环打印：
     *  信号量数组和线程数组的映射关系，当前线程持有上一个线程的信号量
     *
     *  1. 首先初始化信号量数组，需要先消耗完每个的许可，而对于最后一个信号量，不能消耗
     *      因为在循环中，第一次需要使用到最后一个的信号量，是一个首尾相连的循环
     *
     *  2. 遍历信号量数组，在其中创建每个线程，并开启
     *      对于当前线程来说，首先获取上一个信号量，然后打印，最后释放当前信号量
     *
     *  3. 上一个信号量是i-1，而对于i==0，则是最后一个信号量
     *
     */
    private static final AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        int N = 2;

        Thread[] threads = new Thread[N];

        // 信号量数组，下个线程持有上一个线程的信号量
        final Semaphore[] syncObjects = new Semaphore[N];

        for (int i = 0; i < N; i++) {
            // 许可的数量
            syncObjects[i] = new Semaphore(1);
            // 获取一个许可，许可数量-1
            if (i != N - 1) { // 因为是从第一个开始的，而第一个要持有最后一个的信号量
                // 如果最后一个也获得许可的话，那么可用许可为0，就无法acquire了
                // 其他的先消耗尽许可，在循环中获取上一个许可，然后释放当前许可
                syncObjects[i].acquire();
            }
        }

        // 遍历信号量数组，首先持有上一个信号量，然后打印，最后释放当前信号量
        for (int i = 0; i < N; i++) {
            // 上一个线程的信号量
            final Semaphore lastSemaphore = i == 0 ? syncObjects[N - 1] :
                    syncObjects[i - 1];
            // 当前线程的信号量
            final Semaphore curSemaphore = syncObjects[i];
            // 当前线程号
            final int index = i;
            // 当前线程
            threads[i] = new Thread(new Runnable() {

                public void run() {
                    try {
                        while (true) {
                            // 持有上一个信号量的许可，上一个许可-1
                            lastSemaphore.acquire();

                            System.out.println("thread" + index + ": " +
                                    value.getAndIncrement());

                            if (value.get() > 10) {
                                System.exit(0);
                            }

                            // 访问完后，释放当前信号量的许可，当前许可+1
                            curSemaphore.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

            // 开启当前线程
            threads[i].start();
        }
    }
}
