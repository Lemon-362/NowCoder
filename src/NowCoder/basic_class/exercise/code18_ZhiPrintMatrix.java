package NowCoder.basic_class.exercise;

import java.security.PublicKey;
import java.sql.SQLOutput;

/*
	之字形打印矩阵：
		 A B 两点，分别往右和往下移动，每次移动一格，打印一次对角线
		右上 --> 左下
		左下 --> 右上
		移动到头，A往下，B往右
 */
public class code18_ZhiPrintMatrix {
    public static void ZhiPrintMatrix(int[][] arr) {
        if (arr == null) {
            return;
        }
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = arr.length - 1;
        int endC = arr[0].length - 1;
        boolean flag = false;
        while (tR != endR + 1) {
            print(arr, tR, tC, dR, dC, flag);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            flag = !flag;
        }
    }

    public static void print(int[][] arr, int tR, int tC, int dR, int dC, boolean flag) {
        if (flag){
            while (tR != dR + 1){
                System.out.print(arr[tR++][tC--] + " ");
            }
        }else {
            while (dR != tR - 1){
                System.out.print(arr[dR--][dC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        ZhiPrintMatrix(matrix); // 1 2 5 9 6 3 4 7 10 11 8 12
    }
}
