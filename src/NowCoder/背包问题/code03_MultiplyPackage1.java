package NowCoder.背包问题;

/*
多重背包问题 I: TODO 限制了每个物品的个数 只能选择0,1,2,...s[i]个
    有 N 种物品和一个容量是 V 的背包。
    第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
    求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
    输出最大价值。

    TODO 本题数据范围在100以内, 因此可以用三重循环解决

输入格式
    第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
    接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
输出格式
    输出一个整数，表示最大价值。
数据范围
    0<N,V≤100
    0<vi,wi,si≤100
输入样例
4 5
1 2 3
2 4 1
3 4 3
4 5 2
输出样例：
10

 */
public class code03_MultiplyPackage1 {

    /*
    动态规划:
        和完全背包类似, 这里是限制了每个物品的个数, 所以不能一直选择到总体积j

        f[j] = max{f[j - k*v[i]] + k*w[i]}, 0<=k<=s[i] && k*v[i]<=j

     */
    public static int process(int[] v, int[] w, int[] s, int N, int V){

        int[] dp = new int[V + 1];

        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                for (int k = 0; k <= s[i] && k * v[i] <= j; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }

        return dp[V];
    }

    /*
    总结:
        处理第i个物品时, 多重背包问题的模板
            for j: V -> v[i]
                for k: 0 -> k<=s[i] && k*v[i]<=j
                    f[j]=max(f[j], f[j-k*v[i]] + k*w[i])

        多重背包问题
            for i: 1 -> N
                for j: V -> v[i]
                    for k: 0 -> k<=s[i] && k*v[i]<=j
                        f[j]=max(f[j], f[j-k*v[i]] + k*w[i])
     */

    public static void main(String[] args) {
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
        // 一个长度为N的数组，第i个元素表示第i个物品的个数；
        int[] s = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            s[i] = sc.nextInt();
        }
        sc.close();

        process(v, w, s, N, V); // 10

         */

        int N = 4;
        int V = 5;
        // TODO 如果是Scanner, 那么要将v和w数组定义成N+1长度, 从1位置开始接收值
        int[] v = {0, 1, 2, 3, 4};
        int[] w = {0, 2, 4, 4, 5};
        int[] s = {0, 3, 1, 3, 2};

        System.out.println(process(v, w, s, N, V)); // 10
    }
}
