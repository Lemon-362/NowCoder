package NowCoder.basic_class.exercise01.StackQueueList;

public class ZhiPrintMatrix {
    public static void method(int[][] arr) {
        if (arr == null) {
            return;
        }
        int AR = 0;
        int AC = 0;
        int BR = 0;
        int BC = 0;
        int endR = arr.length - 1;
        int endC = arr[0].length - 1;
        boolean flag = true;
        while (AR != endR + 1) {
            print(arr, AR, AC, BR, BC, flag);
            AR = AC == endC ? AR + 1 : AR;
            AC = AC == endC ? AC : AC + 1;
            BC = BR == endR ? BC + 1 : BC;
            BR = BR == endR ? BR : BR + 1;
            flag = !flag;
        }
    }

    public static void print(int[][] arr, int AR, int AC, int BR, int BC, boolean flag) {
        if (flag) {
            while (BR != AR - 1) {
                System.out.print(arr[BR--][BC++] + " ");
            }
        } else {
            while (AR != BR + 1) {
                System.out.print(arr[AR++][AC--] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        method(matrix); // 1 2 5 9 6 3 4 7 10 11 8 12
    }
}
