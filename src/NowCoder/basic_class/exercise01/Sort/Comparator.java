package NowCoder.basic_class.exercise01.Sort;

import java.util.Arrays;
import java.util.Queue;

// 对数器
public class Comparator {
    public static void main(String[] args) {
        int count = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean flag = true;

        for (int i = 0; i < count; i++) {
            // 产生随机数组
            int[] arr = generateArr(maxSize, maxValue);
            // 拷贝数组
            int[] newArr = copyArr(arr);
            // 两个方法分别对两个数组使用
            test(newArr);
            // TODO 此处加上 排序方法
//            BubbleSort.insertSort(arr);
//            SelectionSort.selectionSort(arr);
//            InsertionSort.insertionSort(arr);
//            MergeSort.mergeSort(arr);
            QuickSort.quickSort(arr);
//            HeapSort.heapSort(arr);
            // 比较
            if (!comparator(arr, newArr)) {
                flag = false;
                break;
            }
        }

        System.out.println(flag ? "Nice!" : "Fucking fucked!");

        // 具体数组
        int[] arr = generateArr(maxSize, maxValue);
        // 打印排序前的数组
        printArr(arr);
        // TODO 此处加上 排序方法
//        BubbleSort.insertSort(arr);
//        SelectionSort.selectionSort(arr);
//        InsertionSort.insertionSort(arr);
//        MergeSort.mergeSort(arr);
        QuickSort.quickSort(arr);
//        HeapSort.heapSort(arr);
        // 打印排序后的数组
        printArr(arr);
    }


    // 绝对正确的排序方法
    public static void test(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArr(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static boolean comparator(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
