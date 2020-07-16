package NowCoder.LeetCode.Tags.Hot100.String.exercise;

public class code02 {
    public static String longestPalindrome1(String s){
        if (s == null){
            return null;
        }

        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int len = 0;
        int start = 0;

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
                len = pArr[i] - 1;
                start = (i - len) / 2;
            }
        }

        return s.substring(start, start + len);
    }

    public static String longestPalindrome2(String s){
        if (s == null){
            return null;
        }

        char[] str = manacherString(s);
        int len = 0;
        int start = 0;

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
        String str1 = "abc1234321ab";
        String str2 = "bb";
        System.out.println(longestPalindrome1(str1)); // 1234321
        System.out.println(longestPalindrome2(str1)); // 1234321
        System.out.println(longestPalindrome1(str2)); // bb
        System.out.println(longestPalindrome2(str2)); // bb
    }
}
