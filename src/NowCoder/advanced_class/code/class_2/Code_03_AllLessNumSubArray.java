package NowCoder.advanced_class.code.class_2;

import java.util.LinkedList;

/*
    最大值减去最小值<=num的子数组数量：
        给定arr和num，要求子数组满足：最大值-最小值<=num，问一共有多少个子数组？
        要求时间复杂度O(N)
 */
public class Code_03_AllLessNumSubArray {

    // 暴力法：O(N^3) 不要看！！！（对数器的时候使用）
    public static int getNum1(int[] arr, int num) {
        int res = 0;
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                //[start...end] 就是子数组
                if (isValid(arr, start, end, num)) {
                    res++;
                }
            }
        }
        return res;
    }

    // 判断子数组是否符合题意
    public static boolean isValid(int[] arr, int start, int end, int num) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        return max - min <= num;
    }

    // 滑动窗口和窗口最大/小值更新结构：O(N)
    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 用两个双向链表来存储窗口内的最大值和最小值
        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int L = 0;
        int R = 0;
        int res = 0;
        // 遍历每个数，找以每个数开头的直到不满足条件时R的位置，那么这个子数组内的所有子数组都满足条件
        while (L < arr.length) {
            // L确定，R向右扩，直到不符合条件(max-min > num)时停止
            while (R < arr.length) {
                // 窗口更新
                // 最小值结构更新：头到尾是从小到大的
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                // 最大值结构更新：头到尾是从大到小的
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                // 此时得到了窗口内的最大值和最小值（都在头部）

                // 是否符合条件，若不符合了则找到了以当前L开头的极限位置
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                // 如果还满足条件，R继续往右扩
                R++;
            }

            // 此时不满足条件了，R停止扩
            // 因为L要往右缩，且窗口没有要求长度，所以先更新窗口结构

            // TODO 头部过期是因为L往右缩了，所以只要看从窗口出去的那个值是否还在双向链表中，如果在就弹出。
            // 最小值下标有没有过期
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            // 最大值下标有没有过期
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            // 得到以L开头的整个符合条件的子数组的数量
            res += R - L; // R现在位于x+1位置，L位于0位置，0-x一共有x+1个子数组
            // L向右移动一个
            L++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int num = 2;
        System.out.println(getNum(arr, num)); // 12
    }
}
