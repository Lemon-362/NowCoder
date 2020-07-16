package NowCoder.LeetCode.Tags.Hot100.String;

/**
 * 5. 最长回文子串：
 *  给定一个字符串 s，找到 s 中最长的回文子串，返回该子串
 */
public class code02_LongestPalindrome {
    /*
        1. Manacher算法
        TODO 这里需要返回回文子串，而不是长度
        因此要用if来判断是否是最长长度，并记录此时回文的开始位置
            len = pArr[i] - 1;
            start = (i - len) / 2;
     */
    public static String longestPalindrome1(String s){
        if (s == null){
            return null;
        }

        char[] str = manacherString(s);
        int R = -1;
        int C = -1;
        int[] pArr = new int[str.length];
        int start = 0;
        int len = 0;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2*C-i]) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if (str[i+pArr[i]] == str[i-pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }

            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }

            if (len < pArr[i] - 1){
                // TODO pArr回文半径数组记录的是manacherString的整个回文, 实际上是s的回文直径
                // TODO pArr回文半径数组记录的是当前位置最长回文
                len = pArr[i] - 1;
                start = (i - len) / 2;
            }
        }

        return s.substring(start, start + len);
    }

    /*
        2. 暴力法 + 双指针法（中心扩散法）：
            从某一字符开始向两边扩，逐一比较，不同则后移一个重复操作
          TODO 需要注意：
           中心扩散的方法只适用于奇数长度的回文
           对于偶数长度的回文可以先对字符串进行改造，利用ManacherString的方法
     */
    public static String longestPalindrome2(String s){
        if (s == null){
            return null;
        }

        char[] str = manacherString(s);
        int start = 0;
        int len = 0;

        for (int i = 0; i < str.length; i++) {
            int R = 0;
            int l = i;
            int r = i;
            while (l > -1 && r < str.length){
                if (str[l] == str[r]){
                    R++;
                }else {
                    break;
                }
                l--;
                r++;
            }

            if (len < R - 1){
                len = R - 1;
                start = (i - len) / 2;
            }
        }

        return s.substring(start, start + len);
    }

    public static char[] manacherString(String s){
        char[] str = s.toCharArray();
        char[] res = new char[2 * str.length + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = i % 2 == 0 ? '#' : str[index++];
        }

        return res;
    }

    public static void main(String[] args){
        String str1 = "abc123321ab";
        String str2 = "bb";
        System.out.println(longestPalindrome1(str1)); // 1234321
        System.out.println(longestPalindrome2(str1)); // 1234321
        System.out.println(longestPalindrome1(str2)); // bab
        System.out.println(longestPalindrome2(str2)); // bab
    }
}
