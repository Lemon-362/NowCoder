package NowCoder.LeetCode.Tags.Hot100.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 电话号码的字母组合：
 *  给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合
 *  给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母
 * 例如：
 * 输入"23"，输出"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
 *
 */
public class code04_LetterCombinations {
    public static HashMap<String, String> map = new HashMap<>();

    public static List<String> list = new ArrayList<>();

    public static List<String> letterCombinations(String s){
        if (s == null){
            return null;
        }

        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");

        process(s.toCharArray(), 0, "");

        return list;
    }

    /*
        TODO 递归 + 回溯 + 多叉树：
            只考虑当前情况，剩下的交给递归处理
            实际上是一个多叉树，对于递归函数process，只考虑当前层如何处理，剩下的层进入递归即可

            1. base case：
                index == str.length时，说明遍历的数字字符串到头了，说明对应的字母组合好了
                那么就将当前组合好的res放入list中
            2. 首先截取当前位置index的数字字符，从map中获得其对应的字母字符串
            3. 遍历字母字符串的每一个字符，这些字符就代表了该层（index处数字）的所有可能
            4. 在for循环内，获取每一个字母字符，然后该层的一个选择分支就可以进入递归
               用当前的选择分支的字符去组合剩下层（剩下字母字符）的分支

        */
    public static void process(char[] str, int index, String res){
        // base case
        if (index == str.length){
            list.add(res);
            return;
        }

        char num = str[index];
        String letters = map.get(String.valueOf(num));

        for (int i = 0; i < letters.length(); i++) {
            String letter = letters.substring(i, i + 1);

            process(str, index + 1, res + letter);
        }
    }

    public static void main(String[] args) {
        String s = "23";

        List<String> list = letterCombinations(s);
        for (String s1 : list) {
            System.out.println(s1);
        }
//        ad
//        ae
//        af
//        bd
//        be
//        bf
//        cd
//        ce
//        cf
    }
}
