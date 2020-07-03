package NowCoder.LeetCode.Hot.exercise;

import java.util.HashMap;

public class code01 {
    public static int[] twoSum(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])){
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
        int[] res = twoSum(arr, 17);
        for (int re : res) {
            System.out.println(re); // 3 0
        }
    }
}
