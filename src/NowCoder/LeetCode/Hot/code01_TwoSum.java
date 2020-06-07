package NowCoder.LeetCode.Hot;

import java.util.HashMap;

/**
 * 两数之和：
 *  给定一个整数数组 arr 和一个目标值 target
 *  请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 *
 * 思路：
 *  一开始考虑的是双指针，但是要注意：数组是无序的，如果移动指针，不能确定是要加上还是减去这个值
 *      剑指Offer 42：输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S
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

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int[] res = twoSum(arr, 9);
        for (int re : res) {
            System.out.println(re);
        }
    }
}
