package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.HashMap;

/*
560. 和为k的子数组
    给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

 */
public class code19_SubArraySum {

    /*
    TODO 子数组问题:
        类似advance_code15_LongestSumSubArrayLength
        但是这里求的是子数组的个数, 而不是长度
        所以Map的值不应该存索引位置, 而是存以当前位置结尾的和的个数

    TODO 等价变换:
            求以i位置结尾的和为aim的子数组个数
        ==> 求i位置之前的, 始终以0位置开头, 和为sum-aim的子数组个数
         等价变换的前提是:
            以i位置结尾之前有一个子数组和为aim, 而 0~i 总和为sum
            所以在i位置之前, 一定存在一个和为 sum-aim 的子数组, 统计这个子数组个数即可
     */
    public static int subArraySum1(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) {
            return 0;
        }

        int res = 0;
        // key: 以当前位置结尾的sum value: 以当前位置结尾的sum的子数组的个数
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum - aim)) {
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

    /*
    暴力法: TODO 以每个位置开头的和为aim的子数组
            类似于 进阶_code08_AllLessNumSubArray的暴力法

     */
    public static int subArraySum2(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) {
            return 0;
        }

        int res = 0;

        for (int i = 0; i < arr.length; i++) { // 以i位置开头
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
