package NowCoder.advanced_class.Tags.SlidingWindow;

import java.util.LinkedList;
import java.util.List;

/*
    滑动窗口最大值更新结构的应用：最大值-最小值<=num的子数组数量
        给定arr和num，要求子数组满足：最大值-最小值<=num，问一共有多少个子数组？
        要求时间复杂度O(N)
	TODO 子数组问题：看到子数组，就要想到以每个位置开头的结果，答案一定在其中
    TODO 双端队列里面存的是元素的索引！！！
 */
public class code08_AllLessNumSubArray {
    // 暴力法
    public static int getNum01(int[] arr, int num) {
        if (arr == null) {
            return -1;
        }

        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (isValid(arr, i, j, num)){
                    res++;
                }
            }
        }

        return res;
    }

    public static boolean isValid(int[] arr, int i, int j, int aim){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int k = i; k <= j; k++) {
            max = Math.max(max, arr[k]);
            min = Math.min(min, arr[k]);
        }

        return max - min <= aim;
    }

    /* 窗口更新结构法
        TODO 性质:
            (1) 若有一个子数组符合题意, 那么该子数组内所有的子数组都符合
            (2) 若有一个子数组不符合题意, 那么该子数组往外扩的任一个都不符合

     */
    public static int getNum02(int[] arr, int num){
        if (arr == null || arr.length < 1 || num < 1){
            return -1;
        }

        LinkedList<Integer> maxList = new LinkedList<>();
        LinkedList<Integer> minList = new LinkedList<>();
        int res = 0;
        int l = 0;
        int r = 0;

        // 以每个位置开头
        while (l < arr.length){
            while (r < arr.length){
                while (!maxList.isEmpty() && arr[r] >= arr[maxList.peekLast()]){
                    maxList.pollLast();
                }
                maxList.addLast(r);

                while (!minList.isEmpty() && arr[r] <= arr[minList.peekLast()]){
                    minList.pollLast();
                }
                minList.addLast(r);

                if (arr[maxList.peekFirst()] - arr[minList.peekFirst()] > num){
                    break;
                }

                r++;
            }

            res += (r - l);

            if (maxList.peekFirst() == l){
                maxList.pollFirst();
            }
            if (minList.peekFirst() == l){
                minList.pollFirst();
            }

            l++;
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
