package NowCoder.advanced_class.exercise.advance;

/*
    机器人到达指定位置：
        假设有排成一行的N个位置，记为1~N，N>=2。
        开始时机器人在其中的M位置上，机器人可以往左走或者往右走，
        如果来到1位置，则只能往右来到2位置，如果来到N位置，则只能往左来到N-1位置。
        规定必须走K步，最终来到P位置。
        给定N M K P，求一共有多少种方法？
 */
public class code31_RobotWalk {
    /*
        1. 暴力递归:
            对于其中的一步,可能往左可能往右,最终如果到达P,就记录为一个方法
     */
    // curP: 当前位置  leftK: 剩余步数
    public static int process(int N, int curP, int leftK, int P) {
        if (leftK == 0) {
            return curP == P ? 1 : 0;
        }

        if (curP == 1) {
            return process(N, curP + 1, leftK - 1, P);
        } else if (curP == N) {
            return process(N, curP - 1, leftK - 1, P);
        } else {
            return process(N, curP + 1, leftK - 1, P)
                    + process(N, curP - 1, leftK - 1, P);
        }
    }

    public static int ways1(int N, int M, int K, int P) {
        return process(N, M, K, P);
    }

    /*
        2. 动态规划:
     */
    public static int ways2(int N, int M, int K, int P) {
        int[][] dp = new int[K + 1][N + 1];

        dp[0][P] = 1;

        for (int leftK = 1; leftK < dp.length; leftK++) {
            for (int curP = 1; curP < dp[0].length; curP++) {
                if (curP == 1) {
                    dp[leftK][curP] = dp[leftK - 1][curP + 1];
                } else if (curP == N) {
                    dp[leftK][curP] = dp[leftK - 1][curP - 1];
                } else {
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
