package NowCoder.basic_class.exercise01.RecurAndDP;

public class MoneyProblem {
    public static boolean method(int[] arr, int level, int res, int a){
        if (level == arr.length){
            return res == a;
        }

        return method(arr, level + 1, res, a) || method(arr, level + 1, res + arr[level], a);
    }

    public static boolean method02(int[] arr, int a){
        int row = arr.length + 1;
        int col = 0;
        for (int i = 0; i < arr.length; i++) {
            col += arr[i];
        }
        col = col + 1;
        boolean[][] dp = new boolean[row][col];

        dp[row - 1][a] = true;

        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < col - 1; j++) {
                if (j + arr[i] <= a){
                    dp[i][j] = dp[i + 1][j] || dp[i + 1][j + arr[i]];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr ={1, 4, 8};
        System.out.println(method(arr, 0, 0, 12));
        System.out.println(method02(arr, 12));
    }
}
