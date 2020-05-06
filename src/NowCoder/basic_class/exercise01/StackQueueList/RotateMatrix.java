package NowCoder.basic_class.exercise01.StackQueueList;

public class RotateMatrix {
    public static void method(int[][] arr) {
        if (arr == null) {
            return;
        }
        int tR = 0;
        int tC = 0;
        int dR = arr.length - 1;
        int dC = arr[0].length - 1;
        while (tR < dR) {
            print(arr, tR++, tC++, dR--, dC--);
        }
    }

    public static void print(int[][] arr, int tR, int tC, int dR, int dC) {
        int time = dR - tR;
        int tmp = 0;
        for (int i = 0; i < time; i++) {
            tmp = arr[tR][tC + i];
            arr[tR][tC + i] = arr[dR - i][tC];
            arr[dR - i][tC] = arr[dR][dC - i];
            arr[dR][dC - i] = arr[tR + i][dC];
            arr[tR + i][dC] = tmp;
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        method(arr);
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
