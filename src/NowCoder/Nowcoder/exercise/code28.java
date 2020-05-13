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

        HashMap<Integer, Integer> ntMap = new HashMap<>();
        int halfLen = arr.length / 2;

        for (int i = 0; i < arr.length; i++) {
            if (!ntMap.containsKey(arr[i])){
                ntMap.put(arr[i], 1);
            }else {
                int times = ntMap.get(arr[i]) + 1;
                if (times > halfLen){
                    return arr[i];
                }else {
                    ntMap.put(arr[i], times);
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(getMoreThanHalfNum(arr)); // 2
    }
}
