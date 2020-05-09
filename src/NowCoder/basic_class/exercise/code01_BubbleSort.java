package NowCoder.basic_class.exercise;

/*
    冒泡排序：O(N^2)
        每次比较两个数，将较大的数放在后面，交换完最大的数在最后，遍历长度--
        每次比较一对相邻元素，如果前者大就交换，最终把总长里的最大值放在最后，然后再遍历长度-1，继续比较交换
 */
public class code01_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        /*
            每次比较一对并交换，一路走到最后
            最后一个位置永远是处理好的，是当前长度中最大的数
            长度是从尾开始减小的
            (0, 1), (1, 2), ..., (len-2, len-1)
            (0, 1), (1, 2), ..., (len-3, len-2)
            ......
            (0, 1), (1, 2)
            (0, 1)
         */
        for (int i = arr.length - 1; i > 0; i--) { // 每次要处理的长度
            for (int j = 0; j < i; j++) { // 在一个长度里每次比较一对相邻元素并交换
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
