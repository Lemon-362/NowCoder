package NowCoder.LeetCode.Tags.Hot100.String.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class code08 {
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }

        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];

            char[] ch = s.toCharArray();
            Arrays.sort(ch);

            String key = String.valueOf(ch);

            if (!map.containsKey(key)){
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(key, list);
            }else {
                List<String> list = map.get(key);
                list.add(s);
                map.put(key, list);
            }
        }

        for(String key : map.keySet()){
            res.add(map.get(key));
        }

        return res;
    }

    public static void main(String[] args) {
        String[] str = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> res = groupAnagrams(str);

        for (List<String> list : res) {
            for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
//        eat tea ate
//        bat
//        tan nat
    }
}
