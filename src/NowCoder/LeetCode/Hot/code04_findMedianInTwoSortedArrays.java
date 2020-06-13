package NowCoder.LeetCode.Hot;

/**
 * 寻找两个正序数组的中位数：
 *  给定两个大小为 m 和 n 的正序（从小到大）数组 arr1 和 arr2
 *  请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *  你可以假设 arr1 和 arr2 不会同时为空
 *
 * 思路：
 */
public class code04_findMedianInTwoSortedArrays {
    /*
     TODO 1. 两个有序数组的合并：归并排序
        但是：时间复杂度 O(m+n)，空间复杂度 O(m+n)
     */
    public static double method1(int[] arr1, int[] arr2){
        int len = arr1.length + arr2.length;
        int[] help = new int[len];
        int p1 = 0;
        int p2 = 0;
        int index = 0;

        while (p1 < arr1.length && p2 < arr2.length){
            help[index++] = arr1[p1] < arr2[p2] ? arr1[p1++] : arr2[p2++];
        }

        while (p1 < arr1.length){
            help[index++] = arr1[p1++];
        }
        while (p2 < arr2.length){
            help[index++] = arr2[p2++];
        }

        if (len % 2 == 0){
            return (help[len / 2 - 1] + help[len / 2]) / 2.0;
        }else {
            return help[len / 2];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        System.out.println(method1(arr1, arr2));
    }
}
