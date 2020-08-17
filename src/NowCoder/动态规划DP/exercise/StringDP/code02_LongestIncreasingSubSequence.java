package NowCoder.动态规划DP.exercise.StringDP;

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
    子序列问题 ==> 动态规划
        给了一个数组
    ==> 由于要判断是否是上升的子序列, 所以只要判断当前状态下,
        i位置的元素是否 大于 前面一个位置的元素
    ==> 一个指针即可
    ==> 一维dp数组

        1. 状态:
            每个位置的元素
        2. dp数组:
            一维dp数组 dp[i]
            含义: TODO 以i位置结尾的子串(子数组)中, 最长上升子序列的长度为dp[i]
        3. 选择, 写状态转移, 并择优
            对于当前状态下, 只需要判断当前位置的元素是否 > 之前位置的数
        ==> 遍历i位置之前的所有数, 遇到一个 小于 arr[i]的, 就可以用该位置的最优解 + 1(当前位置)
        ==> for(int j = 0; j < i; j++){
                if(arr[j] < arr[i]){
                    dp[i] = dp[j] + 1;
                }
            }
        ==> 从之前所有位置中选择max最大值
        ==> 最终结果:
            TODO 由于最长上升子序列不一定是在数组末尾时有
                可能在数组内部就出现了, 所以要用全局变量来寻找以每个位置结尾的结果中的最大值
            用一个全局变量res来找以每个位置结尾的dp[i]中最大的
        ==> 范围:
            0<=i<=arr.length-1 ==> new dp[arr.length]
        ==> base case:
            对于每个元素, 子集就可以是一个满足条件的子序列
            所以应该都初始化为1
        4. 根据状态转移和base case确定遍历方向, 边界问题

     */
    // 动态规划可以直接写出来
    public static int longestIncreasingSubSequence1(int[] arr) {

        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        int res = 0;
        // 以每个位置结尾
        for (int i = 0; i < arr.length; i++) {
            // 在i位置之前找构成最长的上升子序列
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

        int[] arr = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(longestIncreasingSubSequence1(arr)); // 6

    }

}
