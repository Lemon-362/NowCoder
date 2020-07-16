package Concurrency;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {
    // 容器，存放4个不同访问修饰符的变量
    class DemoData {
        public volatile int value1 = 1;
        volatile int value2 = 2;
        protected volatile int value3 = 3;
        private volatile int value4 = 4;
    }

   AtomicIntegerFieldUpdater<DemoData> getUpdater(String fieldName) {
        // 获取容器的运行时类，通过反射更新
        return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
    }

    void doit() {
        DemoData data = new DemoData();
        // 返回以前的值
        System.out.println("1 ==> " + getUpdater("value1").getAndSet(data, 10));
        System.out.println(getUpdater("value1").get(data));
        // 返回更新的值
        System.out.println("2 ==> " + getUpdater("value2").incrementAndGet(data));
        // 返回更新的值
        System.out.println("3 ==> " + getUpdater("value3").decrementAndGet(data));
        // 更新成功返回true
        System.out.println("4 ==> " + getUpdater("value4").compareAndSet(data, 4, 5));
        System.out.println(getUpdater("value4").get(data));
    }

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
        demo.doit();
    }

}
