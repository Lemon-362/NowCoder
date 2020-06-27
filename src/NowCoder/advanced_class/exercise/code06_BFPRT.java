package NowCoder.advanced_class.exercise;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
	BFPRT算法：求无序数组中第k小/大的数
		找出无序数组中最小的k个数
 */
public class code06_BFPRT {
    public static class getMinNumByHeap {
        public static int[] getMinByHeap(int[] arr, int k) {
            if (arr == null || arr.length < 1 || k < 1) {
                return null;
            }

            // TODO 维护一个大根堆, 存储较小的k个数
            // TODO 如果直接用小根堆, 那不如直接排序划算
            int[] heap = new int[k];
            for (int i = 0; i < k; i++) {
                heapInsert(heap, arr[i], i);
            }

            /* TODO 因为大根堆存的是较小的k个数, 如果arr中有比 较小的k个数中最大的(堆顶) 大的数,
                    那么就将堆顶变成该数, 始终对这k个数进行调整, 形成大根堆

                    如果直接使用小根堆来取堆顶往下的k个较小的数的话, 那么代价不如排序
             */

            for (int i = k; i < arr.length; i++) {
                if (arr[i] < heap[0]) {
                    heap[0] = arr[i];
                    heapify(heap, 0, k);
                }
            }

            return heap;
        }

        public static void heapify(int[] heap, int index, int size) {
            int left = 2 * index + 1;
            while (left < size) {
                int largest = left + 1 < size && heap[left + 1] > heap[left] ? left + 1 : left;
                largest = heap[largest] > heap[index] ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(heap, index, largest);
                index = largest;
                left = 2 * index + 1;
            }
        }

        public static void heapInsert(int[] heap, int num, int index) {
            heap[index] = num;
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static class getMinNumByBFPRT {
        public static int[] getMinByBFPRT(int[] arr, int k) {
            if (arr == null || arr.length < 1 || k < 1) {
                return null;
            }

            int kthMinNum = getKthMinNum(arr, k);

            int[] res = new int[k];
            int index = 0;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < kthMinNum) {
                    res[index++] = arr[i];
                }
            }

            while (index < res.length) {
                res[index++] = kthMinNum;
            }

            return res;
        }

        public static int getKthMinNum(int[] arr, int k) {
            int[] copyArr = copyArray(arr);
            return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
        }

        public static int bfprt(int[] arr, int l, int r, int k) {
            if (l == r) {
                return arr[l];
            }

            int num = medianInMedians(arr, l, r);
            int[] p = partition(arr, l, r, num);
            if (k >= p[0] && k <= p[1]) {
                return num;
            } else if (k < p[0]) {
                return bfprt(arr, l, p[0] - 1, k);
            } else {
                return bfprt(arr, p[1] + 1, r, k);
            }
        }

        public static int[] partition(int[] arr, int l, int r, int num) {
            int less = l - 1;
            int more = r + 1;
            int cur = l;

            while (cur < more) {
                if (arr[cur] < num) {
                    swap(arr, ++less, cur++);
                } else if (arr[cur] > num) {
                    swap(arr, --more, cur);
                } else {
                    cur++;
                }
            }

            return new int[]{less + 1, more - 1};
        }

        public static int medianInMedians(int[] arr, int l, int r) {
            int len = r - l + 1;
            int offset = len % 5 == 0 ? 0 : 1;
            int group = len / 5 + offset;
            int[] mArr = new int[group];

            for (int i = 0; i < group; i++) {
                int start = l + 5 * i;
                int end = start + 4;
                if (i == group - 1) {
                    mArr[i] = getMedian(arr, start, r);
                } else {
                    mArr[i] = getMedian(arr, start, end);
                }
            }

            return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
        }

        public static int getMedian(int[] arr, int l, int r) {
            insertSort(arr, l, r);
            int mid = (l + r) >> 1;
            return arr[mid];
        }

        public static void insertSort(int[] arr, int l, int r) {
            for (int i = l + 1; i <= r; i++) {
                for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                    swap(arr, j, j + 1);
                }
            }
        }

        public static int[] copyArray(int[] arr) {
            int[] res = new int[arr.length];
            for (int i = 0; i < res.length; i++) {
                res[i] = arr[i];
            }
            return res;
        }

        public static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void printArrayByHeap(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printArrayByBFPRT(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9};
        // 5 3 2 3 1 1 2 2 1 1
        printArrayByHeap(getMinNumByHeap.getMinByHeap(arr, 10)); // 1 1 2 2 1 1 3 2 3 5
        printArrayByBFPRT(getMinNumByBFPRT.getMinByBFPRT(arr, 10)); // 1 3 1 2 2 1 3 2 1 5
        int[] arr2 = {3, 2, 1, 5, 4, 7};
        printArrayByHeap(getMinNumByHeap.getMinByHeap(arr2, 3)); // 1 2 3
        printArrayByBFPRT(getMinNumByBFPRT.getMinByBFPRT(arr2, 3)); // 2 1 3
    }
}
