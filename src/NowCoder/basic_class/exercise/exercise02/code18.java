package NowCoder.basic_class.exercise.exercise02;

public class code18 {
    public static void ZhiPrintMatrix(int[][] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = arr.length - 1;
        int endC = arr[0].length - 1;
        boolean flag = true;

        while (tR <= endR) {
            printEdge(arr, tR, tC, dR, dC, flag);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            flag = !flag;
        }
    }

    public static void printEdge(int[][] arr, int tR, int tC, int dR, int dC, boolean flag) {
        if (flag) {
            while (dR != tR - 1) {
                System.out.print(arr[dR--][dC++] + " ");
            }
        } else {
            while (tR != dR + 1) {
                System.out.print(arr[tR++][tC--] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        ZhiPrintMatrix(matrix); // 1 2 5 9 6 3 4 7 10 11 8 12
    }
}
