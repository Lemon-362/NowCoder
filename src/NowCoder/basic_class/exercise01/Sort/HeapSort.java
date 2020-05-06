package NowCoder.basic_class.exercise01.Sort;

public class HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            heapInsert(arr, i);
        }
        swap(arr, 0, --len);
        while (len > 0) {
            heapify(arr, 0, len);
            swap(arr, 0, --len);
        }

    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void heapify(int[] arr, int index, int len) {
        int left = 2 * index + 1;
        while (left < len) {
            int largest = left + 1 < len && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                return;
            }
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }
}
