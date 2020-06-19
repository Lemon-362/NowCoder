package NowCoder.Nowcoder.exercise;

import java.util.Arrays;
import java.util.Comparator;

public class code32 {
    public static String PrintMinNumber(int[] arr){
        if (arr == null || arr.length < 1){
            return null;
        }

        String[] s = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            s[i] = String.valueOf(arr[i]);
        }

        Arrays.sort(s, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });

        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            stringBuffer.append(s[i]);
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};
        System.out.println(PrintMinNumber(numbers)); // 3033459
    }
}
