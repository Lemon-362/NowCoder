package NowCoder.basic_class.exercise.exercise02;

public class code40 {
    public static void moveLeftToRight(int n) {
        // base case
        if (n == 1) {
            System.out.println("move 1 from left to right");
        } else {
            moveLeftToMid(n - 1);
            System.out.println("move " + n + " from left to right");
            moveMidToRight(n - 1);
        }
    }

    public static void moveLeftToMid(int n) {
        // base case
        if (n == 1) {
            System.out.println("move 1 from left to mid");
        } else {
            moveLeftToRight(n - 1);
            System.out.println("move " + n + " from left to mid");
            moveRightToMid(n - 1);
        }
    }

    public static void moveMidToLeft(int n) {
        // base case
        if (n == 1) {
            System.out.println("move 1 from mid to left");
        } else {
            moveMidToRight(n - 1);
            System.out.println("move " + n + " from mid to left");
            moveRightToLeft(n - 1);
        }
    }

    public static void moveMidToRight(int n) {
        // base case
        if (n == 1) {
            System.out.println("move 1 from mid to right");
        } else {
            moveMidToLeft(n - 1);
            System.out.println("move " + n + " from mid to right");
            moveLeftToRight(n - 1);
        }
    }

    public static void moveRightToLeft(int n) {
        // base case
        if (n == 1) {
            System.out.println("move 1 from right to left");
        } else {
            moveRightToMid(n - 1);
            System.out.println("move " + n + " from right to left");
            moveMidToLeft(n - 1);
        }
    }

    public static void moveRightToMid(int n) {
        // base case
        if (n == 1) {
            System.out.println("move 1 from right to mid");
        } else {
            moveRightToLeft(n - 1);
            System.out.println("move " + n + " from right to mid");
            moveLeftToMid(n - 1);
        }
    }

    public static void Hanoi(int n){
        moveLeftToRight(n);
    }

    public static void Hanoi2(int n, String from, String to, String help){
        // base case
        if (n == 1){
            System.out.println("move 1 from " + from + " to " + to);
        }else {
            Hanoi2(n - 1, from, help, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            Hanoi2(n - 1, help, to, from);
        }
    }

    public static void main(String[] args) {
        Hanoi(3);

        System.out.println("=================");

        Hanoi2(3, "left", "right", "mid");

//        move 1 from left to right
//        move 2 from left to mid
//        move 1 from right to mid
//        move 3 from left to right
//        move 1 from mid to left
//        move 2 from mid to right
//        move 1 from left to right
    }
}
