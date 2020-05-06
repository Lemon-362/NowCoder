package NowCoder.advanced_class.class_4;

import java.util.HashMap;

/*
	累加和等于num的最长子数组：TODO 可正可负可0，累加和等于aim
		给定一个数组arr，和一个整数num，求在arr中，累加和等于num的最长子数组的长度
	例：
	arr = [7, 3, 2, 1, 1, 7, 7, 7]	num = 7
	输出：[3, 2, 1, 1]	len = 4

	TODO 子数组问题：看到子数组，就要想到以每个位置结尾的结果，答案一定在其中
 */
public class Code_02_LongestSumSubArrayLength {

	public static int maxLength(int[] arr, int aim) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// map key：sum，从0开始到i位置的累加和  value：第一次出现sum的索引位置
		// TODO 如果key在map中出现过了，则不更新map的value。如果没有出现，则存key-value
		HashMap<Integer, Integer> map = new HashMap<>();

		// TODO 重要的初始化步骤！！！
		map.put(0, -1); // important 防止是从0位置开始的子数组

		int len = 0;
		int sum = 0;
		// 遍历数组，求以每个位置结尾的和
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			// 先求len
			if (map.containsKey(sum - aim)) { // 看map中是否有sum-aim的key
				// 如果有，则 sum-aim对应的value+1 -- i当前位置，就是累加和等于aim的子数组
				len = Math.max(i - map.get(sum - aim), len);
			}
			// 再存key
			if (!map.containsKey(sum)) { // 如果map中没有该key则存，有则不更新
				map.put(sum, i);
			}
		}
		return len;
	}

	public static int[] generateArray(int size) {
		int[] result = new int[size];
		for (int i = 0; i != size; i++) {
			result[i] = (int) (Math.random() * 11) - 5;
		}
		return result;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = generateArray(20);
		printArray(arr);
		System.out.println(maxLength(arr, 10));

		int[] arr2 = {7, 3, 2, 1, 1, 7, 7, 7};
		System.out.println(maxLength(arr2, 7)); // 4

	}

}
