package NowCoder.动态规划DP.DP;

/*
01背包问题: TODO 每个物品只能使用一次, 1: 选择, 0: 不选择
    有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
    第 i 件物品的体积是 vi，价值是 wi。
    求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
    输出最大价值。

 */
public class code03_01Package {

    /*
    动态规划标准流程:
        1. 明确 [状态], [选择]
            (1) [状态]: 通过哪几个变量, 可以描述该问题
                ==> 给定几个物品和一个背包的容量, 就可以形成一个背包问题(可能是其子问题)
                ==> 状态: [背包的容量], [可选择物品]
            (2) [选择]: 装进背包, 不装进背包

        2. 明确 [dp函数(数组)的定义]
            因为有两个状态, 所以要定义成二维dp数组dp[i][w]
            dp[i][w]: 对于前i个物品, 当前背包容量为w, 这种情况下可以装下的 [最大价值] 是dp[i][w]
            ==> 最终结果: dp[N][W]
            ==> base case: 当i==0(没有物品选择)或者w==0(背包容量为0)时, dp=0

        3. 根据 [选择], 思考 [状态转移]
            (1) 当前第i个物品不装进背包:
                dp[i][w] = dp[i-1][w]
            (2) 当前第i个物品装进背包:
                dp[i][w] = dp[i-1][w-wt[i-1]] + val[i-1]
             ==> 两者选出较大的

            由于dp二维数组是被定义为 (N+1)*(W+1)的,
            所以当i==0/w==0时, 都是base case
            TODO 由于索引i是从1开始的, 所以 i==1时, 应该表示第一个
                而 对于wt[]和val[]来说, 第一个应该是 wt[0],val[0], 即 i=1 ==> wt[0]
                所以, wt和val数组的索引为 [i-1] 时, 才表示第 [i] 个物品

        4. 转换成代码, 处理 [边界问题]
            边界问题: 当当前物品的体积 > 当前背包容量时, 直接选择不装进背包

        TODO 穷举模板
            for 状态1(第一维) in 状态1的所有取值:
                for 状态2(第二维) in 状态2的所有取值:
                    for ...
                        dp[状态1][状态2][...] = 择优(选择1, 选择2, ...)

     */
    public static int package01Problem(int[] wt, int[] val, int N, int W){

        int[][] dp = new int[N + 1][W + 1];
        // 初始化 base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 0;
        }

        // 状态1: 前i个物品
        for (int i = 1; i <= N; i++) {
            // 状态2: 当前背包容量为w
            for (int w = 1; w <= W; w++) {
                // 边界问题
                if (w < wt[i - 1]){ // 当当前物品的体积 > 当前背包容量时, 直接选择不装进背包
                    dp[i][w] = dp[i - 1][w];
                }else { // 两种选择, 选择较大的一个
                    // TODO 索引i为1, 表示第一个物品, 而对于wt和val数组来说, 第一个物品是wt[i-1]和val[i-1]
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - wt[i - 1]] + val[i - 1]);
                }
            }
        }

        // 最终结果
        return dp[N][W];
    }

    public static void main(String[] args) {

        int[] wt1 = {2, 1, 3};
        int[] val1 = {4, 2, 3};
        System.out.println(package01Problem(wt1, val1, 3, 4)); // 6

        int[] wt2 = {1, 2, 3, 4};
        int[] val2 = {2, 4, 4, 5};
        System.out.println(package01Problem(wt2, val2, 4, 5)); // 8

    }
}
