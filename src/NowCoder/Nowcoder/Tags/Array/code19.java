package NowCoder.Nowcoder.Tags.Array;

import java.util.ArrayList;

public class code19 {
    public static ArrayList<Integer> list = new ArrayList<>();

    public static ArrayList<Integer> printMatrix(int[][] arr){
        if (arr == null || arr.length < 1){
            return null;
        }

        int tR = 0;
        int tC = 0;
        int dR = arr.length - 1;
        int dC = arr[0].length - 1;

        while (tR <= dR && tC <= dC){
            print(arr, tR++, tC++, dR--, dC--);
        }

        return list;
    }

    public static void print(int[][] arr, int tR, int tC, int dR, int dC){
        if (tR == dR){
            for (int i = tC; i <= dC; i++) {
                list.add(arr[tR][i]);
            }
        }else if (tC == dC){
            for (int i = tR; i <= dR; i++) {
                list.add(arr[i][tC]);
            }
        }else {
            int curR = tR;
            int curC = tC;
            while (curC < dC){
                list.add(arr[curR][curC++]);
            }
            while (curR < dR){
                list.add(arr[curR++][curC]);
            }
            while (curC > tC){
                list.add(arr[curR][curC--]);
            }
            while (curR > tR){
                list.add(arr[curR--][curC]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};
        ArrayList<Integer> list = printMatrix(arr);
        for (Integer num : list) {
            System.out.print(num + " ");
        } // 1 2 3 4 5 10 15 20 25 24 23 22 21 16 11 6 7 8 9 14 19 18 17 12 13
    }
}
