package NowCoder.LeetCode.Tags.Hot100.Array;

import java.util.HashMap;
import java.util.List;

/*
1. 两数之和:
    给定一个整数数组 arr 和一个目标值 target
    请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 */
public class code06_TwoSum {

    public static int[] twoSum(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(aim - arr[i])) {
                res[0] = i;
                res[1] = map.get(aim - arr[i]);
                break;
            }

            map.put(arr[i], i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 11, 7, 15, 2, 5, 7, 4};

        int[] sum = twoSum(arr, 9);
        for (int index : sum) {
            System.out.println(index); // 2 0
        }

    }
}
