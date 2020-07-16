package NowCoder.LeetCode.Tags.Hot100.String;

/**
 * 72. 编辑距离
 *  给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *  你可以对一个单词进行如下三种操作：
 *      插入一个字符
 *      删除一个字符
 *      替换一个字符
 *
 *  TODO 暴力递归 ==> 动态规划
 *
 */
public class code09_MinOperations {

    /**
     * 方法一: 暴力递归
     *
     *  1. 递归函数f(i, j): 表示从s1的i位置开始和s2的j位置匹配, 默认之前的都修改成相同的了
     *
     *  2. 分析可能性:
     *  (1) s1[i] == s2[j]: 说明s1的i位置和s2的j位置的字符相同, 不需要处理, 因为默认之前处理完了
     *      ==> 递归f(i+1, j+1), 去匹配s1的i+1位置和s2的j+1位置
     *  (2) s1[i] != s2[j]: 说明s1的i位置和s2的j位置的字符相同, 需要进行处理
     *      A. 删:
     *          删除s1的i位置元素, 使得s1的0 - i和s2的0 - j-1匹配,
     *          操作数+1
     *          ==> 递归f(i+1, j), 去匹配s1的i+1位置和s2的j位置
     *      B. 改:
     *          将s1的i位置元素改为s2的j位置元素, 使得s1的0 - i和s2的0 - j匹配
     *          操作数+1
     *          ==> 递归f(i+1, j+1), 去匹配s1的i+1位置和s2的j+1位置
     *      C. 增:
     *          TODO 因为删除元素很好理解, 但是如果对s1增加的话, 会导致原来的i位置往后移动
     *               而 s1增加 <==> s2删除
     *               所以可以将 在s1的i位置增加 理解为 在s2的j位置删除
     *          删除s2的j位置元素, 使得s1的0 - i-1和s2的 0 - j匹配
     *          操作数+1
     *          ==> 递归f(i, j+1), 去匹配s1的i位置和s2的j+1位置
     *
     *  3. base case:
     *  (1) i == s1.length:
     *      说明s1先匹配完成, s2还剩字符
     *      那么只能将s2还剩的字符增加到s1末尾
     *      操作数 = s2还剩的字符 = s2.length - j
     *  (2) j == s2.length:
     *      说明s2先匹配完成, s1还剩字符
     *      那么只能将s1还剩的字符删除
     *      操作数 = s1还剩的字符 = s1.length - i
     *
     */
    public static int minOperations1(String word1, String word2){
        if (word1 == null || word2 == null){
            return 0;
        }

        return process(word1.toCharArray(), word2.toCharArray(), 0, 0);
    }

    public static int process(char[] s1, char[] s2, int i, int j){
        // base case
        if (i == s1.length) { // s1先匹配完
            return s2.length - j;
        }
        if (j == s2.length){ // s2先匹配完
            return s1.length - i;
        }


        // 1 相同, 直接跳过
        if (s1[i] == s2[j]){
            return process(s1, s2, i + 1, j + 1);
        }else {
            // 2.1 增加
            int res1 = 1 + process(s1, s2, i, j + 1);
            // 2.2 删除
            int res2 = 1 + process(s1, s2, i + 1, j);
            // 2.3 改变
            int res3 = 1 + process(s1, s2, i + 1, j + 1);

            // 从三个操作中选择最小操作数的
            return Math.min(res1, Math.min(res2, res3));
        }

    }

    /**
     * 方法二: 暴力递归改动态规划
     *
     *
     */
    public static int minOperations2(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int[][] dp = new int[s1Len + 1][s2Len + 1];

        // base case
//        if (i == s1.length) { // s1先匹配完
//            return s2.length - j;
//        }
//        if (j == s2.length){ // s2先匹配完
//            return s1.length - i;
//        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[s1Len][j] = s2Len - j;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][s2Len] = s1Len - i;
        }

//        // 1 相同, 直接跳过
//        if (s1[i] == s2[j]){
//            res = process(s1, s2, i + 1, j + 1);
//        }else {
//            // 2.1 增加
//            int res1 = 1 + process(s1, s2, i, j + 1);
//            // 2.2 删除
//            int res2 = 1 + process(s1, s2, i + 1, j);
//            // 2.3 改变
//            int res3 = 1 + process(s1, s2, i + 1, j + 1);
//
//            // 从三个操作中选择最小操作数的
//            res = Math.min(res1, Math.min(res2, res3));
//        }
        for (int i = s1Len - 1; i >= 0; i--) {
            for (int j = s2Len - 1; j >= 0; j--) {
                // 1
                if (s1[i] == s2[j]){
                    dp[i][j] = dp[i + 1][j + 1];
                }else {
                    // 2.1
                    int res1 = 1 + dp[i][j + 1];
                    // 2.2
                    int res2 = 1 + dp[i + 1][j];
                    // 2.3
                    int res3 = 1 + dp[i + 1][j + 1];

                    dp[i][j] = Math.min(res1, Math.min(res2, res3));
                }
            }
        }

        return dp[0][0];
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
        System.out.println(minOperations2(word1, word2)); // 6
    }
}
