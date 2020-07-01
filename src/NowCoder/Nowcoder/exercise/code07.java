package NowCoder.Nowcoder.exercise;

public class code07 {
    public static int process(int N){
        // base case
        if (N <= 1){
            return N;
        }else {
            return process(N - 1) + process(N - 2);
        }
    }

    public static int fibonacci(int N){
        return process(N);
    }

    public static int fibonacci1(int N){
        int[] dp = new int[N + 1];

        // base case
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[N];
    }

    public static int fibonacci2(int N){
        if (N <= 1){
            return N;
        }

        int cur = 1;
        int pre = 0;
        int res = 0;

        for (int i = 2; i <= N; i++) {
            res = cur + pre;

            pre = cur;
            cur = res;
        }

        return res % 1000000007;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(6)); // 8
        System.out.println(fibonacci1(6));
        System.out.println(fibonacci2(6));
    }
}
