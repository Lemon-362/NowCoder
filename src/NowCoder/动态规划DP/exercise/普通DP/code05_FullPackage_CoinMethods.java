package NowCoder.动态规划DP.exercise.普通DP;

/*
完全背包问题: 零钱兑换
    给定不同面值的硬币和一个总金额
    计算可以凑出总金额的硬币组合数
    TODO 每种硬币可以使用无限次 ==> 完全背包

 */
public class code05_FullPackage_CoinMethods {

    /*
    解析题目:
        给定一组硬币, 一个总金额, 每个硬币可以用无限次
        求凑出总金额的组合数
    ==> 完全背包问题 (因为每个硬币可以用无限次)

        1. 状态:
            可选的硬币种类, 总金额
        2. dp数组:
            二维dp数组 dp[i][j]
            含义: 在前i种硬币, 总金额为j的状态下, 可以凑出总金额j的组合数为dp[i][j]
        3. 选择, 写状态转移, 并择优
            每种硬币都有选/不选, 而硬币可以选择无限次,
            所以就算选了第i个硬币后, 仍然可以继续选第i个硬币, 应该仍然停留在i处
            (1) 不选
                dp[i][j] = dp[i - 1][j]
            (2) 选
                dp[i][j] = dp[i][j - coins[i]]
            ==> 最终结果: dp[coins.length][amount]
            ==> 范围: 0<=i<=coins.length, 0<=j<=amount ==> new dp[coins.length+1][amount+1]
            ==> base case:
                (1) dp[0][...] = 没有硬币可选 = 0
                (2) dp[...][0] = 总金额为0 = 不选这一种方案 = 1
            ==> 改进方程:
                (1) 不选
                    dp[i][j] = dp[i - 1][j]
                (2) 选
                    dp[i][j] = dp[i][j - coins[i-1]]
        4. 根据状态转移和base case, 确定遍历方向, 边界问题
        遍历方向:
            dp[i][j] 依赖于 dp[i-1][j], dp[i][j-coins[i-1]]
        ==> 需要先知道 上方,左边 的结果
        ==> 正向遍历
        边界问题:
            coins[i-1] > j: 直接不选

     */
    public static int coinMethods1(int[] coins, int amount) {
        return dp(coins, coins.length, amount);
    }

    public static int dp(int[] coins, int index, int amount) {
        // base case
        if (index == 0) {
            return 0;
        }
        if (amount == 0) {
            return 1;
        }

        // 1
        if (amount >= coins[index - 1]) {
            return dp(coins, index - 1, amount)
                    + dp(coins, index, amount - coins[index - 1]);
        } else {
            return dp(coins, index - 1, amount);
        }
    }

    public static int coinMethods2(int[] coins, int amount) {

        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        // base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 1
                if (j >= coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[N][amount];
    }

    // dp再优化: 一维滚动数组
    public static int coinMethods3(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        // base case
        dp[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            // TODO 对比code04_01Package_CanPartition的滚动数组
            // 这里由于可以选择无限次, 所以可以直接更新掉之前的数
            // 用之前更新的数来更新本次结果, 表示: 之前的选择次数 + 1(本次选择)
            for (int j = 1; j < dp.length; j++) {
                if (j >= coins[i]) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};

        System.out.println(coinMethods1(coins, 5)); // 4
        System.out.println(coinMethods2(coins, 5)); // 4
        System.out.println(coinMethods3(coins, 5)); // 4
    }
}
