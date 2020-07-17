package NowCoder.Nowcoder.Nowcoder;

/*
    连续子数组的最大和：
        输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。
        求所有子数组的和的最大值。
        要求时间复杂度为O(n)。

 */
public class code30_FindGreatestSumOfSubArray {
    public static int FindGreatestSumOfSubArray(int[] array) {
        // F(N) 表示最大子序列和
        // 如果F(N-1)>0，那么F(N) = arr[N] + F(N-1)
        // 如果F(N-1)<0，那么F(N) = arr[N]
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int max = dp[0];
        for (int i = 1; i < array.length; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + array[i];
            } else {
                dp[i] = array[i];
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(FindGreatestSumOfSubArray(arr)); // 6
    }
}
