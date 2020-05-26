package NowCoder.Nowcoder.exercise;

import java.util.HashSet;

public class code50 {
    public static boolean duplicate(int[] numbers, int length, int[] duplication){
        if (numbers == null || numbers.length < 1){
            return false;
        }

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < length; i++) {
            if (set.contains(numbers[i])){
                duplication[0] = numbers[i];
                return true;
            }else {
                set.add(numbers[i]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 2, 5, 3};
        int[] res = new int[1];

        System.out.println(duplicate(arr, arr.length, res)); // true

        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]); // 2
        }
    }
}
