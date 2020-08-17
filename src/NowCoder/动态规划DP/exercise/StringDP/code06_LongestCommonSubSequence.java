package NowCoder.动态规划DP.exercise.StringDP;

/*
最长公共子序列LCS:
    给定两个字符串, 求最长公共子序列的长度
    TODO 注意:
        (1) 子序列: 不要求连续
        (2) 子序列问题: 通常都是用 [动态规划] 来解决

 */
public class code06_LongestCommonSubSequence {

    /*
    子序列问题 ==> 动态规划
        给了两个字符串 ==> 二维dp数组

        TODO 和code02_LongestIncreasingSubSequence最长上升子序列对比:
            code02_LongestIncreasingSubSequence的dp[i]表示 以arr[i]结尾的最长上升子序列
            所以如果最终结果是 dp[arr.length] 的话, 仅仅表示以最后一个位置结尾的最长上升子序列
            而真正的结果可能不包含最后一个位置

        1. 状态:
            子串s1, 子串s2
        2. dp数组:
            二维dp数组 dp[i][j]
            含义: 在子串 s1[0,...,i]和s2[0,...,j] 中, 最长公共子序列长度是dp[i][j]
        3. 选择, 写状态转移, 并择优:
            当前状态下, 只需要判断s1[i]和s2[j]是否相等即可
            (1) s1[i] == s2[j]:
                dp[i][j] = dp[i-1][j-1] + 1
            (2) s1[i] != s2[j]:
                如果两者不相同, 那么说明不可能同时出现在最长公共子序列中, 也可能都不在
                只能一边移动, 选择max较大的
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
            ==> 最终结果: dp[s1.length-1][s2.length-1]
            ==> 范围: 0<=i<=s1.length-1, 0<=j<=s2.length-1 ==> new dp[s1.length][s2.length]
            ==> base case:
                (1) dp[0][...]=0;
                (2) dp[...][0]=0;
            ==> 改进:
                由于base case中0位置表示的是s1/s2为空串
                而对于s1/s2来说0位置应该是第一个字符, 所以实际上0位置对应于i和j应该是1
            ==> 范围: 0<=i<=s1.length, 0<=j<=s2.length ==> new dp[s1.length+1][s2.length+1]
            ==> 最终结果: dp[s1.length][s2.length]
                (1) s1[i-1] == s2[j-1]:
                    dp[i][j] = dp[i-1][j-1] + 1
                (2) s1[i-1] != s2[j-1]:
                    如果两者不相同, 那么说明不可能同时出现在最长公共子序列中, 也可能都不在
                    只能一边移动, 选择max较大的
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
        4. 遍历方向, 边界问题
        遍历方向:
            dp[i][j] 依赖于 dp[i-1][j-1], dp[i-1][j], dp[i][j-1]
        ==> 需要先知道 左边,上边,左上 的结果
        ==> 正向遍历

     */
    public static int longestCommonSubSequence1(String s1, String s2) {
        return dp(s1.toCharArray(), s2.toCharArray(),
                s1.length(), s2.length());
    }

    public static int dp(char[] str1, char[] str2, int i, int j) {
        // base case
        if (i == 0) {
            return 0;
        }
        if (j == 0) {
            return 0;
        }

        // 1
        if (str1[i - 1] == str2[j - 1]) {
            return dp(str1, str2, i - 1, j - 1) + 1;
        } else {
            // 2
            int res1 = dp(str1, str2, i - 1, j);
            int res2 = dp(str1, str2, i, j - 1);
            int res3 = dp(str1, str2, i - 1, j - 1);

            return Math.max(res1, Math.max(res2, res3));
        }
    }

    public static int longestCommonSubSequence2(String s1, String s2) {

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[][] dp = new int[str1.length + 1][str2.length + 1];
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
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    int res1 = dp[i - 1][j];
                    int res2 = dp[i][j - 1];
                    int res3 = dp[i - 1][j - 1];

                    dp[i][j] = Math.max(res1, Math.max(res2, res3));
                }
            }
        }

        return dp[str1.length][str2.length];
    }

    public static void main(String[] args) {

        String s1 = "abcde";
        String s2 = "ace";

        System.out.println(longestCommonSubSequence1(s1, s2)); // 3
        System.out.println(longestCommonSubSequence2(s1, s2)); // 3

    }
}
