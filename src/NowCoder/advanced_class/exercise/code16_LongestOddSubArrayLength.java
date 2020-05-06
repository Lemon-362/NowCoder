package NowCoder.advanced_class.exercise;

import java.util.HashMap;

/*
累加和等于num的最长子数组的变形1：
    全为正数的数组，求子数组中奇偶个数相等时的最长子数组
    TODO 全为正数，奇偶个数相同
    解法：
        奇数：1，偶数：-1，求累加和等于0的最长子数组
 */
public class code16_LongestOddSubArrayLength {
    public static int maxLength(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }

        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0){
                newArr[i] = -1;
            }else {
                newArr[i] = 1;
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int len = 0;
        int max = 0;
        // TODO 一定要加！
        map.put(0, -1);

        for (int i = 0; i < newArr.length; i++) {
            sum += newArr[i];

            if (map.containsKey(sum - 0)){
                len = i - map.get(sum - 0);
                max = Math.max(len ,max);
            }

            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 6, 7, 5};
        System.out.println(maxLength(arr)); // 6
    }
}
