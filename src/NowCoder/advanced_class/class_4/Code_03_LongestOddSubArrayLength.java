package NowCoder.advanced_class.class_4;

import java.util.HashMap;

/*
累加和等于num的最长子数组的变形1：
    全为正数的数组，求子数组中奇偶个数相等时的最长子数组
    TODO 全为正数，奇偶个数相同
    解法：
        奇数：1，偶数：-1，求累加和等于0的最长子数组
 */
public class Code_03_LongestOddSubArrayLength {
    public static int maxLength(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        // 改变数组元素
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) { // 偶数：-1
                newArr[i] = -1;
            } else {
                newArr[i] = 1;
            }
        }

        // key:0-i的累加和sum, value:第一次出现sum的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = 0;
        int maxLen = 0;
        int sum = 0;
        // TODO 一定要加！
        map.put(0, -1);

        for (int i = 0; i < newArr.length; i++) {
            sum += newArr[i];
            // aim = 0;
            if (map.containsKey(sum - 0)) {
                len = i - map.get(sum - 0);
                maxLen = Math.max(maxLen, len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 6, 7, 5};
        System.out.println(maxLength(arr)); // 6
    }
}
