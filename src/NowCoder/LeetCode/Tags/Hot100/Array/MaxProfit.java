package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.HashMap;

/*
121. 买卖股票的最佳时机 I
    给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
    如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），
    设计一个算法来计算你所能获取的最大利润。
    注意：你不能在买入股票前卖出股票。

 */
public class MaxProfit {

    /*
    方法一: 暴力法
        题目要求的是: 左边<右边, 且差值最大
        那么就找每个位置上右边比它大, 且差值最大的值

        时间复杂度: O(N^2)
     */
    public static int maxProfit1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int res = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    int num = arr[j] - arr[i];
                    res = Math.max(res, num);
                }
            }
        }

        return res;
    }

    /*
    方法二: 贪心
        从题意出发, 只需要找到当前位置之前的历史最低点, 那么那个点就是买入的时候

        时间复杂度: O(N)
     */
    public static int maxProfit2(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }

        // 记录之前的最低点价格
        int min = arr[0];
        int res = 0;

        for (int i = 1; i < arr.length; i++) {
            res = Math.max(res, arr[i] - min);
            // 更新i位置之前的历史最低点价格, 即买入时刻
            min = Math.min(min, arr[i]);
        }

        return res;
    }

    /*
    方法三: 动态规划
        (1) 多阶段最优化问题
        (2) 无后效性: 前面已确定的状态不会因为后面的状态而改变

        1. f(i, j): 表示在第i天, 用户状态为j的时候, 获得的最大利润
        ==> i: 0 ~ N-1
            j: 0(卖出), 1(买入)
        TODO "0卖出"之后不能转移到"1买入", 且限制了只能交易一次
            即状态转移的可能只有 0->0, 1->0, 1->1

        2. TODO 状态转移: dp[i][j]是如何从dp[i-1][j]转移过来的
                首先考虑可以从"i-1"的什么状态转移过来
                然后考虑从"i-1"怎么转移过来("i"的时候做了什么事)

        - 对于dp[i][0]: 第i天卖出的利润
            (1) dp[i][0] = dp[i - 1][0] ==> 第i-1天卖出, 第i天不操作
            (2) dp[i][0] = dp[i - 1][1] + prices[i] ==> 第i-1天买入, 第i天卖出
            ==> 取最大值

        - 对于dp[i][1]:
            (1) dp[i][1] = dp[i - 1][1] ==> 第i-1天买入, 第i天不操作
            (2) dp[i][1] = -prices[i]
            ==> 由于限制了交易一次, 且不能先卖再买,
                所以如果第i天买入, 那么只能算上当天买入后的利润,
                而不能加上第i-1天的利润
            ==> 取最大值

        3. 初始化:
        (1) dp[0][0] = 0 ==> 第一天不能卖出
        (2) dp[0][1] = -prices[0] ==> 第一天可以买入

        4. 输出:
            由于每一天的状态都考虑了之前的最优状态, 所以最后一天一定是最优状态
            并且最后一天买入会 -prices, 所以一定是最后一天卖出的状态下是最大值
            而不需要在循环内找每一次的最大值

        时间复杂度: O(N)
        空间复杂度: O(N)
     */
    public static int maxProfit3(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int[][] dp = new int[arr.length][2];

        dp[0][0] = 0;
        dp[0][1] = -arr[0];
//        int res = 0;

        for (int i = 1; i < arr.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -arr[i]);
//            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }

//        return res;
        return dp[arr.length - 1][0];
    }

    /*
    方法四: 动态规划的优化(参考01背包的空间优化)

        - 对于dp[i][0]:
            (1) dp[i][0] = dp[i - 1][0] ==> 第i-1天卖出, 第i天不操作
            (2) dp[i][0] = dp[i - 1][1] + prices[i] ==> 第i-1天买入, 第i天卖出
        - 对于dp[i][1]:
            (1) dp[i][1] = dp[i - 1][1] ==> 第i-1天买入, 第i天不操作
            (2) dp[i][1] = -prices[i]

        从状态转移方程中可以看出, 第i天的状态只和第i-1天的状态有关
        所以可以进行降维处理, 仅利用前一次计算出来且已保存在数组中的值, 来更新当前时刻的值

        时间复杂度: O(N)
        空间复杂度: O(1)
     */
    public static int maxProfit4(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int[] dp = new int[2];

        dp[0] = 0;
        dp[1] = -arr[0];

        for (int i = 1; i < arr.length; i++) {
            dp[0] = Math.max(dp[0], dp[1] + arr[i]);
            dp[1] = Math.max(dp[1], -arr[i]);
        }

        return dp[0];
    }

    public static void main(String[] args) {

        int[] arr1 = {7, 1, 5, 3, 6, 4};
        int[] arr2 = {7, 6, 4, 3, 1};

        System.out.println(maxProfit1(arr1)); // 5
        System.out.println(maxProfit2(arr1)); // 5
        System.out.println(maxProfit3(arr1)); // 5
        System.out.println(maxProfit4(arr1)); // 5

        System.out.println(maxProfit1(arr2)); // 0
        System.out.println(maxProfit2(arr2)); // 0
        System.out.println(maxProfit3(arr2)); // 0
        System.out.println(maxProfit4(arr2)); // 0

    }
}
