package NowCoder.advanced_class.exercise;

import java.util.LinkedList;
import java.util.List;

/*
    滑动窗口最大值更新结构的应用：最大值-最小值<=num的子数组数量
        给定arr和num，要求子数组满足：最大值-最小值<=num，问一共有多少个子数组？
        要求时间复杂度O(N)
	TODO 子数组问题：看到子数组，就要想到以每个位置结尾的结果，答案一定在其中
    TODO 双端队列里面存的是元素的索引！！！
 */
public class code08_AllLessNumSubArray {
    // 暴力法
    public static int getNum01(int[] arr, int num) {
        if (arr == null) {
            return -1;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) { // 以每个位置开头
            for (int j = i; j < arr.length; j++) {
                // 找以i开头的i-j上所有满足条件的数组
                if (isValid(arr, i, j, num)) {
                    res++;
                }
            }
        }
        return res;
    }

    public static boolean isValid(int[] arr, int i, int j, int num) {
        // 对每个数组进行判断
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            max = Math.max(max, arr[k]);
            min = Math.min(min, arr[k]);
        }
        return max - min <= num;
    }

    // 窗口更新结构法
    public static int getNum02(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        LinkedList<Integer> minList = new LinkedList<>();
        LinkedList<Integer> maxList = new LinkedList<>();
        int res = 0;
        int L = 0;
        int R = 0;

        while (L < arr.length){
            while (R < arr.length){
                while (!minList.isEmpty() && arr[R] <= arr[minList.peekLast()]){
                    minList.pollLast();
                }
                minList.addLast(R);

                while (!maxList.isEmpty() && arr[R] >= arr[maxList.peekLast()]){
                    maxList.pollLast();
                }
                maxList.addLast(R);

                if (arr[maxList.peekFirst()] - arr[minList.peekFirst()] > num){
                    break;
                }

                R++;
            }

            res += (R - L);

            if (minList.peekFirst() == L){
                minList.pollFirst();
            }
            if (maxList.peekFirst() == L){
                maxList.pollFirst();
            }

            L++;
        }

        return res;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int num = 2;
        System.out.println(getNum01(arr, num)); // 12
        System.out.println(getNum02(arr, num));
    }
}
