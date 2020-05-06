package NowCoder.advanced_class.class_2;

/*
	BFPRT算法：求无序数组中第k小/大的数
		找出无序数组中最小的k个数
 */
public class Code_01_BFPRT {

    // 堆：O(N*logK)
    public static int[] getMinKNumsByHeap(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        // 创建一个大根堆，这个大根堆就表示目前选出的k个最小的数
        // 堆顶是这k个最小的数中最大的那个数
        int[] kHeap = new int[k];
        for (int i = 0; i != k; i++) { // 往kHeap中添加元素，并形成大根堆
            heapInsert2(kHeap, arr[i], i);
        }
        // 往后遍历整个数组，看当前数是否比堆顶小，如果小，说明这个大根堆中的元素不是整个数组最小的k个数，将它与堆顶交换，然后调整大根堆
        // 遍历完后，这个大根堆就一定是整个数组中最小的k个数
        for (int i = k; i != arr.length; i++) {
            if (arr[i] < kHeap[0]) {
                kHeap[0] = arr[i]; // 堆顶换成当前数，堆顶变小，就要调整
                heapify2(kHeap, 0, k); // heapify始终调整长度为k的大根堆
            }
        }
        return kHeap;
    }

    // TODO HeapSort里面的代码
    public static void heapInsert2(int[] arr, int value, int index) {
        arr[index] = value;
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // TODO HeapSort里面的代码
    public static void heapify2(int[] arr, int index, int size) {
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

    public static void heapInsert(int[] arr, int value, int index) {
        arr[index] = value; // 从0位置开始到k-1位置添加元素
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (arr[parent] < arr[index]) {
                swap(arr, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;
        while (left < heapSize) {
            if (arr[left] > arr[index]) {
                largest = left;
            }
            if (right < heapSize && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest != index) {
                swap(arr, largest, index);
            } else {
                break;
            }
            index = largest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    // BFPRT：O(N)
    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int minKth = getMinKthByBFPRT(arr, k); // 第k小的数值
        int[] res = new int[k];
        int index = 0;
        // 遍历数组arr，先找小于minKth的数
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }
        // 因为有可能这个第k小的数值在arr数组中有重复的多个，所以res后面都加该数
//        for (; index != res.length; index++) {
//            res[index] = minKth;
//        }
        while (index < res.length) {
            res[index++] = minKth;
        }
        return res;
    }

    // 返回第k小的数
    public static int getMinKthByBFPRT(int[] arr, int K) {
        int[] copyArr = copyArray(arr);
        return bfprt(copyArr, 0, copyArr.length - 1, K - 1); // 第k小的数，也就是k-1位置
    }

    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i != res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /* BFPRT算法步骤：BFPRT求的是第k小的数
        1. 逻辑分组，每5个数为一组，不足5个也成一组
        2. 小组内排序
        3. 取每组的中位数，形成中位数数组。长度为N/5
        4. 求中位数数组的中位数，也就是求中位数数组的第len/2小的数，递归调用BFPRT，直到中位数数组长度<5，直接返回此时的中位数
        5. 用该中位数作为划分值，进行partition
     */
    public static int bfprt(int[] arr, int begin, int end, int i) {
        if (begin == end) {
            return arr[begin];
        }
        // 中位数数组中的中位数 作为划分值
        int pivot = medianOfMedians(arr, begin, end);
        // partition
        int[] pivotRange = partition(arr, begin, end, pivot);
        if (i >= pivotRange[0] && i <= pivotRange[1]) { // 如果i在等于区域
            return pivot;
        } else if (i < pivotRange[0]) { // 如果i在小于区域，小于区域递归
            return bfprt(arr, begin, pivotRange[0] - 1, i);
        } else { // 如果i在大于区域，大于区域递归
            return bfprt(arr, pivotRange[1] + 1, end, i);
        }
    }

    // 中位数数组中的中位数
    public static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1; // 数组元素个数
        int offset = num % 5 == 0 ? 0 : 1; // 看是否能被5刚好划分，如果不可以，最后不足5个的也成一组
        int[] mArr = new int[num / 5 + offset]; // 中位数数组
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5; // 每5个数成一组，所以每组的开头是 i * 5
            int endI = beginI + 4; // 每组的结尾是 开头 + 4
            // 对beginI-endI的一组进行排序得到该组的中位数
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI)); // 因为有可能最后一组不足5个，所以用min来判断
            // 每组中的中位数放在中位数数组中
        }
        // 此时获得了中位数数组
        // 递归调用，求中位数数组中的中位数
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < pivotValue) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > pivotValue) {
                swap(arr, cur, --big);
            } else {
                cur++;
            }
        }
        // 返回等于区域
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    // 获得中位数数组的中位数
    public static int getMedian(int[] arr, int begin, int end) {
        insertSort(arr, begin, end);
//        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2); // 上中位数，针对偶数个元素时
        return arr[mid];
    }

    public static void insertSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i < end; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9};
        // sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
        printArray(getMinKNumsByHeap(arr, 10)); // 5 3 2 3 1 1 2 2 1 1
        printArray(getMinKNumsByBFPRT(arr, 10)); // 1 3 1 2 2 1 3 2 1 5
        int[] arr2 = {3, 2, 1, 5, 4, 7};
        printArray(getMinKNumsByHeap(arr2, 3)); // 3 2 1 因为返回的是大根堆
        printArray(getMinKNumsByBFPRT(arr2, 3)); // 2 1 3
    }

}
