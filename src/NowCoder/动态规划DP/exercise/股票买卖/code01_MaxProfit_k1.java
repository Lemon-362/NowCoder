package NowCoder.动态规划DP.exercise.股票买卖;

/*
股票买卖1:
    一个数组, arr[i]表示一只股票, 在第i天的价格
    最多可以完成k笔交易, 求可以获得的最大利润
    TODO k=1
 */
public class code01_MaxProfit_k1 {

    /*
        1. 状态:
            前i天, 最多k笔交易, 当天持有股票的状态(0: 未持有, 1: 持有)
            因为买股票的前提是当天持有股票, 所以必须知道当天持有股票的状态
        2. dp数组:
            三维dp数组 dp[i][k][n]
            含义: 在前i天中, 允许最多交易k笔, 当天持有股票状态是n, 这种状态下获得的最大利润为dp[i][k][n]
        3. 选择, 状态转移, 择优:
            对于当天的每支股票都可以买/卖, 但是有前提
            (1) n=0: 今天不持有股票
                可以从 昨天不持有, 今天不操作 / 昨天持有, 今天卖了 转移过来
                dp[i][k][0] = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i]}
            (2) n=1: 今天持有股票
                可以从 昨天不持有, 今天买了 / 昨天持有, 今天不操作 转移过来
                dp[i][k][1] = max{dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]}
            从两种选择中选择max较大的
            ==> 最终结果:
                dp[N-1][K][0]
                因为在最后一天, 如果n=1持有股票的话, 一定会比n=0不持有股票的金额小
                因为n=1表示 今天买入/昨天就持有, 一定是要 -prices 的
            ==> 范围:
                0<=i<=N-1 (N=arr.length)
                0<=k<=K
                n=0/1
            ==> base case:
                (1) i=0: 第0天, 还未开始
                    dp[0][k][0] = 0
                    dp[0][k][1] = -INF (还未开始, 不可能持有股票)
                (2) k=0: 最大交易次数为0
                    dp[i][0][0] = 0
                    dp[i][0][1] = -INF (最多交易0次, 不可能持有股票)
            ==> 改进:
                因为i=0表示第0天, 所以i=1才表示第一天, 而在prices中应该是i-1
                (1) n=0:
                    dp[i][k][0] = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i-1]}
                (2) n=1:
                    dp[i][k][1] = max{dp[i-1][k][1], dp[i-1][k-1][0] - prices[i-1]}
            ==> 最终结果:
                dp[N][K][0]
            ==> 范围:
                0<=i<=N (N=arr.length)
                0<=k<=K
                n=0/1

     */

    /*
    模板:
        (1) n=0:
            dp[i][k][0] = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i-1]}
        (2) n=1:
            dp[i][k][1] = max{dp[i-1][k][1], dp[i-1][k-1][0] - prices[i-1]}
        ==> 最终结果:
            dp[N][K][0]
        ==> 范围:
            0<=i<=N (N=arr.length)
            0<=k<=K
            n=0/1
        ==> base case:
            (1) i=0: 第0天, 还未开始
                dp[0][k][0] = 0
                dp[0][k][1] = -INF (还未开始, 不可能持有股票)
            (2) k=0: 最大交易次数为0
                dp[i][0][0] = 0
                dp[i][0][1] = -INF (最多交易0次, 不可能持有股票)

    K=1:
        (1) n=0:
            dp[i][1][0] = max{dp[i-1][1][0], dp[i-1][1][1] + prices[i-1]}
                        = max{dp[i-1][1][0], dp[i-1][1][1] + prices[i-1]}
        (2) n=1:
            dp[i][1][1] = max{dp[i-1][1][1], dp[i-1][0][0] - prices[i-1]}
                        = max{dp[i-1][1][1], -prices[i-1]}
        ==> 最终结果:
            dp[N][1][0]
        ==> 范围:
            0<=i<=N (N=arr.length)
            k=0/1
            n=0/1
        ==> base case:
            (1) i=0: 第0天, 还未开始
                dp[0][1][0] = 0
                dp[0][1][1] = -INF (还未开始, 不可能持有股票)
            (2) k=0: 最大交易次数为0
                dp[i][0][0] = 0
                dp[i][0][1] = -INF (最多交易0次, 不可能持有股票)

        ==> dp数组和K无关, 可以省去k维度
        (1) n=0:
            dp[i][0] = max{dp[i-1][1][0], dp[i-1][1][1] + prices[i-1]}
                        = max{dp[i-1][0], dp[i-1][1] + prices[i-1]}
        (2) n=1:
            dp[i][1] = max{dp[i-1][1][1], dp[i-1][0][0] - prices[i-1]}
                        = max{dp[i-1][1], -prices[i-1]}
        ==> 最终结果:
            dp[N][0]
        ==> 范围:
            0<=i<=N (N=arr.length)
            n=0/1
        ==> base case:
            (1) i=0: 第0天, 还未开始
                dp[0][0] = 0
                dp[0][1] = -INF (还未开始, 不可能持有股票)

     */
    public static int maxProfit1(int[] arr) {

        int[][] dp = new int[arr.length + 1][2];
        // base case
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], -arr[i - 1]);
        }

        return dp[arr.length][0];
    }

    public static int maxProfit2(int[] arr) {

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + arr[i]);
            dp_i_1 = Math.max(dp_i_1, -arr[i]);
        }

        return dp_i_0;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 1, 5, 3, 6, 4};
        int[] arr2 = {7, 6, 4, 3, 1};

        System.out.println(maxProfit1(arr1)); // 5
        System.out.println(maxProfit2(arr1)); // 5
        System.out.println(maxProfit1(arr2)); // 0
        System.out.println(maxProfit2(arr2)); // 0
    }

}
