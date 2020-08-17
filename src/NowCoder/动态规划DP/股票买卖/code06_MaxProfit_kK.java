package NowCoder.动态规划DP.股票买卖;

/*
股票买卖1:
    一个数组, arr[i]表示一只股票, 在第i天的价格
    最多可以完成k笔交易, 求可以获得的最大利润
    TODO k=K
 */
public class code06_MaxProfit_kK {

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
            所以要先知道 之前一个位置(k-1)的 结果
            所以K可以正序, 也可以逆序
        3. 范围:
            0<=i<=arr.length
            0<=k<=K
            n=0/1

        TODO 优化:
            由于买和卖不能在同一天, 所以一次交易(买+卖)至少需要两天
            那么 K<=(len/2), 交易次数最多可以限制在数组长度的一半
            如果 K>(len/2), 那么K就是无约束的了, 类似于 code02_MaxProfit_kINF 中K是可以省略的

     */
    public static int maxProfit1(int[] arr, int K) {

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
            for (int k = 1; k <= K; k++) {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + arr[i - 1]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - arr[i - 1]);
            }
        }

        return dp[arr.length][K][0];
    }

    // 优化
    public static int maxProfit2(int[] arr, int K) {

        int n = arr.length;
        if (K > n / 2){
            return code02_MaxProfit_kINF.maxProfit2(arr);
        }

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

        int[] arr1 = {2, 4, 1};
        int[] arr2 = {3, 2, 6, 5, 0, 3};

        System.out.println(maxProfit1(arr1, 2)); // 2
        System.out.println(maxProfit2(arr1, 2)); // 2
        System.out.println(maxProfit1(arr2, 2)); // 7
        System.out.println(maxProfit2(arr2, 2)); // 7

    }

}
