package NowCoder.Addition.exercise;

public class code01_RegularExpressionMatch {
    public static boolean process(char[] str, char[] exp, int i, int j){
        // base case
        if (j == exp.length){
            return i == str.length;
        }

        // j < exp.length

        // 1 j == exp.length - 1
        if (j == exp.length - 1){
            return i == str.length - 1 && (exp[j] == str[i] || exp[j] == '.');
        }

        // 2 exp[j + 1] != '*'
        if (exp[j + 1] != '*'){
            return i < str.length && (exp[j] == str[i] || exp[j] == '.')
                    && process(str, exp, i + 1, j + 1);
        }

        /*
        // 1和2合并
        if (j == exp.length - 1 || exp[j + 1] != '*'){
            return i < str.length && (exp[j] == str[i] || exp[j] == '.')
                    && process(str, exp, i + 1, j + 1);
        }
         */

        // exp[j + 1] == '*'

        // 3.2 _* == str[i]
        while (i < str.length && (exp[j] == str[i] || exp[j] == '.')){
            if (process(str, exp, i, j + 2)){
                return true;
            }
            i++;
        }

        // 3.1 _= != str[i]
        return process(str, exp, i, j + 2);
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

    public static boolean isMatch(String s, String e){
        if (s == null || e == null){
            return false;
        }

        char[] str = s.toCharArray();
        char[] exp = e.toCharArray();

        return isValid(str, exp) && process(str, exp, 0, 0);
    }

    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp)); // true
//        System.out.println(isMatchDP(str, exp));

        System.out.println("*******************");

        str = "mississippi";
        exp = "mis*is*ip*.";
        System.out.println(isMatch(str, exp)); // true
//        System.out.println(isMatchDP(str, exp));
    }
}
