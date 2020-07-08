package NowCoder.Hard.RecurAndDp.exercise;

public class basic_code44 {
    public static int process(int[][] arr, int i, int j) {
        // base case
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            return arr[i][j];
        }

        if (i == arr.length - 1) {
            return arr[i][j] + process(arr, i, j + 1);
        }

        if (j == arr[0].length - 1) {
            return arr[i][j] + process(arr, i + 1, j);
        }

        int right = process(arr, i, j + 1);
        int down = process(arr, i + 1, j);

        return Math.min(right, down) + arr[i][j];
    }

    public static int walk1(int[][] arr) {
        return process(arr, 0, 0);
    }

    public static int walk2(int[][] arr) {

        int[][] dp = new int[arr.length][arr[0].length];

        // base case
        dp[arr.length - 1][arr[0].length - 1] = arr[arr.length - 1][arr[0].length - 1];

        // 1
        for (int j = arr[0].length - 2; j >= 0; j--) {
            dp[arr.length - 1][j] = arr[arr.length - 1][j] + dp[arr.length - 1][j + 1];
        }

        // 2
        for (int i = arr.length - 2; i >= 0; i--) {
            dp[i][arr[0].length - 1] = arr[i][arr[0].length - 1] + dp[i + 1][arr[0].length - 1];
        }

        // 3
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = arr[0].length - 2; j >= 0; j--) {
                int right = dp[i][j + 1];
                int down = dp[i + 1][j];
                dp[i][j] = Math.min(right, down) + arr[i][j];
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
