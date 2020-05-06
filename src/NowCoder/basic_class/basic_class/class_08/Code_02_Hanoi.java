package NowCoder.basic_class.basic_class.class_08;

/*
    汉诺塔问题：
 */
public class Code_02_Hanoi {

    public static void hanoi(int n) {
        if (n > 0) {
            func(n, n, "left", "mid", "right");
        }
    }

    public static void func(int rest, int down, String from, String help, String to) {
        if (rest == 1) {
            System.out.println("move " + down + " from " + from + " to " + to);
        } else {
            func(rest - 1, down - 1, from, to, help);
            func(1, down, from, help, to);
            func(rest - 1, down - 1, help, from, to);
        }
    }

    // 法一：递归，法二的优化版本
    // N : 1 - N
    public static void process(int N, String from, String to, String help) {
        if (N == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else { // 三步骤
            process(N - 1, from, help, to); // 1 - N-1 从from移到help，可以借助to
            System.out.println("Move " + N + " from " + from + " to " + to); //from的N移到to
            process(N - 1, help, to, from); // 1 - N-1 从help移到to，可以借助from
        }
    }

    // 法二：6个过程，相互嵌套（每个过程都是三步）
    // 左--右，左--中，右--左，右--中，中--左，中--右

    // 左--右，1-N-1从左到中，N从左到右，1-N-1从中到右
    public static void moveLeftToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to right");
        } else {
            moveLeftToMid(N - 1);
            System.out.println("move " + N + " from left to right");
            moveMidToRight(N - 1);
        }
    }

    // 右--左，1-N-1从右到中，N从右到左，1-N-1从中到左
    public static void moveRightToLeft(int N) {
        if (N == 1) {
            System.out.println("move 1 from right to left");
        } else {
            moveRightToMid(N - 1);
            System.out.println("move " + N + " from right to left");
            moveMidToLeft(N - 1);
        }
    }

    // 左--中，1-N-1从左到右，N从左到中，1-N-1从右到中
    public static void moveLeftToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to mid");
        } else {
            moveLeftToRight(N - 1);
            System.out.println("move " + N + " from left to mid");
            moveRightToMid(N - 1);
        }
    }

    // 中--左，1-N-1从中到右，N从中到左，1-N-1从右到左
    public static void moveMidToLeft(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to left");
        } else {
            moveMidToRight(N - 1);
            System.out.println("move " + N + " from mid to left");
            moveRightToLeft(N - 1);
        }
    }

    // 右--中，1-N-1从右到左，N从右到中，1-N-1从左到右
    public static void moveRightToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from right to mid");
        } else {
            moveRightToMid(N - 1);
            System.out.println("move " + N + " from right to mid");
            moveLeftToRight(N - 1);
        }
    }

    // 中--右，1-N-1从中到左，N从中到右，1-N-1从左到右
    public static void moveMidToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to right");
        }
        moveMidToLeft(N - 1);
        System.out.println("move " + N + " from mid to right");
        moveLeftToRight(N - 1);
    }

    public static void main(String[] args) {
        int n = 3;
//        hanoi(n);

        process(n, "left", "right", "mid");

//        moveLeftToRight(n);

//        move 1 from left to right
//        move 2 from left to mid
//        move 1 from right to mid
//        move 3 from left to right
//        move 1 from mid to left
//        move 2 from mid to right
//        move 1 from left to right
    }

}
