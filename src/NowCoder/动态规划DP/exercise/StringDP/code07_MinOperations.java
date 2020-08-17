package NowCoder.动态规划DP.exercise.StringDP;

/*
编辑距离
    给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
    你可以对一个单词进行如下三种操作：
        插入一个字符
        删除一个字符
        替换一个字符

 */
public class code07_MinOperations {

    /*
    字符串问题 ==> 动态规划
        给了两个字符串 ==> 二维dp数组

        1. 状态:
            两个指针分别指向s1和s2
        2. dp数组:
            二维dp数组 dp[i][j]
            含义: 在s1[0,...,i]和s2[0,...j]中, 将s1转换成s2的最少操作数为dp[i][j]
        3. 选择, 状态转移, 择优:
            当前状态下, 要想把s1转换成s2, 就得看s1[i]和s2[j]是否相同
            (1) s1[i] == s2[j]:
                不需要操作, 跳过
                dp[i][j] = dp[i - 1][j - 1]
            (2) s1[i] != s2[j]:
                可以进行增删改三种操作 TODO 从末尾开始往前缩
                - 增: 在s1的i位置增加s2[j], 使得s1[0,...,i]匹配s2[0,...,j]
                      而原来i位置并没有匹配, j位置已经匹配过了, 所以i不动, j往前缩一个
                      dp[i][j] = dp[i][j - 1] + 1
                - 删: 删除s1的i位置, 使得s1[0,...,i-1]匹配s2[0,...,j]
                      原来i位置被删除, j还是没有被匹配, 所以j不动, i往前缩一个
                      dp[i][j] = dp[i - 1][j] + 1
                - 改: 将s1的i位置字符改成s2的j位置字符, 使得s1[0,...,i]匹配s2[0,...,j]
                      i和j位置匹配上了, 所以i和j都往前缩一个
                      dp[i][j] = dp[i - 1][j - 1] + 1
            ==> 选择三者中min最小的结果
            ==> 最终结果: dp[s1.length-1][s2.length-1]
            ==> 范围: 0<=i<=s1.length-1, 0<=j<=s2.length-1 ==> new dp[s1.length][s2.length]
            ==> base case:
                TODO 因为0位置是base case, 而对于s1和s2来说是第一个字符的位置
                     所以dp[i][j]的i和j表示的是 索引为i-1/j-1位置的字符
                     而剩余字符个数就是i/j
                (1) dp[0][...] = i==0,s1匹配完了,s2还剩下j个 = j
                (2) dp[...][0] = j==0,s2匹配完了,s1还剩下i个 = i
            ==> 改进:
                由于base case中的位置0表示的是s1/s2为空串
                所以对于s1/s2的子串, 0位置应该表示第一个字符, 所以在dp[i][j]中, 应该是i/j=1的位置
                (1) s1[i - 1] == s2[j - 1]
                (2) s1[i - 1] != s2[j - 1]
            ==> 最终结果: dp[s1.length][s2.length]
            ==> 范围: 0<=i<=s1.length, 0<=j<=s2.length ==> new dp[s1.length+1][s2.length+1]
        4. 遍历方向, 边界问题
        遍历方向:
            dp[i][j] 依赖于 dp[i-1][j-1], dp[i][j-1], dp[i-1][j]
        ==> 需要先知道 左下,左,上 的结果
        ==> 正向遍历

     */
    public static int minOperations1(String s1, String s2) {
        return dp(s1.toCharArray(), s2.toCharArray(),
                s1.length(), s2.length());
    }

    public static int dp(char[] str1, char[] str2, int i, int j) {
        // base case
        if (i == 0) {
            return j;
        } else if (j == 0) {
            return i;
        }

        // 1
        if (str1[i - 1] == str2[j - 1]) {
            return dp(str1, str2, i - 1, j - 1);
        } else { // 2
            int res1 = dp(str1, str2, i, j - 1) + 1;
            int res2 = dp(str1, str2, i - 1, j) + 1;
            int res3 = dp(str1, str2, i - 1, j - 1) + 1;
            return Math.min(res1, Math.min(res2, res3));
        }
    }

    public static int minOperations2(String s1, String s2) {

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[][] dp = new int[str1.length + 1][str2.length + 1];
        // base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 1
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else { // 2
                    int res1 = dp[i][j - 1] + 1;
                    int res2 = dp[i - 1][j] + 1;
                    int res3 = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(res1, Math.min(res2, res3));
                }
            }
        }

        return dp[str1.length][str2.length];
    }

    public static void main(String[] args) {

        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minOperations1(word1, word2)); // 3
        System.out.println(minOperations2(word1, word2)); // 3

        System.out.println("*************************");

        word1 = "intention";
        word2 = "execution";
        System.out.println(minOperations1(word1, word2)); // 5
        System.out.println(minOperations2(word1, word2)); // 5

        System.out.println("*************************");

        word1 = "dinitrophenylhydrazine";
        word2 = "acetylphenylhydrazine";
        System.out.println(minOperations1(word1, word2)); // 6
        System.out.println(minOperations2(word1, word2)); // 6

    }
}
