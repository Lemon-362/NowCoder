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
            String str = strs[i];
            char[] s = str.toCharArray();

            Arrays.sort(s);

            if (!map.containsKey(String.valueOf(s))){
                map.put(String.valueOf(s), new ArrayList<>());
            }

            map.get(String.valueOf(s)).add(str);
        }

        for(String key: map.keySet()){
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
