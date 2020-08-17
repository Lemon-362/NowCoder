package NowCoder.动态规划DP.exercise.股票买卖;

/*
股票买卖1:
    一个数组, arr[i]表示一只股票, 在第i天的价格
    最多可以完成k笔交易, 求可以获得的最大利润
    TODO k=INF 无限次交易, 且有冷却期, 一次sell后必须要等一天才能买入
 */
public class code03_MaxProfit_kINF_CoolDown {

    /*
        卖出后必须后天才能买入, 所以要是今天选择买入, 必须从i-2天转移过来
        因为i-1天可能是卖出的
        (1) n=0:
            dp[i][0] = {昨天不持有今天不操作, 昨天持有今天卖出}
                     = max{dp[i-1][0], dp[i-1][1] + prices[i-1]}
        (2) n=1:
            dp[i][1] = {昨天持有今天不操作, 昨天不持有今天买入}
                     = max{dp[i-1][1], dp[i-2][0] - prices[i-1]}
        ==> 最终结果:
            dp[N][0]
        ==> 范围:
            0<=i<=N (N=arr.length)
            n=0/1
        ==> base case:
            (1) i=0: 第0天, 还未开始
                dp[0][0] = 0
                dp[0][1] = -INF (还未开始, 不可能持有股票)
            (2) i=1: 第1天
                dp[1][0] = max{dp[0][0], dp[0][1] + prices[0]}
                         = 0
                dp[1][1] = max{dp[0][1], -prices[0]}
                         = -prices[0]

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
        int dp_i_pre = 0;

        for (int i = 0; i < arr.length; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + arr[i]);
            dp_i_1 = Math.max(dp_i_1, dp_i_pre - arr[i]);
            dp_i_pre = temp;
        }

        return dp_i_0;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 0, 2};

        System.out.println(maxProfit1(arr)); // 3
        System.out.println(maxProfit2(arr)); // 3

    }
}
