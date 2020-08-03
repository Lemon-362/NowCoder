package NowCoder.Exam.Exam1;

import java.util.Arrays;

public class test8 {

    public static void sort(int[] arr, int begin, int end) {
        if (begin >= end)
            return;

//        int pivotIndex = partition1(arr,begin,end);
        int pivotIndex = partition1(arr, begin, end);



        sort(arr, begin, pivotIndex - 1); //sort left  of pivot
        sort(arr, pivotIndex + 1, end); //sort right of pivot
    }

    private static int partition1(int[] arr, int begin, int end) {
        int low = begin;
        int high = end;
        int pivot = arr[low]; // first as pivot

        while (low < high) {
            while (low < high && arr[high] > pivot) {  //pivot is the first, so start from end
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;

        return low;
    }

    public static int partition2(int arr[], int begin, int end) {

        int pivot = arr[end];
        int i = begin - 1; // index of smaller element
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // pivot to the middle
        swap(arr, i + 1, end);

        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j])
            return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr = {25, 84, 21, 47, 15, 27, 68, 35, 20};

    }
}
