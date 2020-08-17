package NowCoder.动态规划DP.DP;

import java.util.Arrays;
import java.util.HashMap;

/* 凑零钱问题:
    给定k种面值的硬币, 面值为c1,c2,...,ck, 每种硬币数量无限
    给定一个总金额amount
    问 "最少" 需要几枚硬币可以凑出这个金额

    TODO 和advance_code29_CoinsWay不一样
        这里求的是最少需要几枚硬币
        而code29_CoinsWay求的是共有多少种方法
*/
public class code01_MinCoins {

    /*
    1. 暴力递归: 将所有可能的凑硬币方法都穷举出来, 然后选择最少的硬币个数

       确定状态转移方程:
        (1) 明确 "状态": 原问题和子问题种变化的变量 ==> TODO 通过哪几个变量, 可以描述这个问题
            由于硬币数量无限, 所以只有 "目标金额amount" 是变化的
            ==> 状态: "目标金额amount"
        (2) 根据 "状态" 确定dp函数的含义
            dp(n): 表示当前金额是n(状态), 最少需要 "dp(n)" 个硬币可以凑出这个金额
        (3) 明确 "选择", 并 "择优": 对于每个状态, 做出什么选择可以改变当前状态
            无论当前金额(状态)是什么, 都需要从 "面值数组coins" 中选择一个硬币, 然后当前金额减少
        (4) 明确 "base case"
            当 "目标金额amount"=0 时, dp(0)=0
            当 "目标金额amount"<0 时, -1无解

        ==> 状态转移方程:
            dp(n) = 0, n==0
                    -1, n<0
                    min{ [dp(n-coin) + 1] | coin∈coins}, n>0

            TODO 注意:
                这里的思想是指 当amount==0时, 只需要0个硬币,
                因为在dp递归函数中, 用+1表示了当前状态的选择方案所需硬币的个数
                而原来写的递归是指 当amount==0时, 就找到了一种方案, 会返回1
                在dp递归函数中, 不会+1

        时间复杂度:
            子问题个数 = O(N^k)
            一个子问题的时间复杂度 = 一个子问题就有一个for循环 = O(k)
        ==> O(k * N^k)
     */
    // 1. 暴力递归
    public static int minCoin1(int[] coins, int amount) {
        return dp1(coins, amount);
    }

    public static int dp1(int[] coins, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        } else if (amount < 0) {
            return -1;
        }

        // 当前状态: 从 "面值数组coins" 种选择一个硬币, 然后当前金额减少
        int res = Integer.MAX_VALUE;
        // 遍历coins数组, 每次选出一种硬币, 然后向下递归
        // 在所有可能性中, 选出最小值
        for (int i = 0; i < coins.length; i++) {
            // 选出coins[i]的硬币 ==> +1
            // 剩下amount - coins[i]的面值的最优解 由子问题处理交给我
            int subProblem = dp1(coins, amount - coins[i]);
            // 子问题无解, 直接跳过
            if (subProblem == -1) {
                continue;
            }
            // 当前问题的最优解
            res = Math.min(res, 1 + subProblem);
        }

        return res;
    }

    /*
    2. 带备忘录的递归
        由于暴力递归中存在大量的重叠子问题
        所以可以使用 "备忘录" 来记录每种金额所需的最少硬币数

        时间复杂度:
            子问题个数不会超过金额总数 = O(N)
            一个子问题的时间复杂度 = 一个子问题就有一个for循环 = O(k)
        ==> O(k * N)

     */
    // 2. 带备忘录的递归
    public static int minCoin2(int[] coins, int amount) {
        return dp2(coins, amount);
    }

    // 备忘录, 也可以用arr数组来记录
    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static int dp2(int[] coins, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        } else if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subProblem = dp2(coins, amount - coins[i]);
            if (subProblem == -1) {
                continue;
            }
            res = Math.min(res, 1 + subProblem);
        }

        // 将当前状态的最优解记录到备忘录中
        // 当前状态: 金额为 "amount" 所需的最少硬币数
        if (res != Integer.MAX_VALUE) {
            map.put(amount, res);
        }

        return res;
    }

    /*
    3. 动态规划(dp数组的迭代)
        根据态转移方程:
            dp(n) = 0, n==0
                    -1, n<0
                    min{ [dp(n-coin) + 1] | coin∈coins}, n>0
        可以直接从 dp(0)=0, 向上推到更大规模的子问题, 直到原问题

     */
    // 3. 动态规划(dp数组的迭代)
    public static int minCoin3(int[] coins, int amount) {
        // 每个子问题(金额为amount时最少硬币数)的最优解
        int[] dp = new int[amount + 1];
        // 需要全部初始化为最大值
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        // 遍历dp数组, 找每个子问题的最优解
        for (int i = 0; i < dp.length; i++) {
            // 当前子问题: 金额为i时最少硬币数dp[i]
            for (int j = 0; j < coins.length; j++) {
                // 无解: 选出的coins[j]硬币面值已经大于了所需金额i
                if (coins[j] > i) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
            }
        }

        // 最终目标: dp[amount]
        if (dp[amount] == amount + 1){
            dp[amount] = -1;
        }

        return dp[amount];
    }

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};

        System.out.println(minCoin1(coins, 11)); // 3
        System.out.println(minCoin2(coins, 11));
        System.out.println(minCoin3(coins, 11));

    }
}
