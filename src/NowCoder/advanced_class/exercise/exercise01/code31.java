package NowCoder.advanced_class.exercise.exercise01;

public class code31 {
    public static int process(int N, int curP, int leftK, int P) {
        // base case
        if (leftK == 0) {
            return curP == P ? 1 : 0;
        }

        if (curP == 1) {
            return process(N, curP + 1, leftK - 1, P);
        } else if (curP == N) {
            return process(N, curP - 1, leftK - 1, P);
        } else {
            return process(N, curP + 1, leftK - 1, P) + process(N, curP - 1, leftK - 1, P);
        }
    }

    public static int ways1(int N, int M, int K, int P) {
        return process(N, M, K, P);
    }

    public static int ways2(int N, int M, int K, int P){
        int[][] dp = new int[K + 1][N + 1];
        dp[0][P] = 1;

        for (int leftK = 1; leftK < dp.length; leftK++) {
            for (int curP = 1; curP < dp[0].length; curP++) {
                if (curP == 1){
                    dp[leftK][curP] = dp[leftK - 1][curP + 1];
                }else if (curP == N){
                    dp[leftK][curP] = dp[leftK - 1][curP - 1];
                }else {
                    dp[leftK][curP] = dp[leftK - 1][curP + 1] + dp[leftK - 1][curP - 1];
                }
            }
        }

        return dp[K][M];
    }

    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5)); // 116
        System.out.println(ways2(7, 4, 9, 5)); // 116
    }
}
