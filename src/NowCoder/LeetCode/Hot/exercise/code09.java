package NowCoder.LeetCode.Hot.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class code09 {
    public static List<String> list = new ArrayList<>();

    public static HashMap<String, String> map = new HashMap<>();

    public static List<String> letterCombinations(String s) {
        if (s == null || s.length() < 1) {
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

    public static void process(String letters, int index, String res){
        // base case
        if (index == letters.length()){
            list.add(res);
            return;
        }

        // 2
        char num = letters.charAt(index);
        // 2: abc
        String letter = map.get(String.valueOf(num));

        for (int i = 0; i < letter.length(); i++) {
            // a
            String value = letter.substring(i, i + 1);

            process(letters, index + 1, res + value);
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
