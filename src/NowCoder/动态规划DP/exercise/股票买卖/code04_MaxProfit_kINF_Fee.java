package NowCoder.动态规划DP.exercise.股票买卖;

/*
股票买卖1:
    一个数组, arr[i]表示一只股票, 在第i天的价格
    最多可以完成k笔交易, 求可以获得的最大利润
    TODO k=INF 无限次交易, 每次交易都有手续费
 */
public class code04_MaxProfit_kINF_Fee {

    /*
        base case:
            dp[0][0] = 0
            dp[0][1] = -INF
        状态转移: TODO 类似于 K-1 的选择, 只需要在一次交易的买/卖中 -fee 即可
            (1) n = 0:
                dp[i][0] = max{dp[i-1][0], dp[i-1][1] + prices[i-1]}
            (2) n = 1:
                dp[i][1] = max{dp[i-1][1], dp[i-1][0] - prices[i-1] - fee}
        范围:
            0<=i<=arr.length
            n=0/1
     */
    public static int maxProfit1(int[] arr, int fee) {

        int[][] dp = new int[arr.length + 1][2];
        // base case
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - arr[i - 1] - fee);
        }

        return dp[arr.length][0];
    }

    // dp优化: dp[i][0]和dp[i][1]之和之前一个状态有关, 所以可以用两个变量来存储
    public static int maxProfit2(int[] arr, int fee) {

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            // 因为 K=INF 时, dp[i][1] 是和 dp[i-1][0] 有关的,
            // 所以必须先保存, 不然在更新dp_i_0时, 把上一次的值直接更新掉了
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + arr[i]);
            dp_i_1 = Math.max(dp_i_1, temp - arr[i] - fee);
        }

        return dp_i_0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 8, 4, 9};

        System.out.println(maxProfit1(arr, 2)); // 8
        System.out.println(maxProfit2(arr, 2)); // 8
    }

}
