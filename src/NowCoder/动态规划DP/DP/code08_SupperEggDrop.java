package NowCoder.动态规划DP.DP;

import java.util.HashMap;

/*
高楼扔鸡蛋:
    一栋共N层的高楼, 给你K个鸡蛋(K>=1)
    要求找到一个楼层 0<=F<=N, 在这层楼扔鸡蛋, [恰好] 没有摔碎(大于F的都会碎, 小于F的都不会碎)
    问 [最坏情况下], [至少] 要扔几次, 才能确定这个F

 */
public class code08_SupperEggDrop {

    /*
    动态规划:
        1. 状态:
            (1) 当前拥有的鸡蛋数K
            (2) 当前要测试的楼层数N
        2. dp数组的含义:
            dp[i][j]: 当前有i个鸡蛋, 要测试共j层, 至少要扔几次
            ==> 最终结果: dp[K][N]
            ==> base case:
                (1) dp[1][...]:
                    K=1时, 只有一个鸡蛋, 就必须得线性扫描(遍历), 一楼一楼的扔, 直到刚好鸡蛋碎了
                ==> dp[1][j] = j
                (2) dp[...][0]:
                    N=0时, 没有楼层, 就不需要扔鸡蛋
                ==> dp[...][0] = 0
        3. 选择, 写状态转移, 并择优
            选择: 去哪个楼层扔鸡蛋
            ==> 需要再用一个for循环, 遍历每个楼层
                对于 共i楼层时, 求此时状态下, 至少需要扔几次
            ==> 在for循环内, 用min找最小值(最优解)

        ==> 对于每个楼层来说, 都有两种可能:
            (1) 这层楼扔鸡蛋, 碎了: 鸡蛋就少了一个, 楼层应该 -1, 往下搜索: 0 - j-1
                ==> dp[i][j] = dp[i - 1][j - 1] + 1(操作数)
            (2) 这层楼扔鸡蛋, 没碎: 鸡蛋没有少, 楼层应该 +1, 往上搜索: j+1 - N
                ==> dp[i][j] = dp[i][N - j] + 1(操作数)
            TODO 注意:
                在dp数组的定义中, j表示的是 要测试 [共j层], 而不是 [第j层],
                所以两种可能中转移时, 不是j+1和j-1
                尽管(1)中是j-1, 但表示的是 在[共j-1] 层楼中测试
                而在(2)中就应该是在剩下的 [共N-j] 层楼中测试

            择优: 因为是找出最差情况, 所以两者中选择max较大的

        4. 转换成代码, 边界问题

        TODO 一般先从递归写起, 然后用备忘录优化, 最后用dp表写动态规划
            (1) 递归的时间复杂度 = 子问题个数(递归树的节点个数) * 解决一个字问题所需的时间
            (2) 动态规划的时间复杂度 = 子问题个数(状态的遍历) * 一个dp函数本身的复杂度
     */
    // 递归写法
    // 时间复杂度: O(N) * O(KN) = O(K*N^2)
    public static int supperEggDrop1(int K, int N){
        return dp(K, N);
    }

    public static int dp(int K, int N){
        // base case
        if (K == 1){
            return N;
        }
        if (N == 0){
            return 0;
        }

        int res = Integer.MAX_VALUE;
        // 遍历当前所有楼层, 以获得最优解
        for (int i = 1; i <= N; i++) {
            res = Math.min(res,
                    Math.max(dp(K - 1, i - 1) + 1,
                             dp(K, N - i) + 1));
        }

        return res;
    }

