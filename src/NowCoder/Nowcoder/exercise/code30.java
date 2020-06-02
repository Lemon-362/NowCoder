package NowCoder.Nowcoder.exercise;

public class code30 {
    public static int findGreatestSumOfSubArray(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }

        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < arr.length; i++) {
            if (dp[i - 1] >= 0){
                dp[i] = dp[i - 1] + arr[i];
            }else {
                dp[i] = arr[i];
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(findGreatestSumOfSubArray(arr)); // 6
    }
}
