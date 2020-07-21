package Concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class DataShare {

    public static class MyData {
        private int value = 0;

        public synchronized void add() {
            value += 10;

            System.out.println(Thread.currentThread().getName() + "add的value为: " + value);
        }

        public synchronized void dec() {
            value -= 10;

            System.out.println(Thread.currentThread().getName() + "dec的value为: " + value);
        }

        public int getData() {
            return value;
        }
    }

    public static class AddThread implements Runnable {

        MyData data = new MyData();

        public AddThread(MyData data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.add();
        }
    }

    public static class DecThread implements Runnable {

        MyData data = new MyData();

        public DecThread(MyData data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.dec();
        }
    }

    public static void main(String[] args) {
        MyData data = new MyData();

        AddThread addThread = new AddThread(data);
        DecThread decThread = new DecThread(data);

        for (int i = 0; i < 5; i++) {
            new Thread(addThread).start();
            new Thread(decThread).start();
        }

    }
}
