package NowCoder.LeetCode.Tags.Hot100.String;

import java.util.HashSet;

/**
 * 3. 无重复字符的最长子串：
 *  给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 *
 *  思路：
 *    找以每个字符开头的最长无重复子串，那么答案一定在其中
 *    1. 用start遍历每一个字符，找以start位置开头的最长无重复子串
 *    2. 在每次遍历中，用end往后遍历，直到set中出现了重复字符时停止
 *       此时，start - end（不包括end） 就是以start位置开头的最长无重复子串
 *    3. 可以保证start+1 - end 是一定不会重复的，所以可以继续从end往后找
 *       将start位置从set中移除，start到下一个位置
 */
public class code01_LengthOfLongestSubstring {
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

    /*
    方法二:
        利用剑指code54的字符流中第一个不重复的字符的方法, 将出现的位置保存在数组
     */
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
        System.out.println(lengthOfLongestSubstring(s)); // 3

        s = "bbbbb";
        System.out.println(lengthOfLongestSubstring(s)); // 1

        s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s)); // 3
    }
}
