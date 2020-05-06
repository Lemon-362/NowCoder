package NowCoder.basic_class.basic_class.class_08;

import java.util.ArrayList;
import java.util.List;

/*
    打印字符串全部子序列:
        对于每个字符,都有两种选择(要或者不要)
        递归的时候也就有两种递归
 */
public class Code_03_Print_All_Subsquences {

    public static void printAllSubsquence(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0);
    }

    public static void process(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
            return;
        }
        process(chs, i + 1);
        char tmp = chs[i];
        chs[i] = 0;
        process(chs, i + 1);
        chs[i] = tmp;
    }

    public static void function(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, new ArrayList<Character>());
    }

    public static void process(char[] chs, int i, List<Character> res) {
        if (i == chs.length) {
            printList(res);
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(chs[i]);
        process(chs, i + 1, resKeep);
        List<Character> resNoInclude = copyList(res);
        process(chs, i + 1, resNoInclude);
    }

    public static void printList(List<Character> res) {
        // ...;
    }

    public static List<Character> copyList(List<Character> list) {
        return null;
    }

    // res 上级的字符串
    public static void printAllSub(char[] str, int i, String res) { // 输入必须是char数组
        if (i == str.length) {
            System.out.println(res);
            return;
        }
        printAllSub(str, i + 1, res);
        printAllSub(str, i + 1, res + str[i]);
    }

    public static void main(String[] args) {
        String test = "abc";
//        printAllSubsquence(test);
        printAllSub(test.toCharArray(), 0, "");
    }

}
