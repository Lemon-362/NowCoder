package NowCoder.动态规划DP.股票买卖;

/*
股票买卖1:
    一个数组, arr[i]表示一只股票, 在第i天的价格
    最多可以完成k笔交易, 求可以获得的最大利润
    TODO k=1
 */
public class code01_MaxProfit_k1 {

    /*
    交易次数为K:
        1. 状态:
            天数(第几天), 允许交易的最大次数K, 当前持有股票的状态(rest=0/1)
        2. dp数组:
            三维dp数组 dp[i][k][n]
            含义: 今天是第i天, 最大交易次数是k, 我现在持有股票状态是n, 那么可以获得的最大利润是dp[i][k][n]
            ==> 0<=i<=arr.length-1
                1<=k<=K
                n=0/1
        3. 选择:
            对于当天的状态下, 有三种选择
            (1) 买入buy
            (2) 卖出sell
            (3) 不操作rest
            状态转移: 对于持有状态n=0/1, 可以发现转移规律
                (1) n=0当天不持有股票:
                    - 昨天不持有股票, 今天不操作rest ==> dp[i-1][k][0]
                    - 昨天持有股票, 今天卖出sell ==> dp[i-1][k][1] + prices[i]
                (2) n=1当天持有股票:
                    - 昨天持有股票, 今天不操作rest ==> dp[i-1][k][1]
                    - 昨天不持有股票, 今天买入buy ==> dp[i-1][k-1][0] - prices[i]
                    TODO 由于一次交易是由买和卖构成, 所以只需要在一个持有状态下交易数 -1 即可
            ==> 方程:
                (1) n = 0:
                    dp[i][k][0] = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i]}
                (2) n = 1:
                    dp[i][k][1] = max{dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]}
            ==> 最终结果:
                因为在最后一天, 也可能是持有/不持有, 但是如果持有n=1, 则表示买入, 肯定利润要减小
                dp[arr.length-1][K][0]
            ==> 范围:
                0<=i<=arr.length-1
                0<=k<=K
                n=0/1
            ==> base case:
                (1) i=0时, 表示还没有开始, 第0天:
                    - n=0时, 表示不持有, 最大利润是0
                        dp[0][k][0] = 0
                    - n=1时, 表示持有, 但是又没开始交易, 不可能持有, 所以最大利润是-∞
                        dp[0][k][1] = -INF
                (2) k=0时, 表示最多交易次数是0:
                    - n=0时, 表示不持有, 最大利润是0
                        dp[i][0][0] = 0
                    - n=1时, 表示持有, 但交易次数为0, 不可能持有, 所以最大利润是-∞
                        dp[i][0][1] = -INF
            ==> 改进:
                由于base case中, i=0表示的是第0天, 而对于arr来说是第一个位置
                所以i的范围需要加一个
                TODO 0<=i<=arr.length
                (1) n = 0:
                    dp[i][k][0] = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i-1]}
                (2) n = 1:
                    dp[i][k][1] = max{dp[i-1][k][1], dp[i-1][k-1][0] - prices[i-1]}

        TODO 模板:
            1. 状态转移方程:
                (1) n=0:
                    dp[i][k][0] = {昨天不持有今天不操作, 昨天持有今天卖出}
                                = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i-1]}
                (2) n=1:
                    dp[i][k][1] = {昨天持有今天不操作, 昨天不持有今天买入}
                                = max{dp[i-1][k][1], dp[i-1][k-1][0] - prices[i-1]}
            2. 最终结果:
                dp[N][K][0]
            3. 范围:
                0<=i<=N (N=arr.length)
                0<=k<=K
                n=0/1
            4. base case:
                (1) i=0: 第0天, 还未开始
                    dp[0][k][0] = 0
                    dp[0][k][1] = -INF (还未开始, 不可能持有股票)
                (2) k=0: 最大交易次数为0
                    dp[i][0][0] = 0
                    dp[i][0][1] = -INF (最多交易0次, 不可能持有股票)
     */

    /*
    K=1:
        base case:
            dp[0][1][0] = 0
            dp[0][1][1] = -INF
            dp[i][0][0] = 0
            dp[i][0][1] = -INF
        状态转移:
            (1) n = 0:
                dp[i][1][0] = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i-1]}
                            = max{dp[i-1][1][0], dp[i-1][1][1] + prices[i-1]}
            (2) n = 1:
                dp[i][1][1] = max{dp[i-1][k][1], dp[i-1][k-1][0] - prices[i-1]}
                            = max{dp[i-1][1][1], dp[i-1][0][0] - prices[i-1]} (dp[i][0][0] = 0)
                            = max{dp[i-1][1][1], -prices[i-1]}
        从状态转移中可以看出, K始终为1, K=0的已经被base case取代了
        所以K这个维度可以省去, 对状态转移没有影响
        ==>
        base case:
            dp[0][0] = 0
            dp[0][1] = -INF
        状态转移:
            (1) n = 0:
                dp[i][0] = max{dp[i-1][0], dp[i-1][1] + prices[i-1]}
            (2) n = 1:
                dp[i][1] = max{dp[i-1][1], -prices[i-1]}
        范围:
            0<=i<=arr.length
            n=0/1
     */
    public static int maxProfit1(int[] arr){

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

    // dp优化: dp[i][0]和dp[i][1]之和之前一个状态有关, 所以可以用两个变量来存储
    public static int maxProfit2(int[] arr){

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
