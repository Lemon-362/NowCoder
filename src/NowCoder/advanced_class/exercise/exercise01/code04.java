package NowCoder.advanced_class.exercise.exercise01;

public class code04 {
    public static int maxLength(String s) {
        if (s == null || s.length() < 1) {
            return -1;
        }

        char[] str = manacherString(s);

        int len = 0;
        int R = -1;
        int C = -1;
        int[] pArr = new int[str.length];

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

            len = Math.max(len, pArr[i]);
        }

        return len - 1;
    }

    public static int maxLength1(String s){
        if (s == null || s.length() < 1) {
            return -1;
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

            len = Math.max(len, R - 1);
        }

        return len;
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
        String str1 = "abc1234321ab";
        System.out.println(maxLength(str1)); // 7
        System.out.println(maxLength1(str1));
    }
}
