package NowCoder.advanced_class.class_4;

import java.util.HashMap;

/*
累加和等于num的最长子数组的变形2：
    只含0和1和2的数组，求子数组中12个数相等时的最长子数组
    TODO 只含012，12个数相同
    解法：
        1：1，0：0，2：-1，求累加和等于0的最长子数组
 */
public class Code_05_Longest012SubArrayLength {
    public static int maxLength(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        // 改变数组元素
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2) {
                newArr[i] = -1;
            } else {
                newArr[i] = arr[i];
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int len = 0;
        int max = 0;
        int sum = 0;
        // TODO 一定要加！
        map.put(0, -1);

        for (int i = 0; i < newArr.length; i++) {
            sum += newArr[i];
            // aim = 0;
            if (map.containsKey(sum - 0)) {
                len = i - map.get(sum - 0);
                max = Math.max(max, len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 0, 1, 2, 2, 1, 0, 1, 2, 1};
        System.out.println(maxLength(arr)); // 9
    }
}
