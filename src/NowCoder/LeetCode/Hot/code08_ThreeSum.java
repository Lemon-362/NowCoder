package NowCoder.LeetCode.Hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 三数之和：
 *  给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 *  使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 思路：
 *  借鉴两数之和的思想
 *  以每个位置作为第一个元素，在后续数组中找两个数，使得三数之和等于0
 *  为了简化搜索，可以先将数组进行排序
 *  TODO 当第一个元素 > 0时，可以直接return，因为它后面的数一定也是 > 0的，不可能找到三数之和等于0的情况
 *
 */
public class code08_ThreeSum {
    /*
        1. 剩余数：使用HashMap存储
         以每个位置作为第一个数，在后续数组中找两数之和等于该位置的负数
         TODO 使用HashMap遍历，对于数组有重复元素时，会出现重复解
            对于两数之和不能去重
     */
    public static List<List<Integer>> threeSum(int[] arr){
        if (arr == null || arr.length < 3){
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(arr);

        // 以每个位置作为第一个数
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            if (num > 0){
                break;
            }

            // 去重
            if (i > 0 && arr[i] == arr[i - 1]){
                continue;
            }

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < arr.length; j++) {
                if (map.containsKey(-num - arr[j])){
                    List<Integer> list = new ArrayList<>();
                    list.add(num);
                    list.add(arr[j]);
                    list.add(-num - arr[j]);
                    res.add(list);
                }

                map.put(arr[j], j);
            }
        }

        return res;
    }

    /*
        2. 剩余数：双指针法 —— 去除重复解
        TODO 双指针可以保证两数之和去重，因为排过序，所以如果相同元素，前后指针直接跳过
     */
    public static List<List<Integer>> threeSum2(int[] arr){
        if (arr == null || arr.length < 3){
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(arr);

        // 以每个位置作为第一个数
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            if (num > 0) {
                break;
            }

            // TODO 去重
            if (i > 0 && arr[i] == arr[i - 1]){
                continue;
            }

            // 在后续数组中找两个数等于-num
            int L = i + 1;
            int R = arr.length - 1;
            while (L < R){
                int sum = arr[L] + arr[R];

                if (sum == -num){
                    List<Integer> list = new ArrayList<>();
                    list.add(num);
                    list.add(arr[L]);
                    list.add(arr[R]);
                    res.add(list);
                    // TODO 去重，因为已经排过序了，所以如果有重复元素，那么是在一起的，直接跳过
                    while (L < R && arr[L] == arr[L + 1]){
                        L++;
                    }
                    while (L < R && arr[R] == arr[R - 1]){
                        R--;
                    }
                    // 向内移动，继续找两数之和等于-num的元素
                    L++;
                    R--;
                }else if (sum > -num){ // 说明两数之和大了
                    R--;
                }else {
                    L++;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> lists = threeSum2(arr);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

    }
}
