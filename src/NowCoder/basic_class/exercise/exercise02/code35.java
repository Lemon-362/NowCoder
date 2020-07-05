package NowCoder.basic_class.exercise.exercise02;

import java.util.Arrays;
import java.util.Comparator;

public class code35 {
    public static String LowestLexicographyOrder(String[] str) {
        if (str == null || str.length < 1) {
            return null;
        }

        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs1 = {"jibw", "ji", "jp", "bw", "jibw"};
        System.out.println(LowestLexicographyOrder(strs1)); // bwjibwjibwjijp

        String[] strs2 = {"ba", "b"};
        System.out.println(LowestLexicographyOrder(strs2)); // bab
    }
}
