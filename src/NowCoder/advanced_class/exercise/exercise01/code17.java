package NowCoder.advanced_class.exercise.exercise01;

import java.util.HashMap;

public class code17 {
    public static int maxLength(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }

        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i] == 1 ? 1 : -1;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int len = 0;
        int sum = 0;

        map.put(0, -1);

        for (int i = 0; i < res.length; i++) {
            sum += res[i];

            if (map.containsKey(sum - 0)){
                int right = i;
                int left = map.get(sum - 0) + 1;
                len = Math.max(left, right - left + 1);
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0};
        System.out.println(maxLength(arr)); // 10
    }
}
