package NowCoder.advanced_class.exercise.advance;

/*
    KMP应用1：
		已知一字符串，要求在后面添加字符，使得包含两次原始字符串（开头位置不同），且添加的字符串长度最短

	解法：
	    改进next数组，求整个字符串的匹配程度，然后将前缀与后缀对齐，在后面补上前缀后的字符串
	    这样第二次出现是从原始串的后缀开始往后所有
 */
public class code02_KMP_ShortestHaveTwice {
    public static class KMP {
        private int len;
        private String s;

        public KMP(int len, String s) {
            this.len = len;
            this.s = s;
        }
    }

    public static KMP shortestHaveTwice(String s){
        if (s == null){
            return new KMP(0, null);
        }

        if (s.length() == 1){
            return new KMP(1, s);
        }

        char[] str = s.toCharArray();

        int len = getNextArr(str);

        return new KMP(len, s.substring(len));
    }

    public static int getNextArr(char[] str){
        int[] next = new int[str.length + 1];
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
