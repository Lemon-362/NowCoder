package NowCoder.advanced_class.exercise.exercise01;

import java.util.HashMap;

// 0,1,2
public class code18 {
    public static int maxLength(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }

        int[] copyArr = new int[arr.length];
        for (int i = 0; i < copyArr.length; i++) {
            copyArr[i] = arr[i] == 2 ? -1 : arr[i];
        }

        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;

        for (int i = 0; i < copyArr.length; i++) {
            sum += copyArr[i];

            if (map.containsKey(sum - 0)){
                int right = i;
                int left = map.get(sum - 0) + 1;
                len = Math.max(len, right - left + 1);
            }

            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 0, 1, 2, 2, 1, 0, 1, 2, 1};
        System.out.println(maxLength(arr)); // 9
    }
}
