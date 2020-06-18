package NowCoder.Nowcoder.exercise;

import java.util.HashMap;
import java.util.HashSet;

public class code28 {
    public static int getMoreThanHalfNum(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }else if (arr.length == 1){
            return arr[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])){
                int times = map.get(arr[i]) + 1;
                if (times >= arr.length / 2){
                    return arr[i];
                }

                map.put(arr[i], times);
            }else {
                map.put(arr[i], 1);
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(getMoreThanHalfNum(arr)); // 2
    }
}
