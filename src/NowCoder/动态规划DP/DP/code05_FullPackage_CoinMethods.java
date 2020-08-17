package NowCoder.动态规划DP.DP;

/*
完全背包问题: 零钱兑换
    给定不同面值的硬币和一个总金额
    计算可以凑出总金额的硬币组合数
    TODO 每种硬币可以使用无限次 ==> 完全背包

 */
public class code05_FullPackage_CoinMethods {

    /*
    转换成背包问题:
        TODO 给定一个背包, 容量是amount(总金额), 有一系列物品coins
            每个物品体积是coins[i], 且物品可以用无限次
            问有多少种方法可以将背包装满

        1. [状态] [选择]
            同01背包
            背包容量, 可选的物品
            装进/不装
        2. dp数组的含义
            dp[i][j]: 对于前i个物品, 当前背包容量是j, 这种情况下可以装满的总方法数为dp[i][j]
        ==> 最终结果: dp[N][amount]
            base case:
            (1) dp[...][0]=1
                当背包体积是0的时候, 不需要选择物品, 只有这一种方法可以装满
            (2) dp[0][...]=0
                当可选物品是0个时, 无法装满背包
        3. 状态转移
            (1) 不装: dp[i][j] = dp[i-1][j]
            (2) 装: dp[i][j] = dp[i][j-arr[i-1]]
            TODO 注意:
                由于硬币数可以选择无限次, 所以本次如果选择装进背包, 那么下一次仍然可以选择该物品
                所以, 完全背包的 dp[i][j] = dp[i][...]
                而对比01背包, 只能选择一次, 本轮选了之后, 只能在剩下的 i-1 个物品中选择
                所以, 01背包的 dp[i][j] = dp[i-1][...]
            注意, 由于遍历时从 [i=1] 开始, 而对于arr来说应该是第一个物品, 即 [i-1=1-1=0]
            ==> TODO 由于要求的是总方法数, 所以应该将两者相加
        4. 边界问题
            j < arr[i - 1]时, 直接不装

     */
    public static int coinMethods1(int[] coins, int amount) {

        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        // base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[N][amount];
    }

    /*
    优化: 一维dp数组
        TODO 注意:
            由于每个物品可以选择无限次
            所以在一维更新时, 应该从前往后更新, 这样才能叠加之前的状态

     */
    public static int coinMethods2(int[] coins, int amount) {

        int N = coins.length;
        int[] dp = new int[amount + 1];
        // base case: 一维只表示j体积, 只在体积j=0时, 才有base case
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};

        System.out.println(coinMethods1(coins, 5)); // 4
        System.out.println(coinMethods2(coins, 5)); // 4
    }

}
