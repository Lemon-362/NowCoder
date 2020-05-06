package NowCoder.HuaWei;

import java.util.*;

public class code23_DeleteLeastChar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            char[] s = str.toCharArray();
            Map<Character, Integer> map = new LinkedHashMap<>();
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                if (map.containsKey(s[i])) {
                    map.put(s[i], map.get(s[i]) + 1);
                } else {
                    map.put(s[i], 1);
                }
            }
            Collection<Integer> a = map.values(); // 获得value值
            int index = Collections.min(a); // 找到最小值
            for (char c : s) {
                if (map.get(c) != index)
                    res.append(c);
            }
            System.out.print(res.toString());
            System.out.println();
        }
    }
}
