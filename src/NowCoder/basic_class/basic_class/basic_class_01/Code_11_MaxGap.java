package NowCoder.basic_class.basic_class.basic_class_01;

import java.util.Arrays;

public class Code_11_MaxGap {

	public static int maxGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}

		int len = nums.length;
		int min = Integer.MAX_VALUE; // 初始化为系统最大
		int max = Integer.MIN_VALUE;// 初始化为系统最小
		// 找全局最小和全局最大
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		// 如果min==max，说明这个数组都是一个数，最大差值maxGap就为0
		if (min == max) {
			return 0;
		}
		// 准备桶，每个桶内存储三个东西：mins，maxs，hasNum
		// 一共有N+1个桶，所以三个数组长度为len+1
		// mins：这个桶所表示的范围内的最小值
		// maxs：这个桶所表示的范围内的最大值
		// hasNum：表示这个桶内是否有值，有为true
		// 在这里，hasNum，maxs，mins是数组，所以索引对应的三个数组位置上的数就表示一个桶
		boolean[] hasNum = new boolean[len + 1];
		int[] maxs = new int[len + 1];
		int[] mins = new int[len + 1];
		int bid = 0;
		// 重新遍历整个数组，将每个数放在对应的桶中
		for (int i = 0; i < len; i++) {
			// 将[min, max]区域（数组）划分成N+1个桶
			// 确定当前数属于几号桶
			bid = bucket(nums[i], len, min, max); // 这里bid是索引
			// 一开始是没有值的，所以hasNum要放在最后更新
			// 将当前值和这个桶（索引对应的三个数组的位置）比较
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		int res = 0;
		int lastMax = maxs[0]; // 0号桶的最大值
		int i = 1;
		// 最大差值一定来自 当前个桶的min - 前一个最近桶的max
		// TODO 遍历len+1个桶，注意是遍历到len <=
		for (; i <= len; i++) { // 从1号桶开始
			if (hasNum[i]) { // 如果当前桶有值，lastMax才会变
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i]; // 前一个桶的最大值：变成当前桶的最大值，然后i++到下一个桶
			}
		}
		return res;
	}

	public static int bucket(long num, long len, long min, long max) {
		// 一个桶的长度 = (max - min) / len
		// 该数应该在几号桶
		return (int) ((num - min) * len / (max - min));
	}

	// for test
	public static int comparator(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		Arrays.sort(nums);
		int gap = Integer.MIN_VALUE;
		for (int i = 1; i < nums.length; i++) {
			gap = Math.max(nums[i] - nums[i - 1], gap);
		}
		return gap;
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
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (maxGap(arr1) != comparator(arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
