package NowCoder.动态规划DP.DP;

/*
背包问题变体: 子集分割
    给定只包含正整数的数组, 问是否可以将数组划分成两个子集
    使得两个子集的元素和相等

 */
public class code04_01Package_CanPartition {

    /*
    转换成背包问题:
        求数组所有元素的和sum
        ==> 我们只要找到数组中一个子集等于sum/2, 那么剩下的构成的子集也一定等于sum/2
            所以可以转换成背包问题, 在数组中找物品放进背包, 使得体积刚好是sum/2
        TODO 给定一个背包, 容量是sum/2, 共有N个物品, 每个物品体积是arr[i]
             问能否恰好将该背包装满

        1. [状态] [选择]
            同01背包
            背包容量, 可选的物品
            装进/不装
        2. dp数组的含义
            dp[i][j]=x: 对于前i个物品, 当前背包容量是j, 这种情况下能否装满, 返回true/false
        ==> 最终结果: dp[N][sum/2]
            base case:
            (1) dp[...][0]=true
                因为这里dp返回的是true/false, 而不是01背包的最大价值
                所以当背包体积是0的时候, 不需要选择物品, 一定能装满的
            (2) dp[0][...]=false
                当可选物品是0个时, 无法装满背包
        ==> 两者||或, 只要有一个为true即可
        3. 状态转移
            类似01背包
            (1) 不装: dp[i][j] = dp[i-1][j]
            (2) 装: dp[i][j] = dp[i-1][j-arr[i-1]]
            注意, 由于遍历时从 [i=1] 开始, 而对于arr来说应该是第一个物品, 即 [i-1=1-1=0]
        4. 边界问题
            j < arr[i - 1]时, 直接不装

     */
    public static boolean canPartition1(int[] arr){

        int sum = 0;
        for (int value : arr) {
            sum += value;
        }
        // 总和为奇数时, 是不可能划分出两个相同的子集
        if (sum % 2 != 0){
            return false;
        }

        sum /= 2;
        int N = arr.length;
        boolean[][] dp = new boolean[N + 1][sum + 1];
        // base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = false;
        }

        // 前i个物品
        for (int i = 1; i <= N; i++) {
            // 当前背包容量j
            for (int j = 1; j <= sum; j++) {
                if (j < arr[i - 1]){
                    dp[i][j] = dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        // 最终结果
        return dp[N][sum];
    }

    /*
    TODO 优化: 状态压缩
        根据状态转移方程, 可以看出dp[i][j]只和dp[i-1][...]即上一行有关
        所以可以定义一个一维的dp函数, 仍然两次循环, 在原数组上进行更新

    TODO 注意:
        (1) 为了保证 j-arr[i-1] 位置没有被更新, j 需要从后往前遍历
            另一个原因是, 每个物品只能使用一次
        (2) 此时base case: dp[0]=true, 表示背包容量为0时, 一定可以装满
     */
    public static boolean canPartition2(int[] arr){

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
            // TODO 滚动数组, 必须从后往前遍历, 防止上一次的结果提前被更新
            for (int j = dp.length - 1; j >= 1; j--) {
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
        System.out.println(canPartition1(arr2)); // false
        System.out.println(canPartition2(arr2)); // false

    }

}
