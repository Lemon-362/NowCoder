package NowCoder.LeetCode.Hot;

import java.util.HashSet;

/**
 * 无重复字符的最长子串：
 *  给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 *
 * 思路：
 *  找以每个字符开头的最长无重复子串，那么答案一定在其中
 *  1. 用start遍历每一个字符，找以start位置开头的最长无重复子串
 *  2. 在每次遍历中，用end往后遍历，直到set中出现了重复字符时停止
 *     此时，start - end（不包括end） 就是以start位置开头的最长无重复子串
 *  3. 可以保证start+1 - end 是一定不会重复的，所以可以继续从end往后找
 *     将start位置从set中移除，start到下一个位置
 *
 */
public class code03_LongestSubString {
    public static int longestSubString(String s){
        HashSet<Character> set = new HashSet<>();
        int start = 0;
        int end = 0;
        int len = 0;

        while (start < s.length()){
            while (end < s.length() && !set.contains(s.charAt(end))){
                set.add(s.charAt(end));
                end++;
            }

            len = Math.max(len, end - start);

            set.remove(s.charAt(start));
            start++;
        }

        return len;
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "pwwkew";
        String s3 = "tmmzuxt";

        System.out.println(longestSubString(s1));
        System.out.println(longestSubString(s2));
        System.out.println(longestSubString(s3));
    }
}
