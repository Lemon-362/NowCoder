package NowCoder.动态规划DP.exercise.StringDP;

/*
最长回文子序列

 */
public class code09_LongestHuiWenSubSequence {

    /*
    子序列问题 ==> 动态规划
        给了一个字符串
    ==> 由于要求回文, 所以必须使用两个指针指向两个字符
    ==> 二维dp数组

        1. 状态:
            两个指针指向两个字符 ==> 向外扩的过程
        2. dp数组:
            二维dp数组 dp[i][j]
            含义: 在s[i,...,j]子串中, 最长回文子序列的长度为dp[i][j]
        3. 选择, 状态转移, 择优:
            当前状态下, 只需要判断s[i]和s[j]两端的字符是否相同
            (1) s[i] == s[j]:
                dp[i][j] = dp[i+1][j-1] + 2
            (2) s[i] != s[j]:
                如果两者不同, 那么最多只能有一个在回文中
                所以可能是i位置加进来 / j位置加进来, 两者中选择max较大的
                dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j])
            ==> 最终结果: dp[0][s.length-1]
            ==> 范围: 0<=i,j<=s.length-1 ==> new dp[s.length][s.length]
            ==> base case:
                (1) 对于每个字符来说, i==j时, 自己可以构成一个回文
                    dp[i][i] = 1
                (2) i > j时, dp[i][j]=0, 因为i不能超过j
            ==> 不需要改进
        4. 遍历方向, 边界问题
        遍历方向:
            dp[i][j] 依赖于 dp[i+1][j-1], dp[i][j-1], dp[i+1][j]
        ==> 需要先知道 左,左下,下 的结果
        ==> base case已知对角线及其下面的结果
        ==> i反过来遍历, j正向遍历

     */
    public static int longestHuiWenSubSequence1(String s) {
        return dp(s.toCharArray(), 0, s.length() - 1);
    }

    public static int dp(char[] str, int i, int j) {
        // base case
        if (i == j) {
            return 1;
        }
        if (i > j) {
            return 0;
        }

        // 1
        if (str[i] == str[j]) {
            return dp(str, i + 1, j - 1) + 2;
        } else { // 2
            int res1 = dp(str, i, j - 1);
            int res2 = dp(str, i + 1, j);
            int res3 = dp(str, i + 1, j - 1);
            return Math.max(res1, Math.max(res2, res3));
        }
    }

    public static int longestHuiWenSubSequence2(String s) {

        char[] str = s.toCharArray();
        int[][] dp = new int[str.length][str.length];
        // base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i > j) {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < dp[0].length; j++) {
                // 1
                if (str[i] == str[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else { // 2
                    int res1 = dp[i][j - 1];
                    int res2 = dp[i + 1][j];
                    int res3 = dp[i + 1][j - 1];
                    dp[i][j] = Math.max(res1, Math.max(res2, res3));
                }
            }
        }

        return dp[0][str.length - 1];
    }

    public static void main(String[] args) {

        String s1 = "bbbab";
        String s2 = "cbbd";

        System.out.println(longestHuiWenSubSequence1(s1)); // 4
        System.out.println(longestHuiWenSubSequence2(s1)); // 4

        System.out.println(longestHuiWenSubSequence1(s2)); // 2
        System.out.println(longestHuiWenSubSequence2(s2)); // 2

    }
}
