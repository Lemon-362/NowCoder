package NowCoder.basic_class.exercise.exercise02;

public class code44 {
    public static int process(int[][] arr, int i, int j){
        // base case
        if (i == arr.length - 1 && j == arr[0].length - 1){
            return arr[i][j];
        }

        if (i == arr.length - 1){
            return arr[i][j] + process(arr, i, j + 1);
        }

        if (j == arr[0].length - 1){
            return arr[i][j] + process(arr, i + 1, j);
        }

        int right = process(arr, i, j + 1);
        int down = process(arr, i + 1, j);

        return arr[i][j] + Math.min(right, down);
    }

    public static int walk1(int[][] arr){
        return process(arr, 0, 0);
    }

    public static int walk2(int[][] arr){
        if (arr == null || arr.length < 1){
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
                dp[i][j] = arr[i][j] + Math.min(dp[i][j + 1], dp[i + 1][j]);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] m = {
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };

        System.out.println(walk1(m)); // 12
        System.out.println(walk2(m)); // 12
    }
}
