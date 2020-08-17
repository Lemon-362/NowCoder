package NowCoder.动态规划DP.exercise.普通DP;

/*
01背包问题: TODO 每个物品只能使用一次, 1: 选择, 0: 不选择
    有 N 件物品和一个容量是 W 的背包。每件物品只能使用一次。
    第 i 件物品的体积是 wt[i]，价值是 val[i]。
    求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
    输出最大价值。

 */
public class code03_01Package {

    /*
        1. 状态:
            给定i个物品, 背包容量为w, 求装满w时的最大价值
        ==> (1) 物品个数
            (2) 背包容量
        2. dp数组:
            二维dp数组 dp[i][w]
            含义: 在前i个物品中, 背包容量是w时, 装满w的最大价值是dp[i][w]
        3. 选择, 写状态转移, 并择优
            由于物品只能使用一次, 所以对于每个物品, 只有选或不选两种可能
            (1) 选第i个物品:
                dp[i][w] = dp[i - 1][w - wt[i]] + val[i]
                    ==> 在前i-1个物品, 背包容量是w-wt[i]时的最优解, 转移过来
            (2) 不选第i个物品:
                dp[i][w] = dp[i - 1][w]
            ==> 最终结果: dp[N][W]
            ==> 范围: 0<=i<=N, 0<=w<=W ==> new dp[N + 1][W + 1]
            ==> base case:
                (1) dp[0][...] = 没有物品选择 = 0
                (2) dp[...][0] = 背包容量为0 = 0
            ==> 注意: 由于base case定义的是0, 所以i=1表示的是第一个物品,
                而对于wt和val数组来说, 第一个物品应该是索引为0,
                所以需要改进状态转移方程
            ==> (1) 选第i个物品:
                    dp[i][w] = dp[i - 1][w - wt[i-1]] + val[i-1]
                    ==> 在前i-1个物品, 背包容量是w-wt[i-1]时的最优解, 转移过来
                (2) 不选第i个物品:
                    dp[i][w] = dp[i - 1][w]
            ==> 要求最大价值, 所以两者选max较大的
        4. 根据状态转移和base case, 确定 遍历方向, 边界问题:
        遍历方向:
            dp[i][w] 依赖于 dp[i-1][w], dp[i-1][w-wt[i]]
        ==> 需要先知道 上方, 左上方 的信息
        ==> 正向遍历即可
        边界问题:
            当前物品的体积 > 总体积时, 直接不选
        ==> wt[i - 1] > w: dp[i][w] = dp[i - 1][w]
     */
    // 递归
    public static int package01Problem1(int[] wt, int[] val, int N, int W) {
        return dp(wt, val, N, W);
    }

    public static int dp(int[] wt, int[] val, int i, int j) {
        // base case
        if (i == 0) {
            return 0;
        }
        if (j == 0) {
            return 0;
        }

        // 1
        int res1 = dp(wt, val, i - 1, j);
        // 2
        int res2 = 0;
        if (j >= wt[i - 1]) {
            res2 = dp(wt, val, i - 1, j - wt[i - 1]) + val[i - 1];
        }

        return Math.max(res1, res2);
    }

    // 动态规划
    public static int package01Problem2(int[] wt, int[] val, int N, int W) {

        int[][] dp = new int[N + 1][W + 1];
        // base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 1
                dp[i][j] = dp[i - 1][j];
                // 2
                if (j >= wt[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - wt[i - 1]] + val[i - 1]);
                }
            }
        }

        return dp[N][W];
    }

    // 优化
    public static int package01Problem3(int[] wt, int[] val, int N, int W) {

        int[] dp = new int[W + 1];
        // base case
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = W; j >= 1; j--) {
                // 1
//                dp[j] = dp[j];
                // 2
                if (j >= wt[i]){
                    dp[j] = Math.max(dp[j], dp[j - wt[i]] + val[i]);
                }
            }
        }

        return dp[W];
    }

    public static void main(String[] args) {

        int[] wt1 = {2, 1, 3};
        int[] val1 = {4, 2, 3};
        System.out.println(package01Problem1(wt1, val1, 3, 4)); // 6
        System.out.println(package01Problem2(wt1, val1, 3, 4)); // 6
        System.out.println(package01Problem3(wt1, val1, 3, 4)); // 6

        int[] wt2 = {1, 2, 3, 4};
        int[] val2 = {2, 4, 4, 5};
        System.out.println(package01Problem1(wt2, val2, 4, 5)); // 8
        System.out.println(package01Problem2(wt2, val2, 4, 5)); // 8
        System.out.println(package01Problem3(wt2, val2, 4, 5)); // 8

    }
}
