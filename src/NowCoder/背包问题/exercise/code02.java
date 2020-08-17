package NowCoder.背包问题.exercise;

public class code02 {

    public static int process1(int[] v, int[] w, int N, int V){

        int[][] dp = new int[N + 1][V + 1];

        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                // 1
                dp[i][j] = dp[i - 1][j];
                // 2
                if (j >= v[i]){
                    for (int k = 0; k * v[i] <= j; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * v[i]] + k * w[i]);
                    }
                }
            }
        }

        return dp[N][V];
    }

    public static int process2(int[] v, int[] w, int N, int V){

        int[] dp = new int[V + 1];

        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                for (int k = 0; k * v[i] <= j; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }

        return dp[V];
    }

    public static int process3(int[] v, int[] w, int N, int V){

        int[] dp = new int[V + 1];

        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = v[i]; j <= V; j++) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }

        return dp[V];
    }

    public static void main(String[] args) {

        int N = 4;
        int V = 5;
        int[] v = {0, 1, 2, 3, 4};
        int[] w = {0, 2, 4, 4, 5};

        System.out.println(process1(v, w, N, V)); // 10
        System.out.println(process2(v, w, N, V)); // 10
        System.out.println(process3(v, w, N, V)); // 10
    }
}
