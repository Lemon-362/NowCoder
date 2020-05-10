package NowCoder.Nowcoder.exercise;

public class code07 {
    public static int fibonacci(int n){
        if (n <= 1){
            return n;
        }

        return process(n);
    }

    public static int process(int n){
        int len = n + 1;
        int[] dp = new int[len];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < len; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(6)); // 8
    }
}
