package NowCoder.advanced_class.exercise.exercise01;

public class code30 {
    public static int f(int[] arr, int i, int j) {
        // base case
        if (i == j) {
            return arr[i];
        }

        int f_a = arr[i] + s(arr, i + 1, j);
        int f_b = arr[j] + s(arr, i, j - 1);

        return Math.max(f_a, f_b);
    }

    public static int s(int[] arr, int i, int j) {
        // base case
        if (i == j) {
            return 0;
        }

        int s_a = f(arr, i + 1, j);
        int s_b = f(arr, i, j - 1);

        return Math.min(s_a, s_b);
    }

    public static int getMaxScore1(int[] arr) {
        int f_score = f(arr, 0, arr.length - 1);
        int s_score = s(arr, 0, arr.length - 1);

        return Math.max(f_score, s_score);
    }

    public static int getMaxScore2(int[] arr) {
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];

        for (int j = 0; j < arr.length; j++) {
            f[j][j] = 1;
            s[j][j] = 0;
            for (int i = j - 1; i >= 0; i--) {
                int f_a = arr[i] + s[i + 1][j];
                int f_b = arr[j] + s[i][j - 1];
                f[i][j] = Math.max(f_a, f_b);

                int s_a = f[i + 1][j];
                int s_b = f[i][j - 1];
                s[i][j] = Math.min(s_a, s_b);
            }
        }

        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 100, 4};
        System.out.println(getMaxScore1(arr)); // 101
        System.out.println(getMaxScore2(arr)); // 101
    }
}
