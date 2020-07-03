package NowCoder.LeetCode.Hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 两数之和：
 *  给定一个整数数组 arr 和一个目标值 target
 *  请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 *
 * 思路：
 *  一开始考虑的是双指针，但是要注意：数组是无序的，如果移动指针，不能确定是要加上还是减去这个值
 *      剑指Offer 42：输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S
 *  TODO 可以先对数组进行排序，再用双指针，如果要求找到所有结果，那么需要借助code08的去重
 *      但是如果先排序的话，返回的结果只能是元素值，不能是索引位置！！！
 *
 *  所以可以借鉴 进阶code15的思路：累加和等于num的最长子数组
 *  将求和问题转换成剩余数的问题
 *  每遍历一个数，就求出其还需要的数：target-arr[i]，使用HashMap存储当前的数
 *  如果map中有该数，正好可以凑成target，即为答案。
 */
public class code01_TwoSum {
    public static int[] twoSum(int[] arr, int target) {
        int[] res = new int[2];

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < arr.length; i++){
            if(map.containsKey(target - arr[i])){
                res[0] = i;
                res[1] = map.get(target - arr[i]);
                break;
            }

            map.put(arr[i], i);
        }

        return res;
    }

    // TODO 先排序的话，返回的结果只能是元素，不能是索引位置！！！
    public static int[] twoSum1(int[] arr, int target) {
        int[] res = new int[2];

        Arrays.sort(arr);

        int L = 0;
        int R = arr.length - 1;

        while (L < R){
            int sum = arr[L] + arr[R];

            if (sum == target){
                res[0] = arr[L];
                res[1] = arr[R];
                break;
            }else if (sum > target){
                R--;
            }else {
                L++;
            }
        }

        return res;
    }

    // TODO 找到所有结果，也只能返回元素值
    public static List<List<Integer>> twoSum2(int[] arr, int aim){
        List<List<Integer>> res = new ArrayList<>();

        int L = 0;
        int R = arr.length - 1;

        Arrays.sort(arr);

        while (L < R){
            int sum = arr[L] + arr[R];

            if (sum == aim){
                List<Integer> list = new ArrayList<>();
                list.add(arr[L]);
                list.add(arr[R]);
                res.add(list);
                // TODO 去重
                while (L < R && arr[L] == arr[L + 1]){
                    L++;
                }
                while (L < R && arr[R] == arr[R - 1]){
                    R--;
                }

                L++;
                R--;
            }else if (sum > aim){
                R--;
            }else {
                L++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 11, 7, 15, 2, 5, 7, 4};

        System.out.println("***********返回索引位置***********");

        int[] sum = twoSum(arr, 9);
        for (int index : sum) {
            System.out.println(index); // 2 0
        }

        System.out.println("***********返回元素值***********");

        int[] sum1 = twoSum1(arr, 9);
        for (int index : sum1) {
            System.out.println(index); // 2 7
        }

        System.out.println("***********返回所有结果的元素值***********");

        List<List<Integer>> lists = twoSum2(arr, 9);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        // 2 7
        // 4 5
    }
}
