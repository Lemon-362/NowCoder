package NowCoder.LeetCode.Hot.exercise;

import java.util.HashSet;

public class code03 {
    public static int longestSubString(String s){
        if (s == null || s.length() < 1){
            return -1;
        }

        HashSet<Character> set = new HashSet<>();

        int L = 0;
        int R = 0;
        int len = 0;

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
        String s1 = "abcabcbb";
        String s2 = "pwwkew";
        String s3 = "tmmzuxt";

        System.out.println(longestSubString(s1)); // 3
        System.out.println(longestSubString(s2)); // 3
        System.out.println(longestSubString(s3)); // 5
    }
}
