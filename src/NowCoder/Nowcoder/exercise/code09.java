package NowCoder.Nowcoder.exercise;

public class code09 {
    public static int jump(int n){
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
            dp[i] = 2 * dp[i - 1];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(jump(10)); // 512
    }
}
