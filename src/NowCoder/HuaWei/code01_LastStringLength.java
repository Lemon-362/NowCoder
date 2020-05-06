package NowCoder.HuaWei;

import java.util.*;

/*
    计算字符串最后一个单词的长度，单词以空格隔开。
        输入 hello world
        输出 5
 */
public class code01_LastStringLength {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] s = str.split(" ");
        int len = s[s.length - 1].length();
        System.out.println(len);
    }
}
