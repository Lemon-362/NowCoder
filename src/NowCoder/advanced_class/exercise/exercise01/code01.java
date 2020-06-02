package NowCoder.advanced_class.exercise.exercise01;

public class code01 {
    public static int getIndexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str2.length() > str1.length()) {
            return -1;
        }

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int[] next = getNextArr(s2);

        int p1 = 0;
        int p2 = 0;

        while (p1 < s1.length && p2 < s2.length){
            if (s1[p1] == s2[p2]){
                p1++;
                p2++;
            }else {
                if (next[p2] == -1){
                    p1++;
                }else {
                    p2 = next[p2];
                }
            }
        }

        return p2 == s2.length ? p1 - p2 : -1;
    }

    public static int[] getNextArr(char[] s){
        int[] next = new int[s.length];
        next[0] = -1;
        next[1] = 0;
        int p = 2;
        int cn = 0;

        while (p < next.length){
            if (s[p - 1] == s[cn]){
                next[p++] = ++cn;
            }else if (cn > 0){
                cn = next[cn];
            }else {
                next[p++] = 0;
            }
        }

        return next;
    }

    public static void main(String[] args) {
        String str = "acabcababaccc";
        String match = "bcab";
        System.out.println(str.indexOf(match)); // 3
        System.out.println(getIndexOf(str, match)); // 3
    }
}
