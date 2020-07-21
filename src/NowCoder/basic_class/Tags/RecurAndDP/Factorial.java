package NowCoder.basic_class.Tags.RecurAndDP;

public class Factorial {
    public static int method(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public static int method02(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        return n*method02(n-1);
    }

    public static void main(String[] args) {
        int n =5;
        System.out.println(method(n));
        System.out.println(method02(n));
    }
}
