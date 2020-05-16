package NowCoder.basic_class.exercise.exercise02;

public class code45 {
    public static boolean process(int[] arr, int a, int index, int res) {
        // base case
        if (index == arr.length) {
            return res == a;
        }

        return process(arr, a, index + 1, res) || process(arr, a, index + 1, res + arr[index]);
    }

    public static boolean MoneyProblem1(int[] arr, int a) {
        return process(arr, a, 0, 0);
    }

    public static boolean MoneyProblem2(int[] arr, int a){
        int row = arr.length + 1;
        int col = 0;
        for (int i = 0; i < arr.length; i++) {
            col += arr[i];
        }
        col += 1;

        boolean[][] dp = new boolean[row][col];

        // base case
        dp[row - 1][a] = true;

        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j + arr[i] <= a) {
                    dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 8};
        int a = 5;

        System.out.println(MoneyProblem1(arr, a));
        System.out.println(MoneyProblem2(arr, a));
    }
}
