package NowCoder.Nowcoder;

import java.util.*;

/*
    字符串的全排列:
    TODO 递归思想：对于结果长度和原始长度相同的题目，就以每个位置开头，将剩下的交给递归处理
 */
public class code27_Permutation {
    public static void printAllPermutations1(String str) {
        char[] chs = str.toCharArray();
        process1(chs, 0);
    }

    public static void process1(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
        }
        for (int j = i; j < chs.length; j++) {
            swap(chs, i, j);
            process1(chs, i + 1);
            swap(chs, i, j);
        }
    }

    public static void printAllPermutations2(String str) {
        char[] chs = str.toCharArray();
        process2(chs, 0);
    }

    public static void process2(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
        }
        HashSet<Character> set = new HashSet<>();
        for (int j = i; j < chs.length; j++) {
            if (!set.contains(chs[j])) {
                set.add(chs[j]);
                swap(chs, i, j);
                process2(chs, i + 1);
                swap(chs, i, j);
            }
        }
    }

    /*
    1. 以每个位置作为头部，剩下的交给递归处理
    2. 以每个位置作为头部：将每个位置都和开头交换，然后递归
    3. 递归结束回溯时，要再交换一次还原原始字符串，再进入下一个循环，交换下一个位置
    */
    public static void printAllPermutations(String str){
        if (str == null || str.length() < 1){
            return;
        }

        char[] s = str.toCharArray();
        process(s, 0, s.length - 1);

    }

    public static ArrayList<String> res = new ArrayList<>();

    // 从l到r上，将每个位置和开头l交换
    public static void process(char[] s, int l, int r){
        // base case
        if (l == r){
            System.out.println(String.valueOf(s));
//            res.add(String.valueOf(s));
            return;
        }

        // 用于存储已经交换过的元素，防止相同的元素交换
        HashSet<Character> set = new HashSet<>();

        for (int i = l; i <= r; i++) {
            // 如果要交换的元素不在hashSet中，则可以交换
            if (!set.contains(s[i])){
                set.add(s[i]);

                // 交换i位置和开始位置l
                swap(s, l, i);
                // 递归，将剩余位置交给递归处理
                process(s, l + 1, r);

                // 递归结束回溯时，要先把交换过的元素交换回来，保证原始字符串顺序
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
//        ArrayList<String> list = printAllPermutations(s1);
//        for (String s : list) {
//            System.out.println(s);
//        }
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

        System.out.println("============");
        printAllSubSequence(s1); // null a b c ab ac bc abc
    }

}
