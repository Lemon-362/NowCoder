package NowCoder.advanced_class.exercise.exercise01;

public class code02 {
    public static class KMP {
        private int len;
        private String s;

        public KMP(int len, String s) {
            this.len = len;
            this.s = s;
        }
    }

    public static KMP shortestHaveTwice(String str) {
        if (str == null) {
            return new KMP(0, null);
        }

        if (str.length() == 1){
            return new KMP(1, str);
        }

        char[] s = str.toCharArray();
        int len = getNextArr(s);

        return new KMP(len, str.substring(len));
    }

    public static int getNextArr(char[] s){
        int[] next = new int[s.length + 1];
        next[0] = -1;
        next[1] = 0;
        int p = 2;
        int cn = 0;

        while (p < next.length){
            if (s[p - 1] == s[cn]){
                next[p++] = ++cn;
            }else if (cn > 0 ){
                cn = next[cn];
            }else {
                next[p++] = 0;
            }
        }

        return next[next.length - 1];
    }

        public static void main(String[] args) {
        String test1 = "a";
        System.out.println(shortestHaveTwice(test1).len); // 1
        System.out.println(shortestHaveTwice(test1).s); // a
        System.out.println("==========================");
        String test2 = "aa";
        System.out.println(shortestHaveTwice(test2).len); // 1
        System.out.println(shortestHaveTwice(test2).s); // a
        System.out.println("==========================");
        String test3 = "ab";
        System.out.println(shortestHaveTwice(test3).len); // 0
        System.out.println(shortestHaveTwice(test3).s); // ab
        System.out.println("==========================");
        String test4 = "abcdabcd";
        System.out.println(shortestHaveTwice(test4).len); // 4
        System.out.println(shortestHaveTwice(test4).s); // abcd
        System.out.println("==========================");
        String test5 = "abc12abc";
        System.out.println(shortestHaveTwice(test5).len); // 3
        System.out.println(shortestHaveTwice(test5).s); // 12abc
    }
}
