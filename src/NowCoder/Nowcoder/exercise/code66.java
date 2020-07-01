package NowCoder.Nowcoder.exercise;

public class code66 {
    public static int movingCount(int m, int n, int k) {
        if (m < 1 || n < 1 || k < 0) {
            return 0;
        }

        boolean[][] flag = new boolean[m][n];

        return process(m, n, k, 0, 0, flag);
    }

    public static int process(int m, int n, int k, int i, int j, boolean[][] flag) {
        // base case
        if (i < 0 || i >= m || j < 0 || j >= n || !isValid(i, j, k) || flag[i][j]) {
            return 0;
        }

        flag[i][j] = true;

        return 1 + process(m, n, k, i + 1, j, flag)
                + process(m, n, k, i - 1, j, flag)
                + process(m, n, k, i, j + 1, flag)
                + process(m, n, k, i, j - 1, flag);
    }

    public static boolean isValid(int i, int j, int k) {
        int res = 0;

        while (i != 0) {
            res += i % 10;

            i = i / 10;
        }

        while (j != 0) {
            res += j % 10;

            j = j / 10;
        }

        return res <= k;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(3, 1, 0)); // 1
        System.out.println(movingCount(2, 3, 1)); // 3

    }
}
