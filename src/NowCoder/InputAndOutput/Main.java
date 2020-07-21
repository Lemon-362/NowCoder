package InputAndOutput;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] s = str.split(",");
            boolean flag = false;
            if (s.length <= 3 || s.length >= 100){
                flag = true;
            }
            if (flag){
                System.out.println("error.0001");
            }else {
                boolean flag1 = false;
                for (int i = 0; i < s.length; i++) {
                    char[] chars = s[i].toCharArray();
                    for (int j = 0; j < chars.length; j++) {
                        if (j == 0 && (chars[j] >= 'A' && chars[j] <= 'Z')) {
                            flag1 = true;
                        } else if (j > 0 && (chars[j] >= 'a' && chars[j] <= 'z')) {
                            flag1 = true;
                        } else {
                            flag1 = false;
                            break;
                        }
                    }
                }
                if (flag1) {
                    for (int i = 0; i < s.length; i++) {
                        s[i] = s[i].toLowerCase();
                    }
                    Arrays.sort(s, new myComparator());
                    TreeMap<String, Integer> map = new TreeMap<>();
                    for (int i = 0; i < s.length; i++) {
                        String s1 = s[i];
                        if (!map.containsKey(s1)) {
                            map.put(s1, 1);
                        } else {
                            map.put(s1, map.get(s1) + 1);
                        }
                    }
                    int max = 0;
                    String res = "";
                    for (String key : map.keySet()) {
                        int vote = map.get(key);
                        if (vote > max) {
                            max = vote;
                            res = key;
                        }
                    }
                    res = res.substring(0, 1).toUpperCase() + res.substring(1);
                    System.out.println(res);
                } else {
                    System.out.println("error.0001");
                }
            }
        }
    }

    public static class myComparator implements Comparator<String> {
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }
}
