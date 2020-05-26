package NowCoder.Nowcoder.exercise;

public class code46 {
    public static int lastRemaining(int n, int m){
        if (n < 1 || m < 1){
            return -1;
        }

        int[] dp = new int[n + 1];
        dp[1] = 0;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + m) % i;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3)); // 3

        System.out.println(lastRemaining(10, 17)); // 2
    }
}
