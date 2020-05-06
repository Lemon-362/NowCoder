package NowCoder.basic_class.exercise;

/*
	转圈打印矩阵：
		1	2	3	4
		5	6	7	8	----->   1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
		9	10	11	12
		13	14	15	16
	已知左上角和右下角，就可以打印四个边框
	然后左上和右下沿对角线往中间移动，每次打印一个框
 */

public class code15_PrintMatrixSpiralOrder {
    public static void PrintMatrixSpiralOrder(int[][] arr) {
        if (arr == null) {
            return;
        }
        int tR = 0;
        int tC = 0;
        int dR = arr.length - 1;
        int dC = arr[0].length - 1;
        while (tR <= dR && tC <= dC) {
            printEdge(arr, tR++, tC++, dR--, dC--);
        }
    }

    public static void printEdge(int[][] arr, int tR, int tC, int dR, int dC) {
        if (tR == dR) {
            for (int i = tC; i <= dC; i++) {
                System.out.print(arr[dR][i] + " ");
            }
        } else if (tC == dC) {
            for (int i = tR; i <= dR; i++) {
                System.out.print(arr[i][tC] + " ");
            }
        } else {
            int curR = tR;
            int curC = tC;
            while (curC < dC) {
                System.out.print(arr[curR][curC++] + " ");
            }
            while (curR < dR) {
                System.out.print(arr[curR++][curC] + " ");
            }
            while (curC > tC) {
                System.out.print(arr[curR][curC--] + " ");
            }
            while (curR > tR) {
                System.out.print(arr[curR--][curC] + " ");
            }
        }
    }


    public static void main(String[] args) {
//        int[][] matrix1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
//                {13, 14, 15, 16}};
        int[][] matrix1 = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        int[][] matrix2 = {{1, 2, 3, 4}};
        int[][] matrix3 = {{1}, {5}, {9}, {13}};
        PrintMatrixSpiralOrder(matrix1); // 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
        // 1 2 3 4 5 10 15 20 25 24 23 22 21 16 11 6 7 8 9 14 19 18 17 12 13
        System.out.println();
        PrintMatrixSpiralOrder(matrix2); // 1 2 3 4
        System.out.println();
        PrintMatrixSpiralOrder(matrix3); // 1 5 9 13
    }
}
