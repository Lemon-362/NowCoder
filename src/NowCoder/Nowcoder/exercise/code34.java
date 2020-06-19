package NowCoder.Nowcoder.exercise;

import java.util.LinkedHashMap;

public class code34 {
    public static char firstNotRepeatingChar(String str){
        if (str == null){
            return ' ';
        }

        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

        char[] s = str.toCharArray();

        for (int i = 0; i < s.length; i++) {
            if (map.containsKey(s[i])){
                map.put(s[i], map.get(s[i]) + 1);
            }else {
                map.put(s[i], 1);
            }
        }

        for (int i = 0; i < s.length; i++) {
            if (map.get(s[i]) == 1){
                return s[i];
            }
        }

        return ' ';
    }

    public static void main(String[] args) {
        String str = "abaccdeff";
        System.out.println(firstNotRepeatingChar(str)); // b
    }
}
