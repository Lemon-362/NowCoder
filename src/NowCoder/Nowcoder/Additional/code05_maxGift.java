package NowCoder.Nowcoder.Additional;

/**
 * 在 m*n 的棋盘上，每一个都放有礼物，都有自己的价值
 * 从左上角开始，每次向右或者向下移动一格，直到右下角
 * 求：最多能拿到多少价值的礼物？
 * TODO 和basic_code_44 最小路径和 的思路一样
 */
public class code05_maxGift {
    /**
     * 递归法
     *  f(i,j) = arr[i][j] + max(f(i+1,j), f(i,j+1))
     *  当前值 + 往下/往右走到终点的最大价值和
     */
    public static int process(int[][] arr, int i, int j) {
        // base case
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            return arr[i][j];
        }

        // 1 最后一行
        if (i == arr.length - 1){
            return arr[i][j] + process(arr, i, j + 1);
        }

        // 2 最后一列
        if (j == arr[0].length - 1){
            return arr[i][j] + process(arr, i + 1, j);
        }

        // 3 中间位置
        int leftValue = process(arr, i, j + 1);
        int downValue = process(arr, i + 1, j);

        return arr[i][j] + Math.max(leftValue, downValue);
    }

    public static int maxGift(int[][] arr) {
        return process(arr, 0, 0);
    }

    public static int maxGift1(int[][] arr){
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
                dp[i][j] = arr[i][j] + Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(maxGift(arr));
        System.out.println(maxGift1(arr));
    }

}
