package NowCoder.Nowcoder.Tags.String;

import java.util.HashSet;

public class code27 {
    public static void printAllPermutations(String str) {
        if (str == null || str.length() < 1) {
            return;
        }

        process(str.toCharArray(), 0, str.length() - 1);
    }

    public static void process(char[] str, int l, int r){
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

                process(str, l + 1, r);

                swap(str, i, l);
            }
        }
    }

    public static void swap(char[] str, int i, int j){
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    // 字符串的子序列
    public static void printAllSubSequence(String str) {
        if (str == null || str.length() < 1) {
            return;
        }

        process(str.toCharArray(), 0, "");
    }

    public static void process(char[] str, int index, String res){
        if (index == str.length){
            System.out.println(res);
            return;
        }

        process(str, index + 1, res);
        process(str, index + 1, res + str[index]);
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
