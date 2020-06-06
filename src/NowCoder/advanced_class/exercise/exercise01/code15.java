package NowCoder.advanced_class.exercise.exercise01;

import java.util.HashMap;

public class code15 {
    public static int maxLength(int[] arr, int aim){
        if (arr == null || arr.length < 1) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int len = 0;

        map.put(0, -1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum - aim)){
                int right = i;
                int left = map.get(sum - aim) + 1;
                len = Math.max(len, right - left + 1);
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr2 = {7, 3, 2, 1, 1, 7, 7, 7};
        System.out.println(maxLength(arr2, 7)); // 4
    }
}
