package NowCoder.basic_class.exercise01.HashMap;

public class Islands {
    public static int IslandsCount(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int row = arr.length;
        int col = arr[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == 1){
                    res++;
                    infect(arr, i, j, row, col);
                }
            }
        }
        return res;
    }

    public static void infect(int[][] arr, int i, int j, int row, int col){
        if (i < 0 || i >= row || j < 0 || j >= col || arr[i][j] != 1){
            return;
        }
        arr[i][j] = 2;
        infect(arr, i+1, j, row, col);
        infect(arr, i-1, j, row, col);
        infect(arr, i, j+1, row, col);
        infect(arr, i, j-1, row, col);
    }

    public static void main(String[] args) {
        int[][] m1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(IslandsCount(m1)); // 3

        int[][] m2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(IslandsCount(m2)); // 1
    }
}
