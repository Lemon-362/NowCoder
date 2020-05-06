package NowCoder.basic_class.basic_class.class_08;

/*
    给定一个数组，要求从左上角走到右下角，每一步只能向右或者向下。
    求最小路径和。

暴力递归：
    当前值 + 往下/往右走到终点的最小路径和
动态规划：
    递归时重复状态太多，可以将状态存在缓冲HashMap中

暴力递归 --> 动态规划 ：
    条件：
        有重复状态，且与到达路径无关
     -->无后效性：可变参数固定时，返回值也固定
        有后效性：汉诺塔（打印中间步骤，可变参数改变时，返回值也改变）
                  八皇后问题（打印放置位置）
    步骤：
        1. 写暴力递归
        2. 分析可变参数，哪几个参数可代表返回值状态
        3. 画dp表，将最终状态点出来
        4. 根据终止条件（base case），设置不依赖其他位置的值
        5. 普通位置，需要哪些位置，递推填dp表
 */
public class Code_07_MinPath {

    public static int minPath1(int[][] matrix) {
        return process1(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    public static int process1(int[][] matrix, int i, int j) {
        int res = matrix[i][j];
        if (i == 0 && j == 0) {
            return res;
        }
        if (i == 0 && j != 0) {
            return res + process1(matrix, i, j - 1);
        }
        if (i != 0 && j == 0) {
            return res + process1(matrix, i - 1, j);
        }
        return res + Math.min(process1(matrix, i, j - 1), process1(matrix, i - 1, j));
    }

    public static int minPath2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0]; // 最终状态
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    // 暴力递归
    public static int walk(int[][] matrix, int i, int j) {
        // 到达右下角
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            return matrix[i][j];
        }
        // 到达最后一行，只能一直向右，直到右下角
        if (i == matrix.length - 1) {
            return matrix[i][j] + walk(matrix, i, j + 1);
        }
        // 到达最后一列，只能一直向下，直到右下角
        if (j == matrix[0].length - 1) {
            return matrix[i][j] + walk(matrix, i + 1, j);
        }
        // 往右走，右边位置到右下角的最短路径和
        int right = walk(matrix, i, j + 1);
        // 往下走，下边位置到右下角的最短路径和
        int down = walk(matrix, i + 1, j);

        return matrix[i][j] + Math.min(right, down);
    }

    public static int walk02(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[][] dp = new int[row][col];
        // 右下角
        dp[row - 1][col - 1] = arr[row - 1][col - 1];
        // 最后一行
        for (int i = col - 2; i >= 0; i--) {
            dp[row - 1][i] = arr[row - 1][i] + dp[row - 1][i + 1];
        }
        // 最后一列
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = arr[i][col - 1] + dp[i + 1][col - 1];
        }
        // 普遍位置
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < col - 1; j++) {
                dp[i][j] = arr[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(walk(m, 0, 0));

        System.out.println(minPath1(m));
        System.out.println(minPath2(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
    }
}
