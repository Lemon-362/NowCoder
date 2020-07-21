package NowCoder.basic_class.Tags.RecurAndDP;

public class MinPath {
    public static int minPath(int[][] arr){
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
        int down = process(arr, i + 1, j);
        int right = process(arr, i, j + 1);
        return arr[i][j] + Math.min(down, right);
    }

    public static int minPath2(int[][] arr){
        if (arr == null || arr.length < 1 || arr[0].length < 1){
            return 0;
        }

        int row = arr.length;
        int col = arr[0].length;
        int[][] dp = new int[row][col];

        // base case
        dp[row - 1][col - 1] = arr[row - 1][col - 1];

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
        System.out.println(minPath(m)); // 12
        System.out.println(minPath2(m));
    }
}
