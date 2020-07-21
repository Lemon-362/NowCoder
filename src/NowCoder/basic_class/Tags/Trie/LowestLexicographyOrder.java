package NowCoder.basic_class.Tags.Trie;

import java.util.Arrays;
import java.util.Comparator;

public class LowestLexicographyOrder {
    public static String method(String[] strs){
        if (strs == null){
            return null;
        }
        Arrays.sort(strs, new myComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }

    public static class myComparator implements Comparator<String> {
        public int compare(String o1, String o2){
            return (o1+o2).compareTo(o2+o1);
        }
    }

    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(method(strs1)); // bwjibwjibwjijp

        String[] strs2 = { "ba", "b" };
        System.out.println(method(strs2)); // bab
    }
}
