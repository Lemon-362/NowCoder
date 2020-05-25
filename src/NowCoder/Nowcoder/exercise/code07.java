package NowCoder.Nowcoder.exercise;

public class code07 {
    public static int fibonacci(int n) {
        return process(n);
    }

    public static int process(int n){
        // base case
        if (n <= 1){
            return n;
        }else {
            return process(n - 1) + process(n - 2);
        }
    }

    public static int fibonacci1(int n){
        int len = n + 1;
        int[] dp = new int[len];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < len; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[len - 1];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(6)); // 8
        System.out.println(fibonacci1(6));
    }
}
