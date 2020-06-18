package NowCoder.basic_class.exercise.exercise02;

public class code04 {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int l, int r){
        // base case
        if (l == r){
            return;
        }

        int mid = (l + r) >> 1;

        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        merge(arr, l, r, mid);
    }

    public static void merge(int[] arr, int l, int r, int mid){
        int[] help = new int[r - l + 1];
        int index = 0;
        int p1 = l;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= r){
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid){
            help[index++] = arr[p1++];
        }

        while (p2 <= r){
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 4, 2, 54, 61, 23, 5, 1};
        mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
