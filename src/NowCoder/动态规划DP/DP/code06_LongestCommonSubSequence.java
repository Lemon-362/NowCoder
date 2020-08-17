package NowCoder.动态规划DP.DP;

/*
最长公共子序列LCS:
    TODO 注意:
        (1) 子序列: 不要求连续
        (2) 子序列问题: 通常都是用 [动态规划] 来解决

 */
public class code06_LongestCommonSubSequence {

    /*
    字符串问题的动态规划流程:
        1. 状态
            两个字符串的子串, 就可以确定一个最长公共子序列
        2. 根据 [状态] 确定dp数组的含义
            dp[i][j]: s1[1...i] 和 s2[1...j] 两个字符串, 他们的LCS长度为dp[i][j]
                即对于 以i位置结尾的s1 和 以j位置结尾的s2, 他们的LCS=dp[i][j]
            TODO 实际上s1[1...i]表示的是从第1个字符(索引为0)到第i个字符(索引为i-1)
            ==> 最终结果: dp[s1.length][s2.length] ==> TODO 表示最后一个字符, 而不是字符串末尾的后一个位置
            ==> base case:
                (1) dp[0][...]=0: 表示s1为空串
                (2) dp[...][0]=0: 表示s2为空串
            ==> TODO 根据base case的定义, 所以普遍情况的dp[i][j]是从 [索引1] 开始的
            TODO 注意:
                由于base case是dp[0][...],dp[...][0],
                所以索引从1开始的, 而对于索引为1时, 应该表示第1个字符, 即0位置处
                所以再判断时应该是 s1[i - 1] == s2[j - 1]
        3. 选择, 写状态转移, 择优
            对于s1和s2的每个字符, 都有两种选择: 在 / 不在 LCS中
            (1) 在: 如果s1或s2的某个字符在LCS中, 那么他也一定在s2/s1字符串中
                    即该字符一定同时存在于s1和s2中
            (2) 不在: s1[i]和s2[j]至少有一个不在LCS中, 需要丢弃一个, 去判断s1/s2的后一个字符
            ==> 遍历两个字符串, 对于s1和s2的每个字符, 都有两种情况:
                (1) s1[i - 1] == s2[j - 1]:
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                (2) s1[i - 1] != s2[j - 1]:
                    dp[i][j] = dp[i - 1][j] / dp[i][j - 1] / dp[i - 1][j - 1]
            ==> 对于第二种情况, 因为要选出最长子序列, 所以用max三者中选择较大的一个
            ==> 由于dp[i-1][j-1]这种情况, 表示的是丢弃s1[i]和s2[j], 所以在剩下的字符串中找LCS时
                结果一定是 < dp[i-1][j]/dp[i][j-1]的,
                所以实际上, 可以省略dp[i-1][j-1]的情况

        4. 转换成代码, 边界问题

     */
    public static int longestCommonSubSequence2(String s1, String s2){

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[][] dp = new int[str1.length + 1][str2.length + 1];
        // base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 0;
        }

        // i=1开始, 表示的是从索引为0的位置开始, 所以表示第一个字符应该是 str[i - 1]
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }else {
//                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[str1.length][str2.length];
    }

    /*
    递归法:
        两个指针i和j, 从后往前遍历s1和s2
        TODO 遇到字符串的动态规划问题, 可以先考虑递归解法
             双指针, 分别指向两个字符串的 [最后], 然后一步步往前走, 缩小问题的规模
         而在考虑递归的base case时, 并不需要偏移一位, 直接判断是否为 -1 即可

     */
    public static int longestCommonSubSequence1(String s1, String s2){
        return dp(s1.toCharArray(), s2.toCharArray(),
                s1.length() - 1, s2.length() - 1);
    }

    public static int dp(char[] str1, char[] str2, int i, int j){
        // base case
        if (i == -1 || j == -1){
            return 0;
        }

        if (str1[i] == str2[j]){
            return dp(str1, str2, i - 1, j - 1) + 1;
        }else {
            return Math.max(dp(str1, str2, i - 1, j), dp(str1, str2, i, j - 1));
        }
    }

    public static void main(String[] args) {

        String s1 = "abcde";
        String s2 = "ace";

        System.out.println(longestCommonSubSequence1(s1, s2)); // 3
        System.out.println(longestCommonSubSequence2(s1, s2)); // 3

    }
}
