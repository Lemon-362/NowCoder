package NowCoder.LeetCode.Tags.Hot100.String.exercise;

import java.util.HashSet;

public class code01 {
    public static int lengthOfLongestSubstring(String s){
        if (s == null){
            return 0;
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

    public static int lengthOfLongestSubstring2(String s){

        int[] arr = new int[128];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }

        int len = 0;
        int pre = -1;

        char[] str = s.toCharArray();

        for (int i = 0; i < str.length; i++) {
            char ch = str[i];

            if (arr[ch] == -1){
                arr[ch] = i;
            }else if (arr[ch] >= 0){
                int temp = arr[ch];
                arr[ch] = i;

                len = Math.max(len, arr[ch] - 1 - pre);

                pre = temp;
            }
        }

        return len;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring2(s)); // 3

        s = "bbbbb";
        System.out.println(lengthOfLongestSubstring2(s)); // 1

        s = "pwwkew";
        System.out.println(lengthOfLongestSubstring2(s)); // 3
    }
}
