package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

/*
64. 最小路径和
    给定一个包含非负整数的 m x n 网格，
    请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步。

    basic_code44_MinPath
 */
public class code13 {

    /*
    方法一: 暴力递归(深度优先)

        递归函数process:
            当前层有两种选择: 向下, 向右
            先假设向上返回的是向下和向右的结果
            然后选出较小的一个, 加上当前位置的结果, 一起向上返回

     */
    public static int minPath1(int[][] arr) {
        if (arr == null || arr.length < 1 || arr[0].length < 1){
            return 0;
        }

        return process(arr, 0, 0);
    }

    public static int process(int[][] arr, int i, int j){
        // base case
        if (i == arr.length - 1 && j == arr[0].length - 1){
            return arr[i][j];
        }

        // 1
        if (i == arr.length - 1){
            return arr[i][j] + process(arr, i, j + 1);
        }

        // 2
        if (j == arr[0].length - 1){
            return arr[i][j] + process(arr, i + 1, j);
        }

        // 3
        int right = process(arr, i, j + 1);
        int down = process(arr, i + 1, j);

        return arr[i][j] + Math.min(right, down);
    }

    /*
    方法二: 动态规划 <== 暴力递归

     */
    public static int minPath2(int[][] arr) {

        int row = arr.length;
        int col = arr[0].length;
        int[][] dp = new int[row][col];

        // base case
//        if (i == arr.length - 1 && j == arr[0].length - 1){
//            return arr[i][j];
//        }
        dp[row - 1][col - 1] = arr[row - 1][col - 1];

//        // 1
//        if (i == arr.length - 1){
//            return arr[i][j] + process(arr, i, j + 1);
//        }
//        // 2
//        if (j == arr[0].length - 1){
//            return arr[i][j] + process(arr, i + 1, j);
//        }
//        // 3
//        int right = process(arr, i, j + 1);
//        int down = process(arr, i + 1, j);
//        return arr[i][j] + Math.min(right, down);
        // 1
        for (int i = col - 2; i >= 0; i--) {
            dp[row - 1][i] = arr[row - 1][i] + dp[row - 1][i + 1];
        }
        // 2
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = arr[i][col - 1] + dp[i + 1][col - 1];
        }
        // 3
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                int down = dp[i + 1][j];
                int right = dp[i][j + 1];
                dp[i][j] = arr[i][j] + Math.min(down, right);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        int[][] arr = {{1,3,1},{1,5,1},{4,2,1}};

        System.out.println(minPath1(arr)); // 12
        System.out.println(minPath2(arr));
    }
}
