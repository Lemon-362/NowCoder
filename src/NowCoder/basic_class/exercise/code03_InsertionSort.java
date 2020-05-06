package NowCoder.basic_class.exercise;

/*
    插入排序：O(N^2)
        扑克牌整理顺序，每次处理一个数，之前都有序，往有序里插入到对应的位置
    是对前面有序数组进行重新排序，后面的都是待插入的数
 */
public class code03_InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j+1]; j--) {
                swap(arr, j, j+1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
