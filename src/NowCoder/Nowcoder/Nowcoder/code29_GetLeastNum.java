package NowCoder.Nowcoder.Nowcoder;

import java.util.ArrayList;
import java.util.Arrays;

/*
    最小的k个数：
        输入n个整数，找出其中最小的K个数。
        例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class code29_GetLeastNum {
    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        // TODO 越界时return 空list，而不是null
        if (k > input.length) {
            return list;
        }
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    public static class getMinNumByHeap {
        // Heap
        public static ArrayList<Integer> getMinByHeap(int[] arr, int k) {
            ArrayList<Integer> list = new ArrayList<>();

            if (arr == null || arr.length < 1 || k > arr.length) {
                return list;
            }

            int[] heap = new int[k];

            for (int i = 0; i < k; i++) {
                heapInsert(heap, arr[i], i);
            }

            for (int i = k; i < arr.length; i++) {
                if (arr[i] < heap[0]) {
                    heap[0] = arr[i];
                    heapify(heap, 0, k);
                }
            }

            for (int i = 0; i < heap.length; i++) {
                list.add(heap[i]);
            }

            return list;
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

        public static ArrayList<Integer> getMinByBFPRT(int[] arr, int k) {
            ArrayList<Integer> list = new ArrayList<>();

            if (arr == null || arr.length < 1 || k > arr.length) {
                return list;
            }

            int kthNum = getKthMinNum(arr, k);

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < kthNum) {
                    list.add(arr[i]);
                }
            }

            while (list.size() < k) {
                list.add(kthNum);
            }

            return list;
        }

        public static int getKthMinNum(int[] arr, int k) {
            int[] copyArr = copyArray(arr);
            return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
        }

        public static int bfprt(int[] arr, int l, int r, int k) {
            // base case
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
            for (int i = l + 1; i < r; i++) {
                for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                    swap(arr, j, j + 1);
                }
            }
        }

        public static int[] copyArray(int[] arr) {
            int[] res = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
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

    public static void main(String[] args) {
        int[] arr = {6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9};
//        ArrayList<Integer> list = GetLeastNumbers_Solution(arr, 4);

        ArrayList<Integer> list = getMinNumByHeap.getMinByHeap(arr, 10);
        for (Integer num : list) {
            System.out.print(num + " ");
        }

        System.out.println();

        ArrayList<Integer> list1 = getMinNumByBFPRT.getMinByBFPRT(arr, 10);
        for (Integer num : list1) {
            System.out.print(num + " ");
        }
    }
}
