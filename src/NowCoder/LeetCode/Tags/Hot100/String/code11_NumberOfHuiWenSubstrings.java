package NowCoder.LeetCode.Tags.Hot100.String;

/**
 * 647. 回文子串的个数:
 *  给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *  具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 */
public class code11_NumberOfHuiWenSubstrings {

    /**
     *  Manacher算法: 类似于code02_LongestPalindrome
     *      code02_LongestPalindrome: 计算最长回文子串
     *      code11_NumberOfHuiWenSubstrings: 计算回文子串的个数
     *
     *   TODO pArr回文半径数组: 记录的是当前位置的最长回文
     *          因为回文个数是从自身开始算的, 所以从1,2,3,...计算
     *    (1) 回文个数 = 该位置的回文半径长度 = pArr[i]/2
     *    (2) 回文直径 = pArr[i] - 1
     *    (3) 回文半径 = pArr[i] / 2
     *
     */
    public static int numberOfHuiWenSubstrings1(String s) {
        if (s == null) {
            return 0;
        }

        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int count = 0;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }

            count += pArr[i] / 2;
        }

        return count;
    }

    public static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        char[] res = new char[2 * str.length + 1];
        int index = 0;

        for (int i = 0; i < res.length; i++) {
            res[i] = i % 2 == 0 ? '#' : str[index++];
        }

        return res;
    }

    /**
     * 暴力法 + 中心扩散
     *
     */
    public static int numberOfHuiWenSubstrings2(String s) {
        if (s == null) {
            return 0;
        }

        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int count = 0;

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

            count += R / 2;
        }

        return count;
    }


    public static void main(String[] args) {
        String s = "aaaa";

        System.out.println(numberOfHuiWenSubstrings1(s));
        System.out.println(numberOfHuiWenSubstrings2(s));
    }
}
