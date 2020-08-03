package NowCoder.Exam.Exam1;

import java.util.Scanner;

public class test9 {

    public static void main(String[] args) {

        int[] rec1 = {0, 0, 4, 3};
        int[] rec2 = {0, 1, 5, 4};

        System.out.println(method(rec1, rec2));
    }

    public static int method(int[] arr1, int[] arr2) {

        int zx = Math.abs(arr1[0] + arr1[1] - arr1[2] - arr1[3]); //两个矩形重心在x轴上的距离的两倍
        int x = Math.abs(arr1[0] - arr1[1]) + Math.abs(arr1[2] - arr1[3]);
        int zy = Math.abs(arr2[0] + arr2[1] - arr2[2] - arr2[3]);
        int y = Math.abs(arr2[0] - arr2[1]) + Math.abs(arr2[2] - arr2[3]);
        if (zx <= x && zy <= y) {
            return 1;
        } else {
            return 0;
        }

    }

    public static int isRectangleOverlap(int[] rec1, int[] rec2) {
        int Lx = Math.abs((rec1[0] + rec1[2]) / 2 - (rec2[0] + rec2[2]) / 2);
        int Ly = Math.abs((rec1[1] + rec1[3]) / 2 - (rec2[1] + rec2[3]) / 2);

        int Sx1 = Math.abs(rec1[0] - rec1[2]);
        int Sx2 = Math.abs(rec2[0] - rec2[2]);
        int Sy1 = Math.abs(rec1[1] - rec1[3]);
        int Sy2 = Math.abs(rec2[1] - rec2[3]);
        return Lx < (Sx1 + Sx2) / 2 && Ly < (Sy1 + Sy2) / 2 ? 1 : 0;
    }

    public static int is_rect_intersect(int x01, int x02, int y01, int y02,
                                        int x11, int x12, int y11, int y12) {
        int zx = Math.abs(x01 + x02 - x11 - x12);
        int x = Math.abs(x01 - x02) + Math.abs(x11 - x12);
        int zy = Math.abs(y01 + y02 - y11 - y12);
        int y = Math.abs(y01 - y02) + Math.abs(y11 - y12);
        if (zx <= x && zy <= y)
            return 1;
        else {
            return 0;
        }
    }
}
