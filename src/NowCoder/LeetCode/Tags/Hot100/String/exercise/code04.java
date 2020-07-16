package NowCoder.LeetCode.Tags.Hot100.String.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class code04 {
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

        process(s, 0, "");

        return list;
    }

    public static void process(String s, int index, String res){
        // base case
        if (index == s.length()){
            list.add(res);
            return;
        }

        String num = s.substring(index, index + 1);
        String letters = map.get(num);

        for (int i = 0; i < letters.length(); i++) {
            String letter = letters.substring(i, i + 1);

            process(s, index + 1, res + letter);
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
