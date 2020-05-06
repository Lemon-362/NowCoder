package NowCoder.basic_class.basic_class.basic_class_01;

import java.util.Arrays;

/*
    归并排序：O(NlogN)
        先拆后合，从中间往两边递归，然后合并的过程中外排方式进行排序
 */

public class Code_05_MergeSort {

	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		mergeSort(arr, 0, arr.length - 1);
	}

	public static void mergeSort(int[] arr, int l, int r) {
		// 递归结束条件
		if (l == r) { // 只有一个数时不能拆分了，开始合并
			return;
		}
		int mid = l + ((r - l) >> 1);
		// 向左递归，拆分
		mergeSort(arr, l, mid);
		// 向右递归，拆分
		mergeSort(arr, mid + 1, r);
		// 合并，merge之前左右两边数组一定是有序的
		merge(arr, l, mid, r);
	}

	public static void merge(int[] arr, int l, int mid, int r) {
		// 辅助数组，用于存放排好序的数
		int[] help = new int[r - l + 1];
		// 辅助数组的指针
		int i = 0;
		// 合并时左边数组的指针
		int p1 = l;
		// 合并时右边数组的指针
		int p2 = mid + 1;

		while (p1 <= mid && p2 <= r) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 退出时必有一边先遍历完
		while (p1 <= mid) { // p2遍历完
			help[i++] = arr[p1++];
		}
		while (p2 <= r) { // p1遍历完
			help[i++] = arr[p2++];
		}

		// 拷贝至原数组
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i]; // 因为是对L--R上的数组进行合并，所以原数组索引从L开始
		}
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
			mergeSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		mergeSort(arr);
		printArray(arr);

	}

}
