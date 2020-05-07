package NowCoder.advanced_class.exercise;

import java.util.HashMap;

/*
累加和等于num的最长子数组的变形2：
    只含0和1的数组，求子数组中01个数相等时的最长子数组
    TODO 只含01，01个数相同
    解法：
        1：1，0：-1，求累加和等于0的最长子数组
 */
public class code17_Longest01SubArrayLength {
    public static int maxLength(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr[i] == 0 ? -1 : 1;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int len =0;
        map.put(0, -1);

        for (int i = 0; i < res.length; i++) {
            sum += res[i];

            if (map.containsKey(sum - 0)){
                len = Math.max(len, i - (map.get(sum - 0) + 1) + 1);
            }

            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0};
        System.out.println(maxLength(arr)); // 10
    }
}
