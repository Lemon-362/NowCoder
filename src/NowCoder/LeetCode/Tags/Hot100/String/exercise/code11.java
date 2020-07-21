package NowCoder.LeetCode.Tags.Hot100.String.exercise;

public class code11 {
    public static int numberOfHuiWenSubstrings1(String s){
        if (s == null) {
            return 0;
        }

        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int res = 0;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2*C-i]) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if (str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }

            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }

            res += pArr[i] / 2;
        }

        return res;
    }

    public static int numberOfHuiWenSubstrings2(String s) {
        if (s == null) {
            return 0;
        }

        char[] str = manacherString(s);
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

    public static char[] manacherString(String s){
        char[] str = s.toCharArray();
        char[] res = new char[2 * str.length + 1];
        int index = 0;

        for (int i = 0; i < res.length; i++) {
            res[i] = i % 2 == 0 ? '#' : str[index++];
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(numberOfHuiWenSubstrings1(s)); // 10
        System.out.println(numberOfHuiWenSubstrings2(s));

        s = "aaa";
        System.out.println(numberOfHuiWenSubstrings1(s)); // 6
        System.out.println(numberOfHuiWenSubstrings2(s));

        s = "abc";
        System.out.println(numberOfHuiWenSubstrings1(s)); // 3
        System.out.println(numberOfHuiWenSubstrings2(s));
    }
}
