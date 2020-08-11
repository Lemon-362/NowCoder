package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

/*
62. 不同路径
    一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
    问总共有多少条不同的路径

 */
public class code16 {

    /*
    方法一: 暴力递归
        对于当前位置, 可以往下/往右走, 两种情况加起来
        需要考虑特殊位置

     */
    public static int uniquePaths1(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }

        return process(m, n, 0, 0);
    }

    public static int process(int m, int n, int i, int j) {
        // base case
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        // 1
        if (i == m - 1) {
            return process(m, n, i, j + 1);
        }

        // 2
        if (j == n - 1) {
            return process(m, n, i + 1, j);
        }

        return process(m, n, i, j + 1) + process(m, n, i + 1, j);
    }

    /*
    方法二: 动态规划 <== 暴力递归

     */
    public static int uniquePaths2(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }

        int[][] dp = new int[m][n];

        // base case
//        if (i == m - 1 && j == n - 1){
//            return 1;
//        }
        dp[m - 1][n - 1] = 1;

//        // 1
//        if (i == m - 1){
//            return process(m, n, i, j + 1);
//        }
//        // 2
//        if (j == n - 1){
//            return process(m, n, i + 1, j);
//        }
//        return process(m, n, i, j + 1) + process(m, n, i + 1, j);
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = dp[m - 1][j + 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = dp[i + 1][n - 1];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
            }
        }

//        return process(m, n, 0, 0);
        return dp[0][0];
    }

    public static void main(String[] args) {

        System.out.println(uniquePaths1(3, 2)); // 3
        System.out.println(uniquePaths2(3, 2)); // 3
        System.out.println(uniquePaths1(7, 3)); // 28
        System.out.println(uniquePaths2(7, 3)); // 28

    }
}
