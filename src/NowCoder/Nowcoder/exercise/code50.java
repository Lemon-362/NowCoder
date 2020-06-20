package NowCoder.Nowcoder.exercise;

import java.util.HashSet;

public class code50 {
    public static boolean duplicate(int[] numbers, int length, int[] duplication){
        if (numbers == null || numbers.length < 1){
            return false;
        }

        for (int i = 0; i < length; i++) {
            while (i != numbers[i]){
                if (numbers[i] == numbers[numbers[i]]){
                    duplication[0] = numbers[i];
                    return true;
                }
                swap(numbers, i, numbers[i]);
            }
        }

        return false;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
