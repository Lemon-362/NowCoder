package NowCoder.Nowcoder;

import java.util.Stack;

/*
    翻转单词顺序：给定一个字符串，逐个翻转字符串中的每个单词。
        输入: "the sky is blue"
        输出: "blue is sky the"
 */
public class code44_ReverseSentence {
    public static String ReverseSentence(String str) {
        String str1 = str.trim();
        if (str1.equals("")){
            return str; // 如果删除前后空格后仍为空，则输出原字符串（注意，是原字符串，而不是删除空格后的字符串）
        }
        String[] s = str1.split(" ");
        StringBuffer res = new StringBuffer();
        // 从后往前遍历String数组
        for(int i = s.length - 1; i >= 0; i--){
            // 如果String不为空，则添加进去
            if(s[i].length() != 0){
                res.append(s[i]).append(" ");
            }
        }
        // 删除结尾的空格
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    public static void main(String[] args) {
        String str = "student. a am I";
        String res = ReverseSentence(str);
        System.out.println(res);
    }
}
