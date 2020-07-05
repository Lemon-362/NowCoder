package NowCoder.LeetCode.Hot.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class code08 {
    public static List<List<Integer>> threeSum(int[] arr) {
        if (arr == null || arr.length < 3) {
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            if (num > 0){
                break;
            }

            if (i > 0 && arr[i] == arr[i - 1]){
                continue;
            }

            int L = i + 1;
            int R = arr.length - 1;
            while (L < R){
                int sum = arr[L] + arr[R];

                if (sum < -num){
                    L++;
                }else if (sum > -num){
                    R--;
                }else {
                    List<Integer> list = new ArrayList<>();
                    list.add(arr[i]);
                    list.add(arr[L]);
                    list.add(arr[R]);
                    res.add(list);

                    while (L < R && arr[L] == arr[L + 1]){
                        L++;
                    }
                    while (L < R && arr[R] == arr[R - 1]){
                        R--;
                    }

                    L++;
                    R--;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> lists = threeSum(arr);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        // -1 -1 2
        // -1 0 1
    }
}
