package NowCoder.动态规划DP.exercise.股票买卖;

/*
股票买卖1:
    一个数组, arr[i]表示一只股票, 在第i天的价格
    最多可以完成k笔交易, 求可以获得的最大利润
    TODO k=2
 */
public class code05_MaxProfit_k2 {

    /*
        dp[i][k][n]
        1. base case:
            dp[0][k][0] = 0
            dp[0][k][1] = -INF
            dp[i][0][0] = 0
            dp[i][0][1] = -INF
        2. 状态转移:
            dp[i][k][0] = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i-1]}
            dp[i][k][1] = max{dp[i-1][k][1], dp[i-1][k-1][0] - prices[i-1]}
        此时状态方程中的 dp[i-1][k-1][0] 是不能省去的
        所以需要多一层K的循环
        ==> 遍历方向:
            因为dp[i][k][1] 依赖于 dp[i-1][k-1][0]
            所以要先知道 之前的 结果
            所以K应该反向遍历
        3. 范围:
            0<=i<=arr.length
            0<=k<=K
            n=0/1
     */
    public static int maxProfit(int[] arr) {

        int K = 2;
        int[][][] dp = new int[arr.length + 1][K + 1][2];
        // base case
        for (int k = 0; k <= K; k++) {
            dp[0][k][0] = 0;
            dp[0][k][1] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int k = K; k >= 1; k--) {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + arr[i - 1]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - arr[i - 1]);
            }
        }

        return dp[arr.length][K][0];
    }

    public static void main(String[] args) {

        int[] arr1 = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] arr2 = {1, 2, 3, 4, 5};

        System.out.println(maxProfit(arr1)); // 6
        System.out.println(maxProfit(arr2)); // 4

    }
}
