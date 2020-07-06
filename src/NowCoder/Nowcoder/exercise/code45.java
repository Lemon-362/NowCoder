package NowCoder.Nowcoder.exercise;

import java.util.HashSet;

public class code45 {
    public static boolean isContinuous(int[] arr){
        if (arr == null || arr.length < 1){
            return false;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0){
                continue;
            }

            if (!set.contains(arr[i])){
                set.add(arr[i]);

                max = Math.max(max, arr[i]);
                min = Math.min(min, arr[i]);
            }else {
                return false;
            }
        }

        return max - min < 5;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {0, 0, 1, 2, 5};

        System.out.println(isContinuous(arr1)); // true
        System.out.println(isContinuous(arr2)); // true
    }
}
