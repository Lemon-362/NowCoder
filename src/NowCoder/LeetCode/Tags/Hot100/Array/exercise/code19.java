package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

import java.util.HashMap;

/*
560. 和为k的子数组
    给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

 */
public class code19 {

    public static int subArraySum2(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum - aim)){
                res += map.get(sum - aim);
            }

            if (!map.containsKey(sum)){
                map.put(sum, 1);
            }else {
                map.put(sum, map.get(sum) + 1);
            }
        }

        return res;
    }

    public static int subArraySum1(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == aim){
                    res++;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] arr2 = {1, 1, 1};

        System.out.println(subArraySum1(arr1, 0)); // 55
        System.out.println(subArraySum2(arr1, 0)); // 55
        System.out.println(subArraySum1(arr2, 2)); // 2
        System.out.println(subArraySum2(arr2, 2)); // 2

    }
}
