package NowCoder.basic_class.exercise;

/*
    选择排序：O(N^2)
        先假设最小索引是第一个，然后遍历后面的数，选出最小的索引，
        将该索引和最前面的调换位置，遍历长度--
 */
public class code02_SelectionSort {
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2){
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr, minIndex, i);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
