package NowCoder.basic_class.exercise.exercise02;

public class code40 {
    public static void moveLeftToRight(int N){
        // base case
        if (N == 1){
            System.out.println("move 1 from left to right");
            return;
        }else {
            moveLeftToMid(N - 1);
            System.out.println("move " + N + " from left to right");
            moveMidToRight(N - 1);
        }
    }

    public static void moveLeftToMid(int N){
        // base case
        if (N == 1){
            System.out.println("move 1 from left to mid");
            return;
        }else {
            moveLeftToRight(N - 1);
            System.out.println("move " + N + " from left to mid");
            moveRightToMid(N - 1);
        }
    }

    public static void moveMidToLeft(int N){
        // base case
        if (N == 1){
            System.out.println("move 1 from mid to left");
            return;
        }else {
            moveMidToRight(N - 1);
            System.out.println("move " + N + " from mid to left");
            moveRightToLeft(N - 1);
        }
    }

    public static void moveMidToRight(int N){
        // base case
        if (N == 1){
            System.out.println("move 1 from mid to right");
            return;
        }else {
            moveMidToLeft(N - 1);
            System.out.println("move " + N + " from mid to right");
            moveLeftToRight(N - 1);
        }
    }

    public static void moveRightToLeft(int N){
        // base case
        if (N == 1){
            System.out.println("move 1 from right to left");
            return;
        }else {
            moveRightToMid(N - 1);
            System.out.println("move " + N + " from right to left");
            moveMidToLeft(N - 1);
        }
    }

    public static void moveRightToMid(int N){
        // base case
        if (N == 1){
            System.out.println("move 1 from right to mid");
            return;
        }else {
            moveRightToLeft(N - 1);
            System.out.println("move " + N + " from right to mid");
            moveLeftToMid(N - 1);
        }
    }

    public static void Hanoi(int N){
        moveLeftToRight(N);
    }

    public static void Hanoi2(int N, String left, String right, String mid){
        if (N == 1){
            System.out.println("move 1 from " + left + " to " + right);
            return;
        }else {
            Hanoi2(N - 1, left, mid, right);
            System.out.println("move " + N + " from " + left + " to " + right);
            Hanoi2(N - 1, mid, right, left);
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
