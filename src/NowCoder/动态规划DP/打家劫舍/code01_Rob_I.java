package NowCoder.动态规划DP.打家劫舍;

/*
打家劫舍I:
    你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
    影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
    如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    给定一个代表每个房屋存放金额的非负整数数组，
    计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额

 */
public class code01_Rob_I {

    /*
    求最高金额 ==> 动态规划
        1. 状态:
            每个房子
        2. dp数组:
            一维dp数组 dp[i]
            含义: 在前i个房子中, 能够获得的最高金额是dp[i]
        3. 选择, 状态转移, 择优:
            对于每个房子, 都可以选择抢/不抢
            (1) 抢:
                dp[i] = dp[i - 2] + arr[i]
            (2) 不抢:
                dp[i] = dp[i - 1]
                两者之中去max较大的
            ==> 最终结果: dp[arr.length - 1]
            ==> 范围: 0<=i<=arr.length - 1
            ==> base case:
                (1) dp[0] = 0: 还没开始抢
            ==> 改进:
                (1) 抢:
                    dp[i] = dp[i - 2] + arr[i - 1]
                (2) 不抢:
                    dp[i] = dp[i - 1]
                ==> 最终结果:
                    dp[arr.length]
                ==> 范围:
                    0<=i<=arr.length
                ==> base case:
                    (1) dp[0] = 0
                    (2) dp[1] = max{dp[-1]+arr[0], dp[0]} = arr[0]

        TODO 本题以递归自顶向下的思路考虑流程, 可能会简单一些
            从第一个房子开始, 可能抢/不抢
            (1) 抢: dp[i] --> arr[i] + dp[i+2]
            (2) 不抢: dp[i] --> dp[i+1]
            两者中选max较大的
            ==> 最终结果: dp[0] 从第一个房子开始, 能够获得的最大价值
            ==> base case: i >= arr.length时, dp[i] = 0

     */
    // 递归
    public static int rob1(int[] arr) {
        return dp(arr, 0);
    }

    public static int dp(int[] arr, int index) {
        // base case
        if (index >= arr.length) {
            return 0;
        }

        return Math.max(arr[index] + dp(arr, index + 2),
                dp(arr, index + 1));
    }

    // 动态规划
    public static int rob2(int[] arr) {

        int[] dp = new int[arr.length + 1];
        // base case
        dp[0] = 0;
        dp[1] = arr[0];

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i - 1]);
        }

        return dp[arr.length];
    }

    public static void main(String[] args) {

        int[] arr1 = {2, 7, 9, 3, 1};
        int[] arr2 = {1, 2, 3, 1};

        System.out.println(rob1(arr1)); // 12
        System.out.println(rob2(arr1)); // 12

        System.out.println(rob1(arr2)); // 4
        System.out.println(rob2(arr2)); // 4

    }
}
