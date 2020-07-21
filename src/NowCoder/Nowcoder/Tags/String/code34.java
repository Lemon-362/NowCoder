package NowCoder.Nowcoder.Tags.String;

import java.util.LinkedHashMap;

public class code34 {
    public static char firstNotRepeatingChar(String str) {
        if (str == null) {
            return ' ';
        }

        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < str.length(); i++) {
            if (!map.containsKey(str.charAt(i))){
                map.put(str.charAt(i), 1);
            }else {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            }
        }

        for (Character ch : map.keySet()){
            if (map.get(ch) == 1){
                return ch;
            }
        }

        return ' ';
    }

    public static int firstNotRepeatingCharWithoutMap(String str) {
        if (str == null) {
            return -1;
        }

        int[] arr = new int[128];
        char[] s = str.toCharArray();

        for (int i = 0; i < s.length; i++) {
            arr[s[i]]++;
        }

        int index = Integer.MAX_VALUE;
        char res = ' ';

        for (int i = 0; i < s.length; i++) {
            char ch = s[i];
            if (arr[ch] == 1 && ch < index){
                index = i;
                res = ch;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        String str = "abaccdeff";
        System.out.println(firstNotRepeatingChar(str)); // b
        System.out.println(firstNotRepeatingCharWithoutMap(str)); // 1
    }
}
