package NowCoder.Exam.Exam2;

import java.util.Scanner;

/*
    奇安信

100
5
77 92
22 22
29 36
50 46
99 90
 */
public class test1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int V = scanner.nextInt();
        int N = scanner.nextInt();

        int[] v = new int[N + 1];
        int[] w = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }

        process1(v, w, N, V);
    }

    public static void process1(int[] v, int[] w, int N, int V){

        int[] dp = new int[V + 1];

        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) { // 第i个物品的所有状态体积
                // 每次更新当前状态下的最大价值
                for (int k = 0; k * v[i] <= j; k++) { // 对于第i个物品, 可以选0个,1个,...
                    // 当选择1个的时候, 依赖的是前i-1个物品, 总体积是j-v[i]的结果
                    // 所以更新每个状态依然是从后往前, 这样才能不破坏前i-1个物品的结果
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }

        System.out.println(dp[V]);
    }

}
