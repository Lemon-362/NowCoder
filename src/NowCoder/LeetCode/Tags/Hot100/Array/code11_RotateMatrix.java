package NowCoder.LeetCode.Tags.Hot100.Array;

/*
48. 旋转图像
    给定一个 n × n 的二维矩阵表示一个图像。
    将图像顺时针旋转 90 度

    basic_Code_05_RotateMatrix
 */
public class code11_RotateMatrix {
    public static void rotateMatrix(int[][] arr) {
        if (arr == null) {
            return;
        }

        int tR = 0;
        int tC = 0;
        int dR = arr.length - 1;
        int dC = arr[0].length - 1;

        while (tR <= dR){
            print(arr, tR++, tC++, dR--, dC--);
        }
    }

    public static void print(int[][] arr, int tR, int tC, int dR, int dC){
        int times = dR - tR;

        for (int i = 0; i < times; i++) {
            int temp = arr[tR][tC + i];
            arr[tR][tC + i] = arr[dR - i][tC];
            arr[dR - i][tC] = arr[dR][dC - i];
            arr[dR][dC - i] = arr[tR + i][dC];
            arr[tR + i][dC] = temp;
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        rotateMatrix(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        //        13 9 5 1
        //        14 10 6 2
        //        15 11 7 3
        //        16 12 8 4
    }
}
