package NowCoder.advanced_class.exercise.exercise01;

public class code05 {
    public static String shortestEnd(String s){
        if (s == null || s.length() < 1){
            return null;
        }

        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int len = 0;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;
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

            if (R == str.length){
                len = pArr[i] - 1;
                break;
            }
        }

        char[] res = new char[s.length() - len];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = str[2 * i + 1];
        }

        return String.valueOf(res);
    }

    public static char[] manacherString(String s){
        char[] str = s.toCharArray();
        char[] res = new char[2 * s.length() + 1];
        int index = 0;

        for (int i = 0; i < res.length; i++) {
            res[i] = i % 2 == 0 ? '#' : str[index++];
        }

        return res;
    }

    public static void main(String[] args) {
        String str2 = "abcd123321";
        System.out.println(shortestEnd(str2)); // dcba
    }
}
