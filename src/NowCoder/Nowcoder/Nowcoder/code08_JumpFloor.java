package NowCoder.Nowcoder.Nowcoder;

/*
    跳台阶：
        一只青蛙一次可以跳上1级台阶，也可以跳上2级。
        求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class code08_JumpFloor {
    // 对于n级台阶：由于每次可以跳上1级或2级，所以 n级台阶的跳法 = n-1级台阶的跳法 +  n-2级台阶的跳法
    // F(N) = F(N-1) + F(N-2), N > 2
    // F(1) = 1, F(2) = 2
    // 斐波那契数列问题
    public static int method(int n) {
        if (n <= 2) {
            return n;
        }
        return method(n - 1) + method(n - 2);
    }

    public static int method02(int n) {
        if (n <= 2){
            return n;
        }
        int res = 0;
        int one = 2;
        int two = 1;
        for (int i = 3; i <= n; i++) {
            res = one + two;

            two = one;
            one = res;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(method(n));
        System.out.println(method02(n));
    }
}
