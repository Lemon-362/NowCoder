package NowCoder.basic_class.exercise01.RecurAndDP;

public class MinPath {
    public static int method(int[][] arr, int i, int j){
        if (i == arr.length - 1 && j == arr[0].length - 1){
            return arr[i][j];
        }
        if (i == arr.length - 1){
            return arr[i][j] + method(arr, i, j + 1);
        }
        if (j == arr[0].length - 1){
            return arr[i][j] + method(arr, i + 1, j);
        }
        int right = method(arr, i,j + 1);
        int down = method(arr, i + 1, j);
        return arr[i][j] + Math.min(right, down);
    }

    public static int method02(int[][] arr){
        int row = arr.length;
        int col = arr[0].length;
        int[][] dp = new int[row][col];

        dp[row - 1][col - 1] = arr[row - 1][col - 1];
        for (int i = col - 2; i >= 0; i--) {
            dp[row - 1][i] = arr[row - 1][i] + dp[row - 1][i + 1];
        }
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = arr[i][col - 1] + dp[i + 1][col - 1];
        }

        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                int right = dp[i][j + 1];
                int down = dp[i + 1][j];
                dp[i][j] = arr[i][j] + Math.min(right, down);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(method(m, 0, 0)); // 12
        System.out.println(method02(m));
    }
}
