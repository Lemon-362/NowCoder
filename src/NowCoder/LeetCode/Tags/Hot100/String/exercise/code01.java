package NowCoder.LeetCode.Tags.Hot100.String.exercise;

import java.util.HashSet;

public class code01 {
    public static int lengthOfLongestSubstring(String s){
        if (s == null){
            return 0;
        }

        int L = 0;
        int R = 0;
        int len = 0;
        HashSet<Character> set = new HashSet<>();

        while (L < s.length()){
            while (R < s.length() && !set.contains(s.charAt(R))){
                set.add(s.charAt(R));
                R++;
            }

            len = Math.max(len, R - 1 - L + 1);

            set.remove(s.charAt(L));
            L++;
        }

        return len;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s)); // 3

        s = "bbbbb";
        System.out.println(lengthOfLongestSubstring(s)); // 1

        s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s)); // 3
    }
}
