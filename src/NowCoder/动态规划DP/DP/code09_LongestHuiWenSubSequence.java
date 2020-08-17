package NowCoder.动态规划DP.DP;

/*
最长回文子序列

 */
public class code09_LongestHuiWenSubSequence {

    /*
    TODO 子序列问题的解题模板
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

    /*
    最长回文子序列:
        TODO 由于求的是回文, 所以肯定要有两个指针来看两边的字符
            ==> 一定是定义成二维dp数组

        1. 状态:
            两个指针i和j, 指向两个字符
        2. dp数组:
            dp[i][j]: 在子串arr[i,...,j]中, 最长回文子序列的长度是dp[i][j]
            前提: dp[i+1][j-1]及其之内的都已知, 即arr[i+1,...,j-1]都已知
        ==> 最终结果: dp[0][arr.length-1]
        ==> base case:
            (1) i==j时, dp[i][j] = 1
            (2) i > j时, 一定为0
        3. 选择, 写状态转移, 并择优:
            对于i和j位置的字符, 有两种可能
            (1) arr[i] == arr[j]:
                dp[i][j] = dp[i+1][j-1] + 2(加上这两个字符)
            (2) arr[i] != arr[j]:
                不可能同时在arr[i,...,j]的最长回文子序列中, 最多只有一个在
                dp[i][j] = dp[i+1][j] / dp[i][j-1]
            ==> 对于第二种情况, 因为要选出最长回文子序列, 所以选择较大的那个
                TODO 为什么不要加+1?
                    因为只能判断出arr[i]!=arr[j],
                    而arr[i+1]和arr[j] / arr[i]和arr[j-1]不能判断出来,
                    需要根据之前的状态转移过来,
                    即dp[i+1][j] / dp[i][j-1]是已知的, 只需要判断哪个大, 就是当前dp[i][j]的值
        4. 转换成代码, 边界问题

        TODO 根据base case以及状态转移函数, 考虑如何遍历
            dp[i][j]是从dp[i+1][j-1], dp[i+1][j], dp[i][j-1]转移过来的
            已知对角线都为1, 下半部分都为0
            所以应该从下往上, 从左往右遍历, 并且j只需要从对角线上方遍历

     */
    public static int longestHuiWenSubSequence(String s) {

        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        // base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }

        // 从下往上, 从左往右遍历, 并且j只需要从对角线上方遍历
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < dp[0].length; j++) {
                if (str[i] == str[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {

        String s1 = "bbbab";
        String s2 = "cbbd";

        System.out.println(longestHuiWenSubSequence(s1)); // 4
        System.out.println(longestHuiWenSubSequence(s2)); // 2

    }
}
