package NowCoder.Nowcoder.exercise;

import java.util.ArrayList;
import java.util.HashSet;

public class code27 {
    public static ArrayList<String> res = new ArrayList<>();

    public static ArrayList<String> printAllPermutations(String str){
        if (str == null || str.length() < 1){
            return res;
        }

        char[] s = str.toCharArray();

        process(s, 0, s.length - 1);

        return res;
    }

    public static void process(char[] s, int l, int r){
        if (l == r){
            res.add(String.valueOf(s));
        }

        HashSet<Character> set = new HashSet<>();

        for (int i = l; i <= r; i++) {
            if (!set.contains(s[i])){
                set.add(s[i]);

                swap(s, l, i);

                process(s, l + 1, r);

                swap(s, l, i);
            }
        }
    }

    public static void swap(char[] s, int i, int j){
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    // 字符串的子序列
    public static void printAllSubSequence(String str){
        if (str == null || str.length() < 1){
            return;
        }

        char[] s = str.toCharArray();
        process2(s, 0, "");
    }

    public static void process2(char[] s, int index, String res){
        // base case
        if (index == s.length){
            System.out.println(res);
            return;
        }

        // 可能性1：对于当前字符，我不要
        process2(s, index + 1, res);

        // 可能性2：对于当前字符，我要
        process2(s, index + 1, res + s[index]);
    }

    public static void main(String[] args) {
        String s1 = "abc";
        printAllPermutations(s1);
//        abc
//        cb
//        bac
//        bca
//        cba
//        cab

        System.out.println("============");
        String s2 = "acc";
        printAllPermutations(s2);
//        acc
//        cac
//        cca
//
        System.out.println("============");
        printAllSubSequence(s1); // null a b c ab ac bc abc
    }
}
