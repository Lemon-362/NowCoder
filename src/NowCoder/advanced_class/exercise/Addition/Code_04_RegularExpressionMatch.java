package NowCoder.advanced_class.exercise.Addition;

/*
    字符串匹配问题：
        给定字符串str，其中不含.和*
        再给定匹配字符串exp，其中可以含有.和*
        *不能是exp首字符，且任两个*不相邻。
        .可以代表任一个字符，*表示前一个字符可以有0个或者多个
        写一个函数用于判断str是否能被exp匹配
 */
public class Code_04_RegularExpressionMatch {

    public static boolean isValid(char[] s, char[] e) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) && process(s, e, 0, 0);
    }

    // 递归
    // str[i...一直到最后]，能否被exp[j...一直到最后]，匹配出来
    public static boolean process(char[] str, char[] exp, int i, int j) {
        if (j == exp.length) { // 当前exp已经匹配完了，当前的j指向的是" "
            // 如果str当前i位置还有字符，那么肯定匹配不成功，所以要求str的i也必须到最后，即str也匹配完了，当前的i也指向" "
            return i == str.length;
        }
        // 前提：j位置有字符，考察j+1的情况
        if (j == exp.length - 1 || exp[j + 1] != '*') { // j到达末尾了，无j+1位置（可能1） 或者 j+1不是*（可能2）
            // i != str.length：如果j位置还有字符，而i都匹配完了，那么直接返回false，也就是要求i至少在str的末尾，而不是末尾的后一个
            // exp[j] == str[i] || exp[j] == '.'：如果i和j位置匹配上了（j位置可能和i相同，可能为.），这只是可能可以匹配，要看i+1和j+1
            // process(str, exp, i + 1, j + 1)：继续往后判断是否匹配
            return i != str.length && (exp[j] == str[i] || exp[j] == '.')
                    && process(str, exp, i + 1, j + 1);
        }
        // 前提：exp的j+1位置是*
        while (i != str.length && (exp[j] == str[i] || exp[j] == '.')) { // 可能3B：i和j能够匹配
            if (process(str, exp, i, j + 2)) { // 首先认为j和j+1表示0个i，看j+2和i是否匹配
                // 然后依次认为j和j+1表示1，2，3，。。。个i，看j+2和i+1，i+2，。。。，是否匹配
                // 只要有一个匹配，直接返回true
                return true;
            }
            // 表示exp的j和j+1表示1个str的i
            i++; // 所以i最后再++
        }
        // exp[j] == str[i] || exp[j] == '.'条件不成立：j位置和i不同，且不是.（可能3A），默认j和j+1表示0个i，让j+2往后和i匹配
        return process(str, exp, i, j + 2);
    }

    // 动态规划DP
    public static boolean isMatchDP(String s, String e) {
        if (s == null || e == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = e.toCharArray();

        if (!isValid(str, exp)) {
            return false;
        }

        boolean[][] dp = initDPMap(str, exp); // dp表的初始化，因为dp有些base case不能直接填好

        for (int i = str.length - 1; i >= 0; i--) {
            for (int j = exp.length - 2; j >= 0; j--) {
                if (exp[j + 1] != '*') {
                    dp[i][j] = (exp[j] == str[i] || exp[j] == '.')
                            && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si < str.length && (exp[j] == str[si] || exp[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    // 填好倒数两列和最后一行
    public static boolean[][] initDPMap(char[] str, char[] exp) {
        int slen = str.length;
        int elen = exp.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true;
        if (slen > 0 && elen > 0) {
            if ((str[slen - 1] == exp[elen - 1]) || exp[elen - 1] == '.') {
                dp[slen - 1][elen - 1] = true;
            }
        }
        for (int j = elen - 2; j >= 0; j = j - 2) {
            if (exp[j] != '*' && exp[j + 1] == '*') {
                dp[slen][j] = true;
            } else {
                break;
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp)); // true
        System.out.println(isMatchDP(str, exp));

        System.out.println("*******************");

        str = "mississippi";
        exp = "mis*is*ip*.";
        System.out.println(isMatch(str, exp)); // true
        System.out.println(isMatchDP(str, exp));

    }

}
