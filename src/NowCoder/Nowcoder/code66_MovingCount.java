package NowCoder.Nowcoder;


/*
    机器人的运动范围：
        地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
        一个机器人从坐标 [0, 0] 的格子开始移动，
        它每次可以向左、右、上、下移动一格（不能移动到方格外），
        也不能进入行坐标和列坐标的数位之和大于k的格子。
          例如，
            当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
            但它不能进入方格 [35, 38]，因为3+5+3+8=19。
        请问该机器人能够到达多少个格子？
    1 <= n, m <= 100
    0 <= k <= 20
 */
public class code66_MovingCount {
    public static boolean isValid(int i, int j, int k) {
        int sum = i % 10 + i / 10;
        sum += j % 10 + j / 10;

        return sum <= k;
    }

    public static int movingCount(int m, int n, int k) {
        if (m < 1 || n < 1 || k < 0) {
            return 0;
        }

        boolean[][] flag = new boolean[m][n];

        return process(m, n, 0, 0, k, flag);
    }

    public static int process(int m, int n, int i, int j, int k, boolean[][] flag) {
        // base case
        if (i < 0 || i >= m || j < 0 || j >= n || !isValid(i, j, k) || flag[i][j]) {
            return 0;
        }

        flag[i][j] = true;

        return  1
                + process(m, n, i - 1, j, k, flag)
                + process(m, n, i + 1, j, k, flag)
                + process(m, n, i, j - 1, k, flag)
                + process(m, n, i, j + 1, k, flag);
    }

    public static void main(String[] args) {
        System.out.println(movingCount(3, 1, 0)); // 1
        System.out.println(movingCount(2, 3, 1)); // 3

    }
}
