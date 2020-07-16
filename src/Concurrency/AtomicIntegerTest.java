package Concurrency;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import static org.junit.Assert.*;

public class AtomicIntegerTest {


    @Test
    public void testAll() throws InterruptedException {
        final AtomicInteger value = new AtomicInteger(10);
        // 如果当前值==预期值expect，则以原子方式将该值设置为给定的更新值update
        // 如果成功则返回true，否则返回false，并且不会修改原来的值
        // 原始值10，预期值1，false
        assertEquals(value.compareAndSet(1, 2), false);
        // 不修改，仍然是10
        assertEquals(value.get(), 10);
        // 原始值10，预期值10，true
        assertTrue(value.compareAndSet(10, 3));
        // 修改为3
        assertEquals(value.get(), 3);

        // 设置为给定值newValue
        value.set(0);
        // 以原子方式将当前值加1，返回更新的值1
        assertEquals(value.incrementAndGet(), 1);
        // 以原子方式将给定值delta与当前值相加，返回以前的值1
        assertEquals(value.getAndAdd(2), 1);
        // 以原子方式设置为给定值newValue，返回以前的值3，因为上一步结束后就加上2了
        assertEquals(value.getAndSet(5), 3);
        // 上一步结束就加上3了
        assertEquals(value.get(), 5);

        //
        final int threadSize = 10;
        Thread[] ts = new Thread[threadSize];
        for (int i = 0; i < threadSize; i++) {
            ts[i] = new Thread() {
                public void run() {
                    value.incrementAndGet();
                }
            };
        }
        //
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            t.join();
        }
        //
        assertEquals(value.get(), 5 + threadSize);
    }
}

