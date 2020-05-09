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

        /*
            每次选出最小的放在最前面
            最前面的一个位置永远是处理好的，是当前长度中最小的数
            长度是从头开始减小的
            (0, 1, 2, ..., len-1)
            (1, 2, ..., len-1)
            ......
            (len-2, len-1)
            (len-1)
         */
        for (int i = 0; i < arr.length; i++) { // 每次最小数所放的位置
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) { // 在后续选出最小的数
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
