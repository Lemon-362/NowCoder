package NowCoder.basic_class.exercise;

/*
	求n！
 */

public class code39_Factorial {
    public static int Factorial01(int n) {
        if (n < 0) {
            return 0;
        }
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public static int Factorial02(int n) {
        if (n == 1) {
            return 1;
        }
        return n * Factorial02(n - 1);
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(Factorial01(n));
        System.out.println(Factorial02(n));
    }
}
