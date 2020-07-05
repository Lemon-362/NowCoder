package NowCoder.LeetCode.Hot.exercise;

public class code05 {
    public static String longestHuiWenString1(String s){
        if (s == null || s.length() < 2){
            return null;
        }

        char[] str = manacherString(s);
        int start = 0;
        int len = 0;

        for (int i = 0; i < str.length; i++) {
            int R = 0;
            int l = i;
            int r = i;
            while (l >= 0 && r < str.length){
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

    public static String longestHuiWenString2(String s){
        if (s == null || s.length() < 2){
            return null;
        }

        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int len = 0;
        int start = 0;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;
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

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        String str2 = "bb";

        System.out.println(longestHuiWenString1(str1)); // 1234321
        System.out.println(longestHuiWenString2(str1)); // 1234321

        System.out.println(longestHuiWenString1(str2)); // bab
        System.out.println(longestHuiWenString2(str2)); // bab
    }
}
