package NowCoder.basic_class.exercise.exercise02;

public class code45 {
    public static boolean process(int[] arr, int index, int a){
        // base cae
        if (index == arr.length){
            return a == 0;
        }

        return process(arr, index + 1, a) || process(arr, index + 1, a - arr[index]);
    }

    public static boolean MoneyProblem1(int[] arr, int a){
        return process(arr, 0, a);
    }

    public static boolean MoneyProblem2(int[] arr, int a){
        int row = arr.length + 1;
        int col = a + 1;
        boolean[][] dp = new boolean[row][col];

        dp[arr.length][0] = true;

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int sum = 0; sum < dp[0].length; sum++) {
                if (sum - arr[index] >= 0){
                    dp[index][sum] = dp[index + 1][sum] || dp[index + 1][sum - arr[index]];
                }else {
                    dp[index][sum] = dp[index + 1][sum];
                }
            }
        }

        return dp[0][a];
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 8};
        int a = 5;

        System.out.println(MoneyProblem1(arr, a));
        System.out.println(MoneyProblem2(arr, a));
    }
}
