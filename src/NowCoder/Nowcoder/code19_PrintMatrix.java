package NowCoder.Nowcoder;

import java.util.ArrayList;

/*
    输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
        旋转打印矩阵，这里输出是ArrayList
 */
public class code19_PrintMatrix {
    public static ArrayList<Integer> method(int[][] arr) {
        if (arr == null) {
            return null;
        }
        int tR = 0;
        int tC = 0;
        int dR = arr.length - 1;
        int dC = arr[0].length - 1;
        ArrayList<Integer> list = new ArrayList<>();
        // 想一个办法，把每个值都输到list中
        // 并且保证对角线往里走的同时，list还能继续加东西
        // 因此不能用之前的方法，再调用一个函数，那样的话会将之前的list替换掉
        while (tR <= dR && tC <= dC) {
            if (tR == dR) {
                for (int i = tC; i <= dC; i++) {
                    list.add(arr[tR][i]);
                }
            } else if (tC == dC) {
                for (int i = tR; i <= dR; i++) {
                    list.add(arr[i][tC]);
                }
            } else {
                int curR = tR;
                int curC = tC;
                while (curC < dC) {
                    list.add(arr[curR][curC++]);
                }
                while (curR < dR) {
                    list.add(arr[curR++][curC]);
                }
                while (curC > tC) {
                    list.add(arr[curR][curC--]);
                }
                while (curR > tR) {
                    list.add(arr[curR--][curC]);
                }
            }
            tR = tR + 1;
            tC = tC + 1;
            dR = dR - 1;
            dC = dC - 1;
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        ArrayList<Integer> list = method(arr);
        for (Integer num : list) {
            System.out.print(num + " ");
        }
    }
}


