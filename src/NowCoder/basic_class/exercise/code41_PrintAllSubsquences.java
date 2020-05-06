package NowCoder.basic_class.exercise;

/*
    打印字符串全部子序列:
        对于每个字符,都有两种选择(要或者不要)
        递归的时候也就有两种递归
 */

public class code41_PrintAllSubsquences {
    public static void printAllSub(char[] ch, int i, String str) {
        if (i == ch.length) {
            System.out.println(str);
            return;
        }
        printAllSub(ch, i + 1, str);
        printAllSub(ch, i + 1, str + ch[i]);
    }

    public static void main(String[] args) {
        String str = "abc";
        char[] ch = str.toCharArray();
        printAllSub(ch, 0, ""); // null a b c ab ac bc abc
    }
}
