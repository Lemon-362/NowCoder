package NowCoder.LeetCode.Hot;

import java.util.*;

/**
 * 下一个排列：
 *  找到给定数组重新排列成比当前的字典序更大的下一个排列
 *  如果没有更大的，则返回字典序最小的排列（即升序排列）
 *
 * TODO 寻找下一个字典序的步骤：
 *  1. 从后往前找到索引k，满足arr[k]<arr[k+1]，如果不存在，说明当前数组是最后一个字典序，则翻转整个数组
 *  2. 从后往前找到索引l，满足arr[l]>arr[k]
 *  3. 交换l和k位置的元素
 *  4. 翻转从k+1到最后的数组元素
 */
public class code15_NextPermutation {
    public static void nextPermutation(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        // 从后往前找到索引k，满足arr[k]<arr[k+1]
        int k = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]){
                k = i;
                break;
            }
        }

        // 如果不存在，说明当前数组是最后一个字典序，则翻转整个数组
        if (k == -1){
            reverse(arr, 0, arr.length - 1);
            return;
        }

        // 从后往前找到索引l，满足arr[l]>arr[k]
        int l = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > arr[k]){
                l = i;
                break;
            }
        }

        // 交换l和k位置的元素
        swap(arr, l, k);

        // 翻转从k+1到最后的数组元素
        reverse(arr, k + 1, arr.length - 1);
        return;
    }

    private static void reverse(int[] arr, int start, int end) {   //翻转数组
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1};
        int[] arr2 = {3, 2, 1};
        int[] arr3 = {1, 1, 5};

        nextPermutation(arr1);
        printArr(arr1);

        nextPermutation(arr2);
        printArr(arr2);

        nextPermutation(arr3);
        printArr(arr3);


    }
}
