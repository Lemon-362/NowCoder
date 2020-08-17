package NowCoder.动态规划DP.DP;

import java.util.Arrays;

/*
最长递增子序列:
    给定一个无序数组,找到其中最长上升子序列的长度
    TODO
        子序列: 不需要连续
        子串: 一定要连续

 */
public class code02_LongestIncreasingSubSequence {

    /*
    TODO 动态规划的核心设计思想:
        数学归纳法,
        即假设dp[0,...,i-1]都计算出来了, 如何通过这些结果计算出dp[i]

        1. 状态
            递增子序列的长度
        2. dp函数的含义
            dp[i]: 以arr[i]这个数结尾的最长递增子序列的长度
        3. "选择", 并 "择优"
        (1) 从所有dp中选出最大的
        (2) 对于dp[i], 应该看以arr[i]结尾的最长长度,
            而要满足递增子序列, 就必须要求前面的数 < arr[i]
            ==> TODO 遍历之前的arr, 只要有一个arr[j] < arr[i], 那么就可以使用j位置处的dp解
                 dp[i] = dp[j] + 1(自己)
        4. base case
            所有dp都应该初始化为1, 因为最短的递增子序列可以是自己

    TODO 注意点
        1. 要明确dp数组所存数据的含义
        2. 根据dp数组的定义, 运用数学归纳法的思想, 想办法通过dp[0,..i-1]求出dp[i], 即找出状态转移方程
        3. 如果无法求出dp[i], 有可能是
            (1) dp数组定义不够恰当
            (2) dp数组存储的信息不够多, 无法推出下一步的答案

     */
    public static int longestIncreasingSubSequence(int[] arr) {

        // 以每个位置结尾的结果
        int[] dp = new int[arr.length];
        // 全部初始化为1
        Arrays.fill(dp, 1);
        int res = Integer.MIN_VALUE;

        // 以每个位置结尾
        for (int i = 0; i < arr.length; i++) {
            // 遍历i位置之前的数组, 如果有一个arr[j] < arr[i], 就可以使用j位置处的dp解, 并加上1(自己)
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static void main(String[] args) {

        int[] arr = {1, 4, 3, 4, 2, 3};
        System.out.println(longestIncreasingSubSequence(arr)); // 3

    }

}