    // 动态规划
    // 时间复杂度: O(N) * O(KN) = O(K*N^2)
    public static int supperEggDrop2(int K, int N){

        int[][] dp = new int[K + 1][N + 1];
        // base case
        for (int j = 0; j < dp[0].length; j++) {
            dp[1][j] = j;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        for (int i = 2; i <= K; i++) {
            for (int j = 1; j <= N; j++) { // 共j楼层
                int num = Integer.MAX_VALUE;
                for (int k = 1; k <= j; k++) {
                    num = Math.min(num,
                            Math.max(dp[i - 1][k - 1] + 1, dp[i][j - k] + 1));
                }
                dp[i][j] = num;
            }
        }

        return dp[K][N];
    }

    /*
    TODO 优化1:
        1. 备忘录
        2. dp函数的特性: 二分优化
        dp(K - 1, i - 1), dp(K, N - i)
        ==> 在一个状态下(给定K, N)时, dp(K - 1, i - 1)和dp(K, N - i)都只是i的函数
        (1) dp(K - 1, i - 1): 随着i的增加而增加 ==> 单调增
        (2) dp(K, N - i): 随着i的增加而减少 ==> 单调减

        而我们首先要找的是这两者的max, 假设两者的交点是A,
        所以max这个函数, 应该是A之前的都是递减(2), A之后的都是递增(1)

        其次, 要找出max函数的min最小值, 所以就是交点A, 所以可以用二分法, 来找max函数的最小值

        时间复杂度: O(logN) * O(KN) = O(K*N*logN)
     */
    // 递归的优化
    public static int supperEggDrop3(int K, int N){
        return dp1(K, N);
    }

    public static class Node{
        private int K;
        private int N;

        public Node(int k, int n) {
            K = k;
            N = n;
        }
    }

    public static HashMap<Node, Integer> map = new HashMap<>();

    public static int dp1(int K ,int N){
        // base case
        if (K == 1){
            return N;
        }
        if (N == 0){
            return 0;
        }

        if (map.containsKey(new Node(K, N))){
            return map.get(new Node(K, N));
        }

        int res = Integer.MAX_VALUE;
        int left = 1;
        int right = N;
        while (left <= right){
            int mid = left + (right - left) / 2;

            // 碎了 (1)函数
            int broken = dp1(K - 1, mid - 1) + 1;
            // 没碎 (2)函数
            int no_broken = dp1(K, N - mid) + 1;

            // max函数先减(2)后增(1)
            if (broken > no_broken){ // (1) > (2)
                // 说明位于max的增
                right = mid - 1;
                res = Math.min(res, broken);
            }else { // (2) > (1)
                // 说明位于max的减
                left = mid + 1;
                res = Math.min(res, no_broken);
            }
        }

        map.put(new Node(K, N), res);

        return res;
    }

    /*
    TODO 优化2:
        重新定义dp函数的含义
        题目给定K个鸡蛋, N层, 求最少要扔几次
        ==> 给定K个鸡蛋, 最多扔M次, 求此时可以确定的最高楼层F
        ==> dp[K][M]=n: 表示当前K个鸡蛋, 最多可以尝试扔M次, 在这个状态下, 最坏情况下最多可以确定N层楼

        而要求的扔鸡蛋次数此时定义在了自变量M中, 而给定的是最多能确定下N层楼
        最终结果是找到某个符合条件的索引m
        所以只需要改变循环条件即可 ==> for(int m = 1; dp[k][m] < n; m++)

        base case:
        (1) dp[0][...]=0: 0个鸡蛋, 只能确定0层
        (2) dp[...][0]=0: 最多可以扔0次, 只能确定0层

        状态转移:
            dp[k][m]表示的是这个状态下可以确定的最高楼层n, m表示最多可以扔的次数
        (1) 碎了: dp[k][m] --> dp[k-1][m-1], 应该是继续向上走, 看下个状态能确定多少层
        (2) 没碎: dp[k][m] --> dp[k][m-1], 应该是继续向下走, 看下个状态能确定多少层
        而对于dp[k][m]来说, 当前状态下可以确定的总楼层数 = 转移状态可以确定的楼层数 + 1(当前楼层)
        ==> dp[k][m] = dp[k-1][m-1] + dp[k][m-1] + 1
        TODO m表示最多可以扔的次数, 所以始终是 -1的
     */
    public static int supperEggDrop4(int K, int N){

        int[][] dp = new int[K + 1][N + 1];
        // base case
        for (int k = 0; k < dp.length; k++) {
            dp[k][0] = 0;
        }
        for (int n = 0; n < dp[0].length; n++) {
            dp[0][n] = 0;
        }

        // 最多可以扔鸡蛋的次数
        int m = 0;
        while (dp[K][m] < N){ // 当dp[K][m]=N时停止, 此时参数m就是结果
            m++;
            // 一直到总共有K个鸡蛋为止
            for (int k = 1; k <= K; k++) {
                dp[k][m] = dp[k - 1][m - 1] + dp[k][m - 1] + 1;
            }
        }

        return m;
    }

    // 优化2的优化: 一维滚动数组

    public static void main(String[] args) {

        System.out.println(supperEggDrop1(3, 14)); // 4
        System.out.println(supperEggDrop2(3, 14)); // 4
        System.out.println(supperEggDrop3(3, 14)); // 4
        System.out.println(supperEggDrop4(3, 14)); // 4

    }
}
