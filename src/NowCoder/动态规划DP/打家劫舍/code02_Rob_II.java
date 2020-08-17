package NowCoder.动态规划DP.打家劫舍;

import java.util.Arrays;

/*
打家劫舍II:
    房子不是一排, 而是环形
    首尾相连

 */
public class code02_Rob_II {

    /*
        首尾相连后, 对于最后一个房子的讨论需要进行改进,
        因为第一个如果抢了, 最后一个就不能抢
        (1) 第一个抢, 最后一个一定不能抢:
            ==> 转换成在 [0, len-2] 长度内获得的最大金额
        (2) 第一个不抢, 最后一个抢:
            ==> 转换成在 [1, len-1] 长度内获得的最大金额
        (3) 第一个不抢, 最后一个也不抢:
            两端都不抢的话, 那么肯定是 < (1)/(2)的
        TODO 即转换成两个 Rob_I 的问题
     */
    public static int rob(int[] arr) {
        if (arr.length == 0){
            return 0;
        }

        if (arr.length == 1) {
            return arr[0];
        }

        // (1): [0, len - 2]
        int res1 = rob2(Arrays.copyOfRange(arr, 0, arr.length - 1));
        // (2): [1, len - 1]
        int res2 = rob2(Arrays.copyOfRange(arr, 1, arr.length));

        return Math.max(res1, res2);
    }

    public static int rob2(int[] arr) {

        int[] dp = new int[arr.length + 2];
        // base case
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + arr[i - 2], dp[i - 1]);
        }

        return dp[dp.length - 1];
    }

    public static void main(String[] args) {

        int[] arr1 = {2, 3, 2};
        int[] arr2 = {1, 2, 3, 1};

        System.out.println(rob(arr1)); // 3
        System.out.println(rob(arr2)); // 4

    }

}
