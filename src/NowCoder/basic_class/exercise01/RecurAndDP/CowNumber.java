package NowCoder.basic_class.exercise01.RecurAndDP;

public class CowNumber {
    public static int method(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return method(n - 1) + method(n - 3);
    }

    public static int method02(int n){
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1;
        int tmp2;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;

            res += prepre;

            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(method(n));
        System.out.println(method02(n));
    }

}
