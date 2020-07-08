package NowCoder.Hard.exercise;

import java.util.HashSet;

public class No_code27 {
    public static void printAllPermutations(String str) {
        if (str == null || str.length() < 1) {
            return;
        }

        char[] s = str.toCharArray();

        process1(s, 0);
    }

    public static void process1(char[] s, int index){
        // base case
        if (index == s.length){
            System.out.println(String.valueOf(s));
            return;
        }

        HashSet<Character> set = new HashSet<>();

        for (int i = index; i < s.length; i++) {
            if (!set.contains(s[i])){
                set.add(s[i]);

                swap(s, i, index);

                process1(s, index + 1);

                swap(s, i, index);
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

        process2(s, 0, " ");
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
