package NowCoder.Nowcoder.exercise;

public class code66 {
    public static int movingCount(int m, int n, int k) {
        if (m < 1 || n < 1 || k < 0) {
            return 0;
        }

        int[][] arr = new int[m][n];

        return process(arr, 0, 0, k);
    }

    public static int process(int[][] arr, int i, int j, int k) {
        // base case
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] == 1) {
            return 0;
        }
        if (!isValid(i, j, k)) {
            return 0;
        }

        arr[i][j] = 1;

        return 1 + process(arr, i + 1, j, k)
                + process(arr, i - 1, j, k)
                + process(arr, i, j + 1, k)
                + process(arr, i, j - 1, k);
    }

    public static boolean isValid(int i, int j, int k) {
        int res = 0;
        while (i > 0){
            res += i % 10;

            i /= 10;
        }
        while (j > 0){
            res += j % 10;

            j /= 10;
        }

        return res <= k;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(3, 1, 0)); // 1
        System.out.println(movingCount(2, 3, 1)); // 3

    }
}
