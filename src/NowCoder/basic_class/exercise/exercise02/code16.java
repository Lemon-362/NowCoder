package NowCoder.basic_class.exercise.exercise02;

public class code16 {
    public static void rotate(int[][] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        int tR = 0;
        int tC = 0;
        int dR = arr.length - 1;
        int dC = arr[0].length - 1;

        while (tR < dR){
            printEdge(arr, tR++, tC++, dR--, dC--);
        }
    }

    public static void printEdge(int[][] arr, int tR, int tC, int dR, int dC){
        int k = dR - tR;
        int tmp;

        for (int i = 0; i < k; i++) {
            tmp = arr[tR][tC + i];
            arr[tR][tC + i] = arr[dR - i][tC];
            arr[dR - i][tC] = arr[dR][dC - i];
            arr[dR][dC - i] = arr[tR + i][dC];
            arr[tR + i][dC] = tmp;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
//        13 9 5 1
//        14 10 6 2
//        15 11 7 3
//        16 12 8 4
    }
}
