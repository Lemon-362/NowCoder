package NowCoder.advanced_class.exercise.advance;

import java.util.HashMap;

/*
	累加和等于num的最长子数组：TODO 可正可负可0，累加和等于aim
		给定一个数组arr，和一个整数num，求在arr中，累加和等于num的最长子数组的长度
	例：
	arr = [7, 3, 2, 1, 1, 7, 7, 7]	num = 7
	输出：[3, 2, 1, 1]	len = 4

	TODO 子数组问题：看到子数组，就要想到以每个位置结尾的结果，答案一定在其中
	TODO HashMap应用 —— 在数组中找最早出现一个值的位置，也就是如果map中已经存在则不更新
 */
public class code15_LongestSumSubArrayLength {
    public static int maxLength(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int len = 0;
        int sum = 0;

        map.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum - aim)){
                len = Math.max(len, i - (map.get(sum - aim) + 1) + 1);
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr2 = {7, 3, 2, 1, 1, 7, 7, 7};
        System.out.println(maxLength(arr2, 7)); // 4
    }
}
