package NowCoder.动态规划DP.DP;

import java.util.HashMap;

/*
    动态规划详解
 */
public class code01_DP_Summary {

    /*
    1. 遇到 "求最值" 问题 ==> 动态规划
    2. 动态规划的核心问题:
        TODO 穷举
        在穷举过程中如果出现 "重叠子问题", 那么可以考虑使用 "备忘录"/"DP数组" 来优化
    3. 动态规划的必要条件:
        TODO 必须具有 "最优子结构"
    4. 动态规划的三要素:
        (1) 重叠子问题
        (2) 最优子结构
        (3) 状态转移方程
    5. TODO 动态规划的流程:
           暴⼒的递归解法 (自顶向下, 从原问题一步步推到子问题, 直到可以直接直到子问题结果时, 再一步步向上返回)
        -> 带备忘录的递归解法
        -> 迭代的动态规划解法 (自底向上, 直接从已知子问题的结果, 向上推到原问题)
    6. TODO 状态转移方程的流程:
        (1) 明确 "状态": 原问题和子问题种变化的变量
        (2) 确定dp函数的含义
        (3) 明确 "选择", 并 "择优": 对于每个状态, 做出什么选择可以改变当前状态
        (4) 明确 "base case"
    7. TODO 最优子结构:
        子问题之间必须是独立的,
        更大规模的问题的最优解一定出自某个子问题内部的最优解, 而不是多个子问题的"交集"

        例如:
        1. 具有最优子结构:
            已知每个班级的最高分(子问题), 要求全校的最高分(原问题)
            只需要在所有子问题中选出最大值, 其结果就是原问题的最优解
        2. 不具有最优子结构:
            已知每个班级的最大分数差(子问题), 要求全校的最大分数差(原问题)
            此时最大分数差不一定出现在这些子问题中, 可能是一个班的最高分 - 另一个班的最低分
            这个最优解并不一定在子问题内部

        TODO 如何具有最优子结构 ==> 改造问题
        最大分数差 == 最高分 - 最低分
        分别求出原问题的最高分和最低分(即例1的最优解), 两者相减就是最大分数差

    8. 递归的时间复杂度 = 子问题个数(递归树的节点个数) * 解决一个字问题所需的时间

    TODO 在考虑动态规划 4步流程 时:
        是假设 [i-1] 之前的子问题都计算好了, 现在用之前计算好的结果, 来计算 [i] 的子问题
        所以实际上思考的过程类似于 自顶向下(递归)
        而实际写动态规划时, 是 自底向上
        ==>
            (1) 思考流程时, 自顶向下, 假设之前的结果都计算好了, 用他们来计算当前问题
            (2) 写递归时, 自顶向下, 直接按照流程往下(往前)写, 直到可以直接给出结果(即base case)时,
                递归函数自己一步步返回
            (3) 写动态规划时, 自底向上, 从base case一步步往上(往后)推导

     */

    /*
    TODO 字符串/子序列问题的解题模板
        ==> (1) 子序列问题可能涉及到一个字符串, 也可能涉及到两个字符串
            (2) 一般是要求 [最长] 子序列, 一般都用动态规划方法, 时间复杂度为O(N^2)

        思路1:
            一维dp数组
        ==> dp[i]的含义: 以arr[i]结尾的子串/子数组(连续) arr[0,...,i]中, 要求的子序列长度是dp[i]
                        即, 对字符串向后扩
        ==> 前提: dp[0,...,i-1]的结果已知
        ==> TODO 典型题目: code02_LongestIncreasingSubSequence最长递增子序列
                为什么定义成一维dp数组?
                因为递增子序列只需要看 当前字符 和 前一个字符的关系, 只需要一个指针指向当前字符即可

            int n = arr.length;
            int[] dp = new int[n];
            // TODO 可能需要全局变量来找最值
            int res = Integer.MIN_VALUE;

            for(int i = 0; i < n; i++){
                for(int j = 0; j < i){
                    dp[i] = Math.max(dp[i], dp[j] + ...)
                }
                res = Math.max(res, dp[i]);
            }

            return res;

        思路2:
            二维dp数组
        ==> 可能涉及到给定的是一个或两个字符串

        (1) 只给了一个字符串
        ==> dp[i][j]的含义: 在子串/子数组 arr[i,...,j]中, 要求的子序列长度是dp[i][j]
                           即, 从一个字符的时候开始向外扩
        ==> 前提: dp[i+1][j-1]及其以内的都已知, 即arr[i+1,...,j-1]往里的都已知
        ==> TODO 典型题目: code09_LongestHuiWenSubSequence最长回文子序列

        (2) 给了两个字符串
        ==> dp[i][j]的含义: 在子串 arr1[0,...,i]和arr2[0,...,j]中, 要求的子序列长度是dp[i][j]
                           即, 类似于一维dp数组的含义, 对两个字符串同时向后扩
        ==> 前提: dp[i-1][j-1]及其之前的都已知, 即arr1[0,...,i-1]和arr2[0,...,j-1]都已知
        ==> TODO 典型题目: code06_LongestCommonSubSequence最长公共子序列

            int m = arr1.length;
            int n = arr2.length;
            int[][] dp = new int[m+1][n+1];

            for(int i = 0; i <= m; i++){
                for(int j = 0; j <= n; j++){
                    if(arr1[i] == arr2[j]){
                    // TODO 不一定是 dp[i-1][j-1] 状态转移过来的
                        dp[i][j] = dp[i-1][j-1] + ...
                    }else{
                        dp[i][j] = 最值(状态转移...)
                    }
                }
            }
     */

    public static void test() {}

}
