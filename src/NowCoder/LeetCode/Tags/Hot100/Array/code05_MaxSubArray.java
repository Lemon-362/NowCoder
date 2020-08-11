package NowCoder.LeetCode.Tags.Hot100.Array;

/*
53. 最大子序和
    给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 */
public class code05_MaxSubArray {

    /*
    方法一: 动态规划
        dp[i]: 以i位置结尾的最大子数组的和
        (1) dp[i - 1] >= 0: dp[i] = dp[i - 1] + arr[i];
        (2) dp[i - 1] < 0: dp[i] = arr[i];

     */
    public static int maxSubArray1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int max = 0;

        for (int i = 1; i < arr.length; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + arr[i];
            } else {
                dp[i] = arr[i];
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /*
    方法二: 分治方法

     */
    public static int maxSubArray2(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(maxSubArray1(arr)); // 6
    }

}
