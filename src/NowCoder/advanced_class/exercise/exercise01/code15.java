package NowCoder.advanced_class.exercise.exercise01;

import java.util.HashMap;

public class code15 {
    public static int maxLength(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            
            if (map.containsKey(sum - aim)){
                int left = map.get(sum - aim) + 1;
                res = Math.max(res, i - left + 1);
            }

            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr2 = {7, 3, 2, 1, 1, 7, 7, 7};
        System.out.println(maxLength(arr2, 7)); // 4
    }
}
