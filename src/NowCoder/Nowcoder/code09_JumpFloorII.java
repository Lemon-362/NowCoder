package NowCoder.Nowcoder;

/*
    变态跳台阶：
        一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
        求该青蛙跳上一个n级的台阶总共有多少种跳法
 */
public class code09_JumpFloorII {
    // F(N) = F(N-1) + F(N-2) + ... + F(1)
    // F(N-1) = F(N-2) + F(N-3) + .. + F(1)
    // 两式相减可得：F(N) = 2F(N-1), N > 1  F(1) = 1
    public static int method01(int n) {
        if (n == 1) {
            return n;
        }
        return 2 * method01(n - 1);
    }

    public static int method02(int n) {
        if (n == 1) {
            return n;
        }
        int res = 0;
        int pre = 1;
        for (int i = 2; i <= n; i++) {
            res = 2 * pre;

            pre = res;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(method01(n)); // 512
        System.out.println(method02(n));
    }
}
