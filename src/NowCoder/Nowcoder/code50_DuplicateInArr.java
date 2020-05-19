package NowCoder.Nowcoder;

import java.util.HashSet;

public class code50_DuplicateInArr {
    public static boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length < 1) {
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
}
