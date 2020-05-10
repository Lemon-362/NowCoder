package NowCoder.Nowcoder.exercise;

public class code08 {
    public static int jump(int n){
        if (n <= 2){
            return n;
        }

        return process(n);
    }

    public static int process(int n){
        int len = n + 1;
        int[] dp = new int[len];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < len; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(jump(5)); // 8
    }
}
