package NowCoder.basic_class.basic_class.basic_class_01;

import java.util.Arrays;

/*
    堆排序：
        数组变成大根堆，数组头一定是数组中最大的
        堆顶与最后一个交换，然后Heapify
        堆大小-1，重复交换和后续
 */

public class Code_03_HeapSort {

	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 先将数组变为大根堆
		for (int i = 0; i < arr.length; i++) {
			heapInsert(arr, i);
		}
		// 已经形成大根堆，此时堆顶最大
		int size = arr.length;
		swap(arr, 0, --size); // 堆顶和最后一个交换（数组头和尾交换）
		// 交换后堆顶一定是变小，对堆顶用heapify
		while (size > 0) {
			// 因为是堆顶变小，所以对堆顶用heapify
			heapify(arr, 0, size);
			swap(arr, 0, --size);
		}
	}

	// heapInsert：加入一个新的数，形成大根堆
	public static void heapInsert(int[] arr, int index) {
		// index处值 > 其父节点值，交换，继续向上比较
		while (arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	// heapify：数组中一个值变小，再次形成大根堆
	public static void heapify(int[] arr, int index, int size) {
		// 左孩子
		int left = index * 2 + 1;
		while (left < size) {
			// TODO 在三者之间选最大的
			// 右孩子也在大根堆中，左孩子和右孩子中取较大的那个
			int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
			// 只有在右孩子也在大根堆中，且右孩子比左孩子大：largest才取右孩子，否则都去左孩子
			// 较大值和index值比较取较大的那个
			largest = arr[largest] > arr[index] ? largest : index;
			if (largest == index) { // 如果最大的就是头，就往下调整
				break;
			}
			swap(arr, largest, index);
			// 交换后继续往下比较
			index = largest;
			left = index * 2 + 1;
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			heapSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		heapSort(arr);
		printArray(arr);
	}

}
