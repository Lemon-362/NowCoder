package NowCoder.背包问题.exercise;

/*
01背包问题: TODO 每个物品只能使用一次, 1: 选择, 0: 不选择
    有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
    第 i 件物品的体积是 vi，价值是 wi。
    求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
    输出最大价值。
 */
public class code01 {

    public static int process1(int[] v, int[] w, int N, int V){

        int[][] dp = new int[N + 1][V + 1];

        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                // 1
                dp[i][j] = dp[i - 1][j];
                // 2
                if (j >= v[i]){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i]] + w[i]);
                }
            }
        }

        return dp[N][V];
    }

    public static int process2(int[] v, int[] w, int N, int V){

        int[] dp = new int[V + 1];

        dp[0] = 0;

//        for (int i = 1; i <= N; i++) {
//            for (int j = V; j >= 0; j--) {
//                // 1
//                dp[j] = dp[j];
//                // 2
//                if (j >= v[i]){
//                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
//                }
//            }
//        }
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
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

        System.out.println(process1(v, w, N, V)); // 8
        System.out.println(process2(v, w, N, V)); // 8
    }

}
