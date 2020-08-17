package NowCoder.动态规划DP.exercise.普通DP;

/*
背包问题变体: 子集分割
    给定只包含正整数的数组, 问是否可以将数组划分成两个子集
    使得两个子集的元素和相等

 */
public class code04_01Package_CanPartition {

    /*
        数组分割, 使得两部分和相同
    ==> 前提: 数组总和sum为偶数时, 才能保证一分为二后, 两个子集都是 sum/2
    ==> 转换问题:
        在一个数组中, 是否存在和为sum/2的子集, 对于每个元素, 只能选一次
    ==> 01背包问题

        1. 状态:
            数组元素个数, 总和
        2. dp数组:
            二维dp数组 dp[i][j]
            含义: 在前i个元素中, 是否存在一个子集, 其和是j
        3. 选择, 写状态转移, 并择优
            由于每个元素只能选一次, 所以只可能在/不在这个子集中
            对于每个元素来说, 都只有选/不选两种可能
            (1) 选第i个元素:
                dp[i][j] = dp[i - 1][j - arr[i]]
            (2) 不选第i个元素:
                dp[i][j] = dp[i - 1][j]
            ==> 最终结果: dp[arr.length][sum]
            ==> 范围: 0<=i<=arr.length, 0<=j<=sum ==> new dp[arr.length+1][sum+1]
            ==> base case:
                (1) dp[0][...] = 没有元素选择 = false
                (2) dp[...][0] = 和为0的子集 = 不选择元素 = true
            ==> 注意: 从索引1开始, 表示的是0位置上的元素, 所以要改进方程
                (1) 选第i个元素:
                    dp[i][j] = dp[i - 1][j - arr[i-1]]
                (2) 不选第i个元素:
                    dp[i][j] = dp[i - 1][j]
                ==> 只要有一个为true即返回true, 两者||或
        4. 根据方程和base case, 确定遍历方向, 边界问题
        遍历方向:
            dp[i][j] 依赖于 dp[i-1][j], dp[i-1][j-arr[i-1]
        ==> 需要先知道 上方,左上方 的值
        ==> 正向遍历
        边界问题:
            arr[i-1] > sum时, 直接不选
            数组总和sum为偶数时, 才能保证一分为二后, 两个子集都是 sum/2
     */
    public static boolean canPartition1(int[] arr){

        int sum = 0;
        for (int value : arr) {
            sum += value;
        }

        if (sum % 2 != 0){
            return false;
        }

        sum /= 2;

        return dp(arr, arr.length, sum);
    }

    public static boolean dp(int[] arr, int index, int sum){
        // base case
        if (index == 0){
            return false;
        }
        if (sum == 0){
            return true;
        }

        // 1
        boolean res1 = dp(arr, index - 1, sum);
        // 2
        boolean res2 = false;
        if (sum >= arr[index - 1]){
            res2 = dp(arr, index - 1, sum - arr[index - 1]);
        }

        return res1 || res2;
    }

    public static boolean canPartition2(int[] arr){

        int sum = 0;
        for (int value : arr) {
            sum += value;
        }

        if (sum % 2 != 0){
            return false;
        }

        sum /= 2;

        boolean[][] dp = new boolean[arr.length + 1][sum + 1];
        // base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // 1
                dp[i][j] = dp[i - 1][j];
                // 2
                if (j >= arr[i - 1]){
                    dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        return dp[arr.length][sum];
    }

    // dp再优化: 一维滚动数组
    public static boolean canPartition3(int[] arr){
        int sum = 0;
        for (int value : arr) {
            sum += value;
        }

        if (sum % 2 != 0){
            return false;
        }

        sum /= 2;

        boolean[] dp = new boolean[sum + 1];
        // base case
        dp[0] = true;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                // 1
//                dp[j] = dp[j];
                // 2
                if (j >= arr[i]){
                    dp[j] = dp[j] || dp[j - arr[i]];
                }
            }
        }

        return dp[sum];
    }

    public static void main(String[] args) {

        int[] arr1 = {1, 5, 11, 5};
        int[] arr2 = {1, 2, 3, 5};

        System.out.println(canPartition1(arr1)); // true
        System.out.println(canPartition2(arr1)); // true
        System.out.println(canPartition3(arr1)); // true

        System.out.println(canPartition1(arr2)); // false
        System.out.println(canPartition2(arr2)); // false
        System.out.println(canPartition3(arr2)); // false

    }

}
