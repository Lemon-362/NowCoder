package NowCoder.PackageProblem;

import java.util.Scanner;

/*
完全背包问题: TODO 每个物品可以选无限次 0,1,2,...
    有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
    第 i 种物品的体积是 vi，价值是 wi。
    求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
    输出最大价值。

输入格式
    第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
    接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 种物品的体积和价值。
输出格式
    输出一个整数，表示最大价值。
数据范围
    0<N,V≤1000
    0<vi,wi≤1000
输入样例
4 5
1 2
2 4
3 4
4 5
输出样例：
10
 */
public class code02_FullPackage {

    /*
    普通动态规划:
        1. f[j]: 表示总体积为j时的最大价值

        2. result = max{f[0,...,V]}

        3. 01背包问题的衍生:
        (1) 01背包问题:
            for (int i = 1; i <= N; i++) { // 遍历N个物品
                for (int j = V; j >= v[i]; j--) { // 遍历当前物品的每个状态
                    // 仅在当前总体积j >= 当前物品的体积时, 才更新
                    // TODO 可以将else的判断移除掉了, 因为这里进行判断的时候就是和其前一个物品的状态在进行判断
                    f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
                }
            }
        (2) 完全背包问题: ==> 转换成 01背包 + 无限次选择
            在内层循环中, 可以再使用一个循环, 表示当前物品选择的次数
            for(int j = V; j >= v[i]; j--){
                for(int k = 0; k * v[i] <= j; k++){ // 可以选择的次数, 直到选择的体积超过总体积停止
                    f[j] = max(f[j], f[j - k*v[i]] + k*w[i]);
                }
            }

        ==> f[j] = max{f[j - k*v[i]] + k*w[i]}, 0<=k*v[i]<=j
            对于当前第i个物品, 可以取0个,1个,..., 只要满足取了之后的体积小于当前状态总体积j

     */
    // 最初版本: 二维数组, 01背包最初版本 + 无限次选择
    public static int process1(int[] v, int[] w, int N, int V){

        int[][] dp = new int[N + 1][V + 1];

        dp[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                // 1
                dp[i][j] = dp[i - 1][j];
                // 2
                if (j >= v[i]){
                    for (int k = 0; k * v[i] <= j; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * v[i]] + k * w[i]);
                    }
                }
            }
        }

        return dp[N][V];
    }

    // 优化1: 一维数组, 01背包的优化 + 无限次选择
    public static int process2(int[] v, int[] w, int N, int V){

        int[] dp = new int[V + 1];

        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) { // 第i个物品的所有状态体积
                // TODO 只有在j>=v[i], 才能选第i个物品
                // 每次更新当前状态下的最大价值
                for (int k = 0; k * v[i] <= j; k++) { // 对于第i个物品, 可以选0个,1个,...
                    // 当选择1个的时候, 依赖的是前i-1个物品, 总体积是j-v[i]的结果
                    // 所以更新每个状态依然是从后往前, 这样才能不破坏前i-1个物品的结果
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }

        return dp[V];
    }

    /*
    优化方法:
        两层循环表示的意思:
            当前是第i个物品, 遍历它的每一个状态(体积)
            再次遍历它的选择个数, 只要选择后体积<=当前总体积(状态), 就是可行的
            所以, f[j - k * v[i]] + k * w[i]实际上表示的是在第i个物品, 在选择了(k-1)次的基础上,
            我又选择了它, 并且是满足<=当前总体积j的
            即, 每次加选一个第i个物品时, 当前选择k次的结果依赖的是第i个物品之前选择k-1次的结果

        ==> k只要满足k*v[i]<=j, 就可以逐渐增加
        f[j] = max{f[j - k * v[i]] + k * w[i]}, 0<=k*v[i]<=j

        而根据01背包问题的优化思想, 现在是当前的结果依赖于该物品之前的结果
        所以可以优化成一个循环, 从前往后遍历:
        ==> for(int j = v[i]; j <= V; j++){
                f[j] = max(f[j], f[j - v[i]] + w[i]);
            }

     */
    // 优化2:
    public static int process3(int[] v, int[] w, int N, int V){

        int[] dp = new int[V + 1];

        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = v[i]; j <= V; j++) {
                /*
                当采用正序计算时, dp[j]表示的是当前第i个物品,j体积时的价值
                而不是01背包的第i-1个物品的价值
                所以, 从前往后可以确保 dp[j-v[i]] 选择的是第i个物品(本次循环), 而不是第i-1个物品(上一次循环)
                 */
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }

        return dp[V];
    }

    /*
    总结:
        (1) 01背包 完全背包的状态转移方程
            f[j] = max{f[j], f[j - v[i]] + w[i]}

        (2) 区别
        - 01背包:
            内层循环从后往前计算, 用的是上一次的结果
            for (int i = 1; i <= N; i++) {
                for (int j = V; j >= v[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
                }
            }

        - 完全背包:
            内层循环从前往后计算, 用的是当前i物品的结果, 所以可以重复多次使用
            for (int i = 1; i <= N; i++) {
                for (int j = v[i]; j <= V; j++) {
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
                }
            }

     */

    public static void main(String[] args) {
        // Scanner
        /*
        // 读入数据的代码
        Scanner sc = new Scanner(System.in);
        // 物品的数量为N
        int N = sc.nextInt();
        // 背包的容量为V
        int V = sc.nextInt();
        // 一个长度为N的数组，第i个元素表示第i个物品的体积；
        int[] v = new int[N + 1];
        // 一个长度为N的数组，第i个元素表示第i个物品的价值；
        int[] w = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        sc.close();

         System.out.println(process1(v, w, N, V));

//       System.out.println(process2(v, w, N, V));
         */

        int N = 4;
        int V = 5;
        // TODO 如果是Scanner, 那么要将v和w数组定义成N+1长度, 从1位置开始接收值
        int[] v = {0, 1, 2, 3, 4};
        int[] w = {0, 2, 4, 4, 5};

        System.out.println(process1(v, w, N, V)); // 10
        System.out.println(process2(v, w, N, V)); // 10
        System.out.println(process3(v, w, N, V)); // 10
    }

/*
20 200
24 50
42 60
20 49
7 15
48 115
4 11
3 8
7 5
52 66
50 25
5 8
9 25
14 40
9 22
55 42
40 30
35 49
33 16
12 12
65 127

571
 */
}
