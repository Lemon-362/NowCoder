package NowCoder.动态规划DP.exercise.股票买卖;

/*
股票买卖1:
    一个数组, arr[i]表示一只股票, 在第i天的价格
    最多可以完成k笔交易, 求可以获得的最大利润
    TODO k=INF 无限次交易
 */
public class code02_MaxProfit_kINF {

    /*
    模板:
        (1) n=0:
            dp[i][k][0] = {昨天不持有今天不操作, 昨天持有今天卖出}
                        = max{dp[i-1][k][0], dp[i-1][k][1] + prices[i-1]}
        (2) n=1:
            dp[i][k][1] = {昨天持有今天不操作, 昨天不持有今天买入}
                        = max{dp[i-1][k][1], dp[i-1][k-1][0] - prices[i-1]}
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
    */

    /*
    K=INF:
        k-1 和 k 都趋向于无穷, 所以dp数组和k维度无关, 但是不能根据base case省去dp[i-1][k-1][0]
        (1) n=0:
            dp[i][0] = max{dp[i-1][0], dp[i-1][1] + prices[i-1]}
        (2) n=1:
            dp[i][1] = max{dp[i-1][1], dp[i-1][0] - prices[i-1]}
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
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - arr[i - 1]);
        }

        return dp[arr.length][0];
    }

    // dp优化: dp[i][0]和dp[i][1]之和之前一个状态有关, 所以可以用两个变量来存储
    public static int maxProfit2(int[] arr) {

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + arr[i]);
            dp_i_1 = Math.max(dp_i_1, temp - arr[i]);
        }

        return dp_i_0;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 1, 5, 3, 6, 4};
        int[] arr2 = {1, 2, 3, 4, 5};

        System.out.println(maxProfit1(arr1)); // 7
        System.out.println(maxProfit2(arr1)); // 7
        System.out.println(maxProfit1(arr2)); // 4
        System.out.println(maxProfit2(arr2)); // 4
    }
}
