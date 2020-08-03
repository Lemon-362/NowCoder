package NowCoder.LeetCode.Tags.Hot100.DP;

/**
 * 正则表达式匹配
 */
public class code02_RegularExpressionMatch {
    public static boolean isMatch(String str, String exp){
        if (str == null || exp == null){
            return false;
        }

        return isValid(str.toCharArray(), exp.toCharArray())
                && process(str.toCharArray(), exp.toCharArray(), 0, 0);
    }

    public static boolean process(char[] str, char[] exp, int i, int j){
        // base case
        if (j == exp.length){
            return i == str.length;
        }

        // 1
        if (j == exp.length - 1){
            return i == str.length - 1 && (exp[j] == str[i] || exp[j] == '.');
        }

        // 2
        if (exp[j + 1] != '*'){
            return i < str.length && (exp[j] == str[i] || exp[j] == '.')
                    && process(str, exp, i + 1, j + 1);
        }

        // 3.2
        while (i < str.length && (exp[j] == str[i] || exp[j] == '.')){
            if (process(str, exp, i, j + 2)){
                return true;
            }
            i++;
        }

        // 3.1
        return process(str, exp, i, j + 2);
    }

    public static boolean isMatchDP(String s, String e) {
        if (s == null || e == null) {
            return false;
        }

        int sLen = s.length();
        int eLen = e.length();
        char[] str = s.toCharArray();
        char[] exp = e.toCharArray();

        if (!isValid(str, exp)){
            return false;
        }

        boolean[][] dp = new boolean[sLen + 1][eLen + 1];

        // base case
//        if (j == exp.length){
//            return i == str.length;
//        }
        dp[sLen][eLen] = true;

        // 1
//        if (j == exp.length - 1){
//            return i == str.length - 1 && (exp[j] == str[i] || exp[j] == '.');
//        }
        if (sLen > 0 && eLen > 0) {
            dp[sLen - 1][eLen - 1] = (exp[eLen - 1] == str[sLen - 1] || exp[eLen - 1] == '.');
        }

        for (int j = eLen - 2; j >= 0; j = j - 2) {
            if (exp[j] != '*' && exp[j + 1] == '*'){
                dp[sLen][j] = true;
            }else {
                break;
            }
        }

//        // 2
//        if (exp[j + 1] != '*'){
//            return i < str.length && (exp[j] == str[i] || exp[j] == '.')
//                    && process(str, exp, i + 1, j + 1);
//        }
//        // 3.2
//        while (i < str.length && (exp[j] == str[i] || exp[j] == '.')){
//            if (process(str, exp, i, j + 2)){
//                return true;
//            }
//            i++;
//        }
//        // 3.1
//        return process(str, exp, i, j + 2);
        for (int i = sLen - 1; i >= 0; i--) {
            for (int j = eLen - 2; j >= 0; j--) {
                if (exp[j + 1] != '*'){
                    dp[i][j] = (exp[j] == str[i] || exp[j] == '.')
                    && dp[i + 1][j + 1];
                }else {
                    int curI = i;
                    while (curI < sLen && (exp[j] == str[curI] || exp[j] == '.')){
                        if (dp[curI][j + 2]){
                            dp[i][j] = true;
                            break;
                        }
                        curI++;
                    }

                    if (dp[i][j] != true){
                        dp[i][j] = dp[curI][j + 2];
                    }
                }
            }
        }

        return dp[0][0];
    }

    public static boolean isValid(char[] str, char[] exp){
        for (char c : str) {
            if (c == '*' || c == '.'){
                return false;
            }
        }

        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == '*' && (i == 0 || exp[i - 1] == '*')){
                return false;
            }
        }

        return true;
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
