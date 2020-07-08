package NowCoder.Hard.RecurAndDp;

/*
    给定一个数组，要求从左上角走到右下角，每一步只能向右或者向下。
    求最小路径和。

暴力递归：
    当前值 + 往下/往右走到终点的最小路径和
动态规划：
    就是将最小路径和的二维表一个一个的填完
 */
public class basic_code44_MinPath {
    public static int MinPath(int[][] arr, int i, int j) {
        // base case
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            return arr[i][j];
        }
        // 情况一：到达最右边
        if (i == arr.length -1){
            return arr[i][j] + MinPath(arr, i, j+1);
        }
        // 情况二：到达最下边
        if (j == arr[0].length - 1){
            return arr[i][j] + MinPath(arr, i+1, j);
        }
        // 情况三：普通情况
        int right = MinPath(arr, i, j+1);
        int down = MinPath(arr, i+1, j);
        return arr[i][j] + Math.min(right, down);
    }

    // 动态规划：就是将每个位置到右下角的最短路径和填入表中，一个一个的填进去，最终返回需要的最终位置的最短路径和
    public static int walk02(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[][] dp = new int[row][col];
        // 右下角
        dp[row - 1][col - 1] = arr[row - 1][col - 1];
        // 最后一行：从最后一行的倒数第二个位置开始
        for (int i = col - 2; i >= 0; i--) {
            dp[row - 1][i] = arr[row - 1][i] + dp[row - 1][i + 1];
        }
        // 最后一列：从最后一列的倒数第二个位置开始
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = arr[i][col - 1] + dp[i + 1][col - 1];
        }
        // 普遍位置
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                dp[i][j] = arr[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(MinPath(m, 0, 0)); // 12
        System.out.println(walk02(m));
//        int[][] dp = walk02(m);
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[0].length; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
    }
}
