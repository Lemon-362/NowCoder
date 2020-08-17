package NowCoder.动态规划DP.DP;

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
    动态规划:
        TODO 遇到字符串的动态规划问题, 可以先考虑递归解法
             双指针, 分别指向两个字符串的 [最后], 然后一步步往前走, 缩小问题的规模
        TODO 在考虑动态规划 4步流程 时:
            是假设 [i-1] 之前的子问题都计算好了, 现在用之前计算好的结果, 来计算 [i] 的子问题
            所以实际上思考的过程类似于 自顶向下(递归)
            而实际写动态规划时, 是 自底向上
            ==>
            (1) 思考流程时, 自顶向下, 假设之前的结果都计算好了, 用他们来计算当前问题
            (2) 写递归时, 自顶向下, 直接按照流程往下(往前)写, 直到可以直接给出结果(即base case)时,
                递归函数自己一步步返回
            (3) 写动态规划时, 自底向上, 从base case一步步往上(往后)推导

        1. 状态
            两个字符串的子串, 在s1上操作, 让s1变成s2
        2. 根据 [状态] 确定dp数组的含义
            dp[i][j]: s1[1...i] 和 s2[1...j] 两个字符串, 他们的最小编辑距离为dp[i][j]
            TODO 实际上s1[1...i]表示的是从第1个字符(索引为0)到第i个字符(索引为i-1)
            ==> 最终结果: dp[s1.length][s2.length] ==> TODO 表示最后一个字符, 而不是字符串末尾的后一个位置
            ==> base case:
                (1) dp[0][...]:
                    表示s1从后往前走完了, 而s2还没有, 为了让s1能够变成s2, 必须添加s2剩下的部分
                    ==> dp[0][...] = j, j表示的是索引为j-1, 第j个字符
                (2) dp[...][0]:
                    表示s2从后往前走完了, 而s1还没有, 为了让s1能够变成s2, 必须删除s1剩下的部分
                    ==> dp[...][0] = i, i表示的是索引为i-1, 第i个字符
            ==> TODO 根据base case的定义, 所以普遍情况的dp[i][j]是从 [索引1] 开始的
            TODO 注意:
                由于base case是dp[0][...],dp[...][0],
                所以索引从1开始的, 而对于索引为1时, 应该表示第1个字符, 即0位置处
                所以再判断时应该是 s1[i - 1] == s2[j - 1]
        3. 选择, 写状态转移, 择优
            对于s1和s2的每个字符, 都有两种情况:
            (1) s1[i - 1] == s2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1]
            (2) s1[i - 1] != s2[j - 1]
                此时, 对s1进行操作, 可以增删改
                - 增: 在s1[i]处插入s2[j]的字符, 使得s2[j]被匹配, 那么j往前移, 继续和i匹配
                    ==> dp[i][j] = 1 + dp[i][j - 1]
                - 删: 删除s1[i]处的字符, i往前移, 继续和j匹配
                    ==> dp[i][j] = 1 + dp[i - 1][j]
                - 改: 修改s1[i]处的字符为s2[j], 使得s2[j]被匹配, i和j都往前移
                    ==> dp[i][j] = 1 + dp[i - 1][j - 1]
            ==> 对于第二种情况, 因为要选出最小编辑距离, 所以用min三者中选择较小的一个

        4. 转换成代码, 边界问题

     */
    public static int minOperations1(String s1, String s2) {

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

        // i=1开始, 表示的是从索引为0的位置开始, 所以表示第一个字符应该是 str[i - 1]
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 改3
                    int res1 = 1 + dp[i - 1][j - 1];
                    // 加1
                    int res2 = 1 + dp[i][j - 1];
                    // 删2
                    int res3 = 1 + dp[i - 1][j];

                    dp[i][j] = Math.min(res1, Math.min(res2, res3));
//                    dp[i][j] = Math.min(1 + dp[i - 1][j - 1],
//                            Math.min(1 + dp[i - 1][j], 1 + dp[i][j - 1]));
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
    public static int minOperations2(String s1, String s2) {
        return dp(s1.toCharArray(), s2.toCharArray(),
                s1.length() - 1, s2.length() - 1);
    }

    public static int dp(char[] str1, char[] str2, int i, int j) {
        // base case
        if (i == -1) {
            // s1走完了, s1要加上从j开始往前所有的字符, 而j表示的是索引, 所以应该是 j+1
            return j + 1;
        }
        if (j == -1) {
            // s2走完了, s1要删去从i开始往前所有的字符, 而i表示的是索引, 所以应该是 i+1
            return i + 1;
        }

        if (str1[i] == str2[j]) {
            return dp(str1, str2, i - 1, j - 1);
        } else {
            int res1 = 1 + dp(str1, str2, i - 1, j - 1);
            int res2 = 1 + dp(str1, str2, i, j - 1);
            int res3 = 1 + dp(str1, str2, i - 1, j);
            return Math.min(res1, Math.min(res2, res3));
        }
    }

    /*
    TODO 扩展: 如何求出具体的编辑过程
     */
    public static class Node {
        // 所需最小编辑距离
        private int value;
        // 具体编辑操作
        // 0: 跳过, 1: 添加, 2: 删除, 3: 替换
        private int choice;

        public Node(int value, int choice) {
            this.value = value;
            this.choice = choice;
        }
    }

    public static void minOperations3(String s1, String s2) {

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        Node[][] dp = new Node[str1.length + 1][str2.length + 1];
        // base case
        for (int i = 0; i < dp.length; i++) { // j=0, s2走完, s1剩下的删除
            dp[i][0] = new Node(i, 2);
        }
        for (int j = 0; j < dp[0].length; j++) { // i=0, s1走完, s1添加s2剩下的
            dp[0][j] = new Node(j, 1);
        }

        // i=1开始, 表示的是从索引为0的位置开始, 所以表示第一个字符应该是 str[i - 1]
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = new Node(dp[i - 1][j - 1].value, 0);
                } else {
                    // 改3
                    int res1 = 1 + dp[i - 1][j - 1].value;
                    // 加1
                    int res2 = 1 + dp[i][j - 1].value;
                    // 删2
                    int res3 = 1 + dp[i - 1][j].value;

                    if (res1 <= res2 & res1 <= res3) {
                        dp[i][j] = new Node(1 + dp[i - 1][j - 1].value, 3);
                    } else if (res2 <= res1 & res2 <= res3) {
                        dp[i][j] = new Node(1 + dp[i][j - 1].value, 1);
                    } else if (res3 <= res1 & res3 <= res2) {
                        dp[i][j] = new Node(1 + dp[i - 1][j].value, 2);
                    }
                }
            }
        }

