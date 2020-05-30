package NowCoder.basic_class.exercise01.RecurAndDP;

public class MoneyProblem {
    public static boolean moneyProblem(int[] arr, int a) {
        if (arr == null || arr.length < 1 || a < 0) {
            return false;
        }

        return process(arr, a, 0, 0);
    }

    public static boolean process(int[] arr, int a, int res, int index) {
        // base case
        if (index == arr.length) {
            return res == a;
        }

        return process(arr, a, res, index + 1) || process(arr, a, res + arr[index], index + 1);
    }

    public static boolean moneyProblem2(int[] arr, int a) {
        if (arr == null || arr.length < 1 || a < 0) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        boolean[][] dp = new boolean[arr.length + 1][sum + 1];

        // base case
        dp[arr.length][a] = true;

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j + arr[i] <= a) {
                    dp[i][j] = dp[i + 1][j] || dp[i + 1][j + arr[i]];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 8};
        System.out.println(moneyProblem(arr, 12));
        System.out.println(moneyProblem2(arr, 12));
    }
}
