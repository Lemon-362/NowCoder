package NowCoder.动态规划DP.股票买卖;

/*
股票买卖1:
    一个数组, arr[i]表示一只股票, 在第i天的价格
    最多可以完成k笔交易, 求可以获得的最大利润
    TODO k=INF 无限次交易, 且有冷却期, 一次sell后必须要等一天才能买入
 */
public class code03_MaxProfit_kINF_CoolDown {

    /*
        TODO 由于有冷却期, 所以对于n=1时,
            如果选择买入, 只能从 第i-2天 转移过来
        1. base case:
            dp[0][k][0] = 0
            dp[0][k][1] = -INF
            dp[i][0][0] = 0
            dp[i][0][1] = -INF
        2. 状态转移:
            dp[i][k][0] = max{不操作, 卖出}
                        = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i-1]}
            dp[i][k][1] = max{不操作, 买入}
                        = max{dp[i-1][k][1], dp[i-2][k-1][0] - prices[i-1]}

        ==> 改变:
        1. base case:
            dp[0][0] = 0
            dp[0][1] = -INF
        2. 状态转移:
            dp[i][0] = max{dp[i-1][0], dp[i-1][1] + prices[i-1]}
            dp[i][1] = max{dp[i-1][1], dp[i-2][0] - prices[i-1]}
        ==> TODO 由于i-2可能越界, 所以需要额外的base case:
            i=1第一天时:
            (1) 不持有
                dp[1][0] = max{dp[0][0], dp[0][1] + prices[0]}
                         = max{0, -INF} = 0
            (2) 持有
                dp[1][1] = max{dp[0][1], dp[-1][0] - prices[0]}
                         = max{-INF, -prices[0]} = -prices[0]


        范围:
            0<=i<=arr.length
            n=0/1
     */
    public static int maxProfit1(int[] arr) {
        int[][] dp = new int[arr.length + 1][2];
        // base case
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[1][0] = 0;
        dp[1][1] = -arr[0];

        for (int i = 2; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - arr[i - 1]);
        }

        return dp[arr.length][0];
    }

    // dp优化: dp[i][0]和dp[i][1]之和之前一个状态有关, 所以可以用两个变量来存储
    public static int maxProfit2(int[] arr) {

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0;

        for (int i = 0; i < arr.length; i++) {
            // 因为 K=INF 时, dp[i][1] 是和 dp[i-1][0] 有关的,
            // 所以必须先保存, 不然在更新dp_i_0时, 把上一次的值直接更新掉了
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + arr[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - arr[i]);
            dp_pre_0 = temp;
        }

        return dp_i_0;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 0, 2};

        System.out.println(maxProfit1(arr)); // 3
        System.out.println(maxProfit2(arr)); // 3

    }
}
