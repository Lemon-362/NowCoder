package NowCoder.advanced_class.code.class_4;

import java.util.HashMap;

/*
	异或和为0的最多子数组：
		给定一个数组arr，可以分成很多不相容的子数组
		求分出的子数组中，异或和为0的子数组最多是多少？

	TODO 子数组问题：看到子数组，就要想到以每个位置结尾的结果，答案一定在其中
 */
public class Code_06_Most_EOR {

	public static int mostEOR(int[] arr) {
		int ans = 0;
		int xor = 0;
		int[] dp = new int[arr.length];
		// map存 异或和 - 最晚出现的位置
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		for (int i = 0; i < arr.length; i++) {
			xor ^= arr[i];

			// 情况一：包含当前位置的子数组异或和为0
			// 首先如果map中存在这个异或和，那么先计算再更新
			if (map.containsKey(xor)) { // 在 0 - i中找最晚出现xor的位置
				// 因为更新操作是放在最后的，所以此时map中这个异或和key对应的位置是之前出现的位置，也就是要找的位置
				int pre = map.get(xor); // k-1位置
				// TODO
				dp[i] = pre == -1 ? 1 : (dp[pre] + 1); // 如果k-1位置是-1处，说明第一个元素就是0
			}

			// 情况二：包含当前位置的子数组异或和不为0
			// 只有在i>=1时，才会有可能是dp[i-1]
			if (i > 0) {
				dp[i] = Math.max(dp[i - 1], dp[i]);
			}

			// TODO 和累加和等于aim的区别：因为是找最晚出现的位置，所以不管map中有没有这个异或和，我都存进去，改变他的value出现位置
			map.put(xor, i);

			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}

	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] eors = new int[arr.length];
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
			eors[i] = eor;
		}
		int[] mosts = new int[arr.length];
		mosts[0] = arr[0] == 0 ? 1 : 0;
		for (int i = 1; i < arr.length; i++) {
			mosts[i] = eors[i] == 0 ? 1 : 0;
			for (int j = 0; j < i; j++) {
				if ((eors[i] ^ eors[j]) == 0) {
					mosts[i] = Math.max(mosts[i], mosts[j] + 1);
				}
			}
			mosts[i] = Math.max(mosts[i], mosts[i - 1]);
		}
		return mosts[mosts.length - 1];
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
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
		int testTime = 50000;
		int maxSize = 300;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int res = mostEOR(arr);
			int comp = comparator(arr);
			if (res != comp) {
				succeed = false;
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