//        Node res = dp[str1.length][str2.length];

        printProcess(dp, s1, s2, str1.length, str2.length);
    }

    public static void printProcess(Node[][] dp,
                                    String s1, String s2,
                                    int i, int j){
        // base case
        if (i == 0) {
            // s1走完了, s1要加上从j开始往前所有的字符, 而j表示的是索引, 所以应该是 j+1
            for (int k = 1; k <= j; k++) {
                System.out.println("添加: " + s2.charAt(k - 1));
            }
        }
        if (j == 0) {
            // s2走完了, s1要删去从i开始往前所有的字符, 而i表示的是索引, 所以应该是 i+1
            for (int k = 1; k <= i; k++) {
                System.out.println("删除: " + s1.charAt(k - 1));
            }
        }

        if (i >= 1 && j >= 1) {
            Node node = dp[i][j];

            if (node.choice == 0) { // 跳过
                System.out.println("跳过: " + s1.charAt(i - 1));
                printProcess(dp, s1, s2, i - 1, j - 1);
            } else if (node.choice == 1) { // 添加
                System.out.println("添加: " + s2.charAt(j - 1));
                printProcess(dp, s1,s2,i, j - 1);
            } else if (node.choice == 2) { // 删除
                System.out.println("删除: " + s1.charAt(i - 1));
                printProcess(dp,s1,s2,  i - 1, j);
            } else if (node.choice == 3) { // 替换
                System.out.println("替换: " + s2.charAt(j - 1));
                printProcess(dp, s1,s2,i - 1, j - 1);
            }
        }

    }

    public static void main(String[] args) {

        String word1 = "horse";
        String word2 = "ros";
        minOperations3(word1, word2);
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
