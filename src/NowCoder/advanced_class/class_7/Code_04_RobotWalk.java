package NowCoder.advanced_class.class_7;

/*
    机器人到达指定位置：
        假设有排成一行的N个位置，记为1~N，N>=2。
        开始时机器人在其中的M位置上，机器人可以往左走或者往右走，
        如果来到1位置，则只能往右来到2位置，如果来到N位置，则只能往左来到N-1位置。
        规定必须走K步，最终来到P位置。
        给定N M K P，求一共有多少种方法？
 */
public class Code_04_RobotWalk {
    // TODO 暴力递归
    public static int ways1(int N, int M, int K, int P) {
        return process(N, M, K, P);
    }

    // N 共1~N个位置
    // curPosition 来到的位置(M)
    // restSteps 可以走的步数(K)
    // P 最终要求到达的位置
    // 返回：一共有多少种走法
    public static int process(int N, int curPosition, int restSteps, int P) {
        if (N < 2 || curPosition < 1 || curPosition > N || restSteps < 0 || P < 1 || P > N) {
            return 0;
        }

        // base case
        if (restSteps == 0) { // 可以走的步数为0，进行判断有没有到达指定位置
            return curPosition == P ? 1 : 0;
        }

        int res = 0;
        if (curPosition == 1) { // 只能往右走
            res = process(N, curPosition + 1, restSteps - 1, P);
        } else if (curPosition == N) { // 只能往左走
            res = process(N, curPosition - 1, restSteps - 1, P);
        } else {
            res = process(N, curPosition + 1, restSteps - 1, P)
                    +
                    process(N, curPosition - 1, restSteps - 1, P);
        }
        return res;
    }

    // TODO 动态规划
    // 在1~N上，从M处走K步到达P（一开始，curPosition=M，restSteps=K）
    public static int ways2(int N, int curP, int leftK, int P) {
        if (N < 2 || curP < 1 || curP > N || leftK < 0 || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[leftK + 1][N + 1]; // i行：剩余步数restSteps，j列：所处位置curPosition
        // 这里列上只有1~N是有用的，0列是无用的
        // base case
        dp[0][P] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) { // 从第二行第一列开始根据递归过程填表
                if (j == 1) { // 只能往右走
                    dp[i][j] = dp[i - 1][j + 1];
                } else if (j == N) { // 只能往左走
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j + 1] + dp[i - 1][j - 1];
                }
            }
        }
        return dp[leftK][curP];
    }

    public static void main(String[] args) {
        System.out.println(ways1(7, 4, 9, 5)); // 116
        System.out.println(ways2(7,4,9,5)); // 116
    }
}
