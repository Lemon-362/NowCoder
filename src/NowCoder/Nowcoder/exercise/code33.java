package NowCoder.Nowcoder.exercise;

public class code33 {
    public static int[] getUgly(int n) {
        if (n <= 0) {
            return null;
        }

        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[p2] * 2, Math.min(dp[p3] * 3, dp[p5] * 5));

            if (dp[i] == dp[p2] * 2){
                p2++;
            }
            if (dp[i] == dp[p3] * 3){
                p3++;
            }
            if (dp[i] == dp[p5] * 5){
                p5++;
            }
        }

        return dp;
    }

    public static void main(String[] args) {
        int[] res = getUgly(16);
        for (int i : res) {
            System.out.print(i + " "); // 1 2 3 4 5 6 8 9 10 12 15 16 18 20 24 25
        }
    }
}
