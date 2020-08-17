package NowCoder.动态规划DP.exercise.普通DP;

import java.util.Arrays;

/*
零钱兑换 I:
    给定k种面值的硬币, 面值为c1,c2,...,ck, 每种硬币数量无限
    给定一个总金额amount
    问 "最少" 需要几枚硬币可以凑出这个金额

    TODO 和advance_code29_CoinsWay不一样
        这里求的是最少需要几枚硬币
        而code29_CoinsWay求的是共有多少种方法, 同code05_FullPackage_CoinMethods
*/
public class code01_MinCoins {

    /*
        TODO 如果考虑用 [贪心] 算法, 可能会找不到最优解
            例如: [1, 7, 10] amount=14
                如果用贪心, 最终结果会是 10, 1, 1, 1
                而最优解应该是 7, 7
                所以需要额外考虑剪枝问题, 比较复杂

        TODO 题目求的是 [最少] 几枚硬币, 和 [完全背包] 不同
             code05_FullPackage_CoinMethods中求组合数, 那么可以只用前几个硬币
             而这里为了凑出最少的硬币数, 肯定要事先知道所有的硬币的面值
             所以考虑 [状态] 时, 不需要考虑当前是前几个硬币
        1. 状态:
            amount总金额
        2. dp数组:
            一维dp数组 dp[n]
            含义: 当总金额是n时, 至少需要dp[n]枚硬币, 才能凑出总金额n
        3. 选择, 写状态转移, 并择优:
            因为硬币数量无限, 所以每次都可以在这k种硬币中进行选择
            ==> 在一个状态下, 就要for循环遍历一次coins数组
            在当前状态的循环中, 对于每个硬币, 都可以选/不选
            (1) 选:
                dp[n] = dp[n - coins[i]] + 1
            (2) 不选:
                dp[n] = dp[n] ==> 无用

            择优: 对于for循环内的每种硬币, 选/不选中选择min较小的

            ==> 最终结果: dp[amount]
            ==> 范围: 0<=amount<=Amount ==> new dp[Amount + 1]
            ==> base case: TODO 由于是找最小值, 所以必须先初始化为最大值
                (1) dp[0] = 0
                (2) n<0时, -1无解 ==> 对于递归函数来说, n就是上一个递归函数的n-coins[i]
        4. 根据状态转移和base case, 确定 遍历方向, 边界问题:
        遍历方向:
            dp[n] 依赖于 dp[n-coins[i]]
        ==> 要先知道前面的, 才能推出后面的
        ==> 从前往后遍历
        边界问题:
        (1) 对于递归函数来说, n就是上一个递归函数的n-coins[i]
            有可能出现 n < 0, -1无解
        (2) 对于dp数组, 有可能出现 coins[i] > n,
            即当前要选的硬币面值大于总金额, 所以直接跳过不选

     */
    // 递归
    public static int minCoin1(int[] coins, int amount) {
        return dp(coins, amount);
    }

    public static int dp(int[] coins, int amount){
        // base case
        if (amount == 0){
            return 0;
        }else if (amount < 0){ // 题目要求找不到组合, 返回-1
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int num = dp(coins, amount - coins[i]);

            if (num == -1){
                continue;
            }

            if (amount >= coins[i]){
                res = Math.min(res, num + 1);
            }
        }

        return res;
    }

    // 动态规划
    public static int minCoin2(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        // base case
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        if (dp[amount] == amount + 1){
            return -1;
        }else {
            return dp[amount];
        }
    }

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};

        System.out.println(minCoin1(coins, 11)); // 3
        System.out.println(minCoin2(coins, 11));

    }

}
