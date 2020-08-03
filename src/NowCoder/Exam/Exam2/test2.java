package NowCoder.Exam.Exam2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class test2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();

        str = str.substring(1, str.length() - 1);

        String[] s = str.split(",");

        String s1 = "";

        for (int i = 0; i < s.length; i++) {
            s1 += s[i];
        }

        printAllPermutations(s1);

        int count = 0;

        for (String ss : res) {
            if (isValid(ss.toCharArray())) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static void printAllPermutations(String str) {
        if (str == null || str.length() < 1) {
            return;
        }

        char[] s = str.toCharArray();
        process(s, 0, s.length - 1);

    }

    public static ArrayList<String> res = new ArrayList<>();

    public static boolean isValid(char[] s) {
        String str = String.valueOf(s);
        int num = Integer.parseInt(str);

        return num % 7 == 0;
    }

    public static void process(char[] s, int l, int r) {
        // base case
        if (l == r) {
            res.add(String.valueOf(s));
            return;
        }

        for (int i = l; i <= r; i++) {
            swap(s, l, i);

            process(s, l + 1, r);
            swap(s, l, i);
        }
    }

    public static void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

}
