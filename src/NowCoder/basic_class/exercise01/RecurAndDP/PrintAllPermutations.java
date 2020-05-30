package NowCoder.basic_class.exercise01.RecurAndDP;

import java.util.HashSet;

public class PrintAllPermutations {
    public static void printAllPermutations(String str) {
        if (str == null) {
            return;
        }

        char[] s = str.toCharArray();

        process(s, 0, s.length - 1);
    }

    public static void process(char[] s, int l, int r){
        // base case
        if (l == r){
            System.out.println(String.valueOf(s));
            return;
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

    public static void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static void main(String[] args) {
        printAllPermutations("abc");
        System.out.println("*****************");
        printAllPermutations("acc");
    }
}
