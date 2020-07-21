package Concurrency;

public class test {

    public static class MyData {

        private int value = 0;

        public synchronized void add(){
            value += 10;

            System.out.println(Thread.currentThread().getName() + "-add-" + value);
        }

        public synchronized void dec(){
            value -= 10;

            System.out.println(Thread.currentThread().getName() + "-dec-" + value);
        }
    }

    public static class addThread implements Runnable {

        private MyData myData = new MyData();

        @Override
        public void run() {
            myData.add();
        }

        public addThread(MyData myData) {
            this.myData = myData;
        }
    }

    public static class decThread implements Runnable {

        private MyData myData = new MyData();

        @Override
        public void run() {
            myData.dec();
        }

        public decThread(MyData myData) {
            this.myData = myData;
        }
    }

    public static void main(String[] args) {

        MyData myData = new MyData();

        addThread addThread = new addThread(myData);
        decThread decThread = new decThread(myData);

        for (int i = 0; i < 5; i++) {
            new Thread(addThread).start();
            new Thread(decThread).start();
        }

    }
}
