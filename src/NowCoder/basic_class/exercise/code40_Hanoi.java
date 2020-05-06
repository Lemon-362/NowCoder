package NowCoder.basic_class.exercise;

public class code40_Hanoi {
    public static void method(int n, String from, String to, String help) {
        if (n < 1) {
            return;
        }
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
        } else {
            method(n - 1, from, help, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            method(n - 1, help, to, from);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        method(n, "left", " right", "mid");
    }
}
