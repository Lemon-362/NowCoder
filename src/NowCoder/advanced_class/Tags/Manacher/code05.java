package NowCoder.advanced_class.Tags.Manacher;

public class code05 {
    public static String shortestEnd(String s) {
        if (s == null || s.length() < 1) {
            return null;
        }

        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int len = 0;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;
            while (i - pArr[i] > -1 && i + pArr[i] < str.length) {
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

            if (R == str.length) {
                len = pArr[i] - 1;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - len - 1; i >= 0; i--) {
            sb.append(s.substring(i, i + 1));
        }

        return sb.toString();
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

    public static void main(String[] args) {
        String str2 = "abcd1234321";
        System.out.println(shortestEnd(str2)); // dcba
    }
}
