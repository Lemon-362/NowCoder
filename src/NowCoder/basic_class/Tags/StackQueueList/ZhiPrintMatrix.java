package NowCoder.basic_class.Tags.StackQueueList;

public class ZhiPrintMatrix {
    public static void method(int[][] arr) {
        if (arr == null) {
            return;
        }

        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = arr.length;
        int endC = arr[0].length;
        boolean flag = true;

        while (tR < endR && tC < endC){
            print(arr, tR, tC, dR, dC, flag);
            tR = tC == endC - 1 ? tR + 1 : tR;
            tC = tC == endC - 1 ? tC : tC + 1;
            dC = dR == endR - 1 ? dC + 1 : dC;
            dR = dR == endR - 1 ? dR : dR + 1;
            flag = !flag;
        }
    }

    public static void print(int[][] arr, int tR, int tC, int dR, int dC, boolean flag) {
        if (flag){
            while (dR != tR - 1){
                System.out.print(arr[dR--][dC++] + " ");
            }
        }else {
            while (tR != dR + 1){
                System.out.print(arr[tR++][tC--] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        method(matrix); // 1 2 5 9 6 3 4 7 10 11 8 12
    }
}
