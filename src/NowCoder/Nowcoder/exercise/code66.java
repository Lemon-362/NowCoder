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
        if (i < 0 || i >= m || j < 0 || j >= n || flag[i][j]) {
            return 0;
        }
        if (isValid(i, j, k)) {
            flag[i][j] = true;
        } else {
            return 0;
        }

        return 1 + process(m, n, k, i + 1, j, flag) + process(m, n, k, i - 1, j, flag) + process(m, n, k, i, j + 1, flag) + process(m, n, k, i, j - 1, flag);
    }

    public static boolean isValid(int i, int j, int k) {
        int sum = i % 10 + i / 10;
        sum += j % 10 + j / 10;

        return sum <= k;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(3, 1, 0)); // 1
        System.out.println(movingCount(2, 3, 1)); // 3

    }
}
