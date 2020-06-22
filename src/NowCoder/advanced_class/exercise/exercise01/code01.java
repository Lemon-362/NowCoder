package NowCoder.advanced_class.exercise.exercise01;

public class code01 {
    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() > s1.length()) {
            return -1;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        int[] next = getNextArr(str2);
        int p1 = 0;
        int p2 = 0;

        while (p1 < str1.length && p2 < str2.length){
            if (str1[p1] == str2[p2]){
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

        return p2 == str2.length ? p1 - p2 : -1;
    }

    public static int[] getNextArr(char[] str){
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int p = 2;
        int cn = 0;

        while (p < next.length){
            if (str[p - 1] == str[cn]){
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
