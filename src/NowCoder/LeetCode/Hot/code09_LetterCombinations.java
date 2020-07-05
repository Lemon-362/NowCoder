package NowCoder.LeetCode.Hot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 电话号码的字母组合：
 *  给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合
 *  给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母
 * 例如：
 * 输入"23"，输出"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
 */
public class code09_LetterCombinations {
    /*
        暴力递归：
            只考虑当前情况，剩下的交给递归处理
     */
    public static List<String> list = new ArrayList<>();

    public static HashMap<String, String> map = new HashMap<>();

    public static List<String> letterCombinations(String s){
        if (s == null || s.length() < 1){
            return list;
        }

        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");

        process(s, 0, "");

        return list;
    }

    // 递归：从index开始的letters
    public static void process(String letters, int index, String res){
        // base case
        if (index == letters.length()){
            list.add(res);
            return;
        }

        // 截取letters的第一个数字字符：2
        char num = letters.charAt(index);
        // 获取数字字符对应的字母表：2对应的"abc"
        String letter = map.get(String.valueOf(num));
        // 遍历字母表，拼接
        for (int i = 0; i < letter.length(); i++) {
            // 截取字母表的每一个字母：abc的第一个字符"a"
            String value = letter.substring(i, i + 1);
            // 以当前截取的字母进行拼接，并向下递归：res + "a"
            process(letters, index + 1,res + value);
        }
    }

    public static void main(String[] args) {
        String s = "23";

        List<String> list = letterCombinations(s);
        for (String s1 : list) {
            System.out.println(s1);
        }
    }
}
