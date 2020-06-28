package NowCoder.basic_class.exercise;

/*
    堆排序：
        数组变成大根堆，数组头一定是数组中最大的
        堆顶与最后一个交换，然后Heapify
        堆大小-1，重复交换和后续

        TODO
            heapInsert: 往数组/大根堆中加入元素的过程, 向上调整(和父节点比较)
                        调整完后, 数组的第一个位置一定是堆顶
            heapify: 数组中一个元素变小, 重新调整为大根堆的过程, 向下调整(和左右孩子比较)
         堆排序思想:
             每次将堆顶变小(和最后一个位置交换), 然后对 0 - size-1 范围内heapify
             那么因为是大根堆, 所以可以保证将堆顶和最后一个位置交换后, 最后一个位置是最大的,
             此时堆顶变小，对堆顶heapify，将堆顶向下调整到合适的位置，
             而交换到最后一个位置的元素是不动的，调整之后 0 - size-1范围内重新形成大根堆
             不断重复, 每次交换最大值到最后, 然后对之前的范围重新调整
 */
public class code08_HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    // 和父节点比较
    public static void heapInsert(int[] arr, int index) {
        // 大根堆
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 和左右孩子比较, size只是保证 0 - size-1范围内的数组可以形成大根堆, 之后的不考虑
    public static void heapify(int[] arr, int index, int size) {
        int left = 2 * index + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 15, 6, 2, 5, 7};
        heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
