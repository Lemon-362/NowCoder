package NowCoder.basic_class.exercise.exercise02;

public class code32 {
    public static int islandsCount(int[][] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1){
                    res++;
                    infect(arr, i, j);
                }
            }
        }

        return res;
    }

    public static void infect(int[][] arr, int i, int j){
        // base case
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] != 1){
            return;
        }

        arr[i][j] = 2;

        infect(arr, i + 1, j);
        infect(arr, i - 1, j);
        infect(arr, i, j + 1);
        infect(arr, i, j - 1);
    }

    public static void main(String[] args) {
        int[][] m1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(islandsCount(m1)); // 3

        int[][] m2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(islandsCount(m2)); // 1
    }
}
