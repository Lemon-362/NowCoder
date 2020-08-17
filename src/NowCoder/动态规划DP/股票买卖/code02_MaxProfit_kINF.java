package NowCoder.动态规划DP.股票买卖;

/*
股票买卖1:
    一个数组, arr[i]表示一只股票, 在第i天的价格
    最多可以完成k笔交易, 求可以获得的最大利润
    TODO k=INF 无限次交易
 */
public class code02_MaxProfit_kINF {

    /*
    模板:
        1. base case:
            dp[0][k][0] = 0
            dp[0][k][1] = -INF
            dp[i][0][0] = 0
            dp[i][0][1] = -INF
        2. 状态转移:
            dp[i][k][0] = max{不操作, 卖出}
                        = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i-1]}
            dp[i][k][1] = max{不操作, 买入}
                        = max{dp[i-1][k][1], dp[i-1][k-1][0] - prices[i-1]}
    */

    /*
    K=INF:
        base case:
            dp[0][K][0] = 0
            dp[0][K][1] = -INF
            dp[i][0][0] = 0
            dp[i][0][1] = -INF
        状态转移:
            (1) n = 0:
                dp[i][K][0] = max{dp[i-1][K][0], dp[i-1][K][1] + prices[i-1]}
            (2) n = 1:
                dp[i][K][1] = max{dp[i-1][K][1], dp[i-1][K-1][0] - prices[i-1]}
                            = 因为 K=INF, 所以 K-1也趋近于INF, 即 K = K-1
                            = max{dp[i-1][K][1], dp[i-1][K][0] - prices[i-1]}
        从状态转移中可以看出, K始终为K
        所以K这个维度可以省去, 对状态转移没有影响
        TODO 注意:
            此时(2)中的 dp[i-1][K][0] 并不能像K=1时可以直接根据base case: dp[i][0][0] = 0去掉
            因为此时 dp[i-1][K][0] 中的K并不为0
        ==>
        base case:
            dp[0][0] = 0
            dp[0][1] = -INF
        状态转移:
            (1) n = 0:
                dp[i][0] = max{dp[i-1][0], dp[i-1][1] + prices[i-1]}
            (2) n = 1:
                dp[i][1] = max{dp[i-1][1], dp[i-1][0] - prices[i-1]}
        范围:
            0<=i<=arr.length
            n=0/1
     */
    public static int maxProfit1(int[] arr) {

        int[][] dp = new int[arr.length + 1][2];
        // base case
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - arr[i - 1]);
        }

        return dp[arr.length][0];
    }

    // dp优化: dp[i][0]和dp[i][1]之和之前一个状态有关, 所以可以用两个变量来存储
    public static int maxProfit2(int[] arr) {

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            // 因为 K=INF 时, dp[i][1] 是和 dp[i-1][0] 有关的,
            // 所以必须先保存, 不然在更新dp_i_0时, 把上一次的值直接更新掉了
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + arr[i]);
            dp_i_1 = Math.max(dp_i_1, temp - arr[i]);
        }

        return dp_i_0;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 1, 5, 3, 6, 4};
        int[] arr2 = {1,2,3,4,5};

        System.out.println(maxProfit1(arr1)); // 7
        System.out.println(maxProfit2(arr1)); // 7
        System.out.println(maxProfit1(arr2)); // 4
        System.out.println(maxProfit2(arr2)); // 4
    }
}
