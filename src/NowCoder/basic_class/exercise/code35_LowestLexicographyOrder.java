package NowCoder.basic_class.exercise;

/*
	字典序最小的组合方法：
		排序策略：str1.str2 <= str2.str1 --> str1在前
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class code35_LowestLexicographyOrder {
    public static String LowestLexicographyOrder(String[] strs){
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

    public static class myComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }

    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(LowestLexicographyOrder(strs1)); // bwjibwjibwjijp

        String[] strs2 = { "ba", "b" };
        System.out.println(LowestLexicographyOrder(strs2)); // bab
    }
}
