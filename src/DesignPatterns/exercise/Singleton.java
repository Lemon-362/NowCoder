package DesignPatterns.exercise;

public class Singleton {

    public static void main(String[] args) {

        // 饿汉式
        Bank bank1 = Bank.getInstance();
        Bank bank2 = Bank.getInstance();
        System.out.println(bank1 == bank2); // true

        // 懒汉式
        Order order1 = Order.getInstance();
        Order order2 = Order.getInstance();
        System.out.println(order1 == order2); // true

    }

}

class Bank {

    private Bank() {
    }

    private static Bank instance = new Bank();

    public static Bank getInstance() {
        return instance;
    }

}

class Order {

    private Order() {
    }

    private static Order instance = null;

    public static Order getInstance() {

        if (instance == null) {
            instance = new Order();
        }

        return instance;
    }

}
