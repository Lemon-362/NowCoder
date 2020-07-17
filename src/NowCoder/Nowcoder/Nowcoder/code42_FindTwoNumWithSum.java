package NowCoder.Nowcoder.Nowcoder;

import java.util.ArrayList;

/*
    输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S
    如果有多对数字的和等于S，输出两个数的乘积最小的。
    TODO 给定一个范围的数组，可以使用 双指针法
        特别的：数组递增时，移动左边的i是增加，移动右边的j是减小
 */
public class code42_FindTwoNumWithSum {
    public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        // 双指针，分别指向头部和尾部
        // 每次先求和num
        // 如果 num < sum，由于是递增数组，如果j左移，num只会更小，所以i右移
        // 如果 num > sum，如果i右移，num只会更大，所以j左移
        // 如果 num = sum，记录，不需要，因为只要找到第一组，就一定是乘积最小的（并且i和j同时向内移动）
        int i = 0;
        int j = array.length - 1;
        int num = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (i < j) { // 必须是两个数，i和j不可能相遇
            num = array[i] + array[j];
            if (num < sum) { // 说明当前和小了，要增大，
                i++;
            } else if (num > sum) {
                j--;
            } else {
                // 要求乘积最小，也就是找第一组，因为数组递增，越往内，乘积只会越大
                list.add(array[i]);
                list.add(array[j]);
                break; // 找到第一组就停止，不需要往下找了
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {10, 26, 30, 31, 47, 60};
        ArrayList<Integer> res = FindNumbersWithSum(arr, 40);
        for (Integer num : res) {
            System.out.print(num + " "); // 10 30
        }
    }
}
