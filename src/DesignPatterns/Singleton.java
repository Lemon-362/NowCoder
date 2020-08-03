package DesignPatterns;

public class Singleton {

    /*
    单例:
        (1) 饿汉式: 不管用不用都先new好
        (2) 懒汉式: 要用时在new
     */
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

// 饿汉式
class Bank {

    // private, 防止外部直接new该对象
    private Bank(){}

    // 内部private创建对象
    private static Bank instance = new Bank();

    // 对外只提供public,static的获取创建的对象的方法
    public static Bank getInstance(){
        return instance;
    }
}

// 懒汉式
class Order {

    // private, 防止外部直接new该对象
    private Order(){}

    // 内部private创建对象, 但初始化为null
    private static Order instance = null;

    // 对外只提供public,static的获取创建的对象的方法, 并且首先判断对象是否为null
    public static Order getInstance(){

        if (instance == null){
            instance = new Order();
        }

        return instance;
    }
}
