package NowCoder.Nowcoder;

/*
    斐波那契数列：
        F(0) = 0, F(1) = 1,
        F(N) = F(N-1) + F(N-2), N >= 2
 */
public class code07_Fibonacci {
    // 暴力递归：O(2^N) O(1)
    public static int method01(int n) {
        if (n <= 1) {
            return n;
        }
        return method01(n - 1) + method01(n - 2);
    }

    // 动态规划：因为可变参数为n，当n固定时，前面的F(n)都是固定的，所以无后效性
    // O(N) O(N)
    public static int method02(int n) {
        // 可变参数n，一维dp表，长度n+1
        int len = n + 1;
        int[] dp = new int[len];
        // base case
        dp[0] = 0;
        dp[1] = 1;
        // 根据前两个值，一步一步的往后填
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 动态规划优化：不用dp表，用变量记录
    // O(N) O(1)
    public static int method03(int n){
        if (n <= 1){
            return n;
        }
        int sum = 0; // 要求的F(N)
        int one = 1; // F(N-1)
        int two = 0; // F(N-2)
        // 从n=2开始
        // F(N) = F(N-1) + F(N-2)
        // F(N+1) = F(N) + F(N-1)
        for (int i = 2; i <= n; i++) {
            sum = one + two;

            // TODO 注意先后顺序
            two = one; // F(N-2) --> F(N-1)
            one = sum; // 根据规律，F(N-1) --> F(N)
        }
        return sum;
    }

    // TODO 矩阵求幂法：O(logN) O(logN)

    // TODO 公式法：O(1) O(1)

    public static void main(String[] args) {
        int n = 6;
        System.out.println(method01(n)); // 8
        System.out.println(method02(n));
        System.out.println(method03(n));
    }
}
