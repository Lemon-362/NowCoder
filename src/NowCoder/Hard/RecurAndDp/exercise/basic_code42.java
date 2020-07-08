package NowCoder.Hard.RecurAndDp.exercise;

import java.util.HashSet;

public class basic_code42 {
    public static void process(char[] s, int l, int r) {
        // base case
        if (l == r){
            System.out.println(String.valueOf(s));
            return;
        }

        HashSet<Character> set = new HashSet<>();

        for (int i = l; i <= r; i++) {
            if (!set.contains(s[i])){
                set.add(s[i]);

                swap(s, i, l);

                process(s, l + 1, r);

                swap(s, i, l);
            }
        }
    }

    public static void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void printAllPermutation(String str){
        if (str == null){
            return;
        }

        char[] s = str.toCharArray();


        process(s, 0, str.length() - 1);
    }

    public static void main(String[] args) {
        printAllPermutation("abc");
//        abc
//        acb
//        bac
//        bca
//        cba
//        cab
        System.out.println("================");

        printAllPermutation("acc");
//        acc
//        cac
//        cca
    }
}
