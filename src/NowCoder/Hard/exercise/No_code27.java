package NowCoder.Hard.exercise;

import java.util.HashSet;

public class No_code27 {
    public static void printAllPermutations(String str) {
        if (str == null || str.length() < 1) {
            return;
        }

        char[] s = str.toCharArray();

        process1(s, 0, s.length - 1);
    }

    public static void process1(char[] str, int l, int r){
        // base case
        if (l == r){
            System.out.println(String.valueOf(str));
            return;
        }

        HashSet<Character> set = new HashSet<>();

        for (int i = l; i <= r; i++) {
            if (!set.contains(str[i])){
                set.add(str[i]);

                swap(str, i, l);

                process1(str, l + 1, r);

                swap(str, i, l);
            }
        }
    }

    public static void swap(char[] s, int i, int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    // 字符串的子序列
    public static void printAllSubSequence(String str) {
        if (str == null || str.length() < 1) {
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

        process2(s, index + 1, res);

        process2(s, index + 1, res + s[index]);
    }

    public static void main(String[] args) {
        String s1 = "abc";
        printAllPermutations(s1);
//        abc
//        acb
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

        System.out.println("============");
        printAllSubSequence(s1); // null c b bc a ac ab abc
    }
}
