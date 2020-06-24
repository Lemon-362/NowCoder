package NowCoder.advanced_class.exercise.Addition;

/*
    正则表达式（字符串）匹配问题：
        给定字符串str，其中不含.和*
        再给定匹配字符串exp，其中可以含有.和*
        *不能是exp首字符，且任两个*不相邻。
        .可以代表任一个字符，*表示前一个字符可以有0个或者多个
        写一个函数用于判断str是否能被exp匹配
 */
public class RegularExpressionMatch {
    /**
     * 递归法：f(i, j)表示str从i开始到最后能否被exp从j开始到最后匹配成功
     *  因为 * 不能单独出现, 必须和字符成对出现(_*)
     *  所以分情况讨论exp的j+1位置是不是*
     *
     * (1) exp(j)是最后一个字符, 根本就没有exp(j+1)字符
     *  ==> 此时 j == exp.length - 1
     *  ==> 要求 exp(j) 和 str(i) 能够匹配, 并且str(i)也是最后一个字符
     *  ==> 即   if(exp(j) == str(i) && i == str.length - 1)
     *      那么 return true
     *      否则 return false
     *
     * (2) exp(j+1)有字符, 且exp(j+1) != *
     *  ==> 此时 j < exp.length - 1 && exp(j + 1) != *
     *  ==> 要求 exp(j) 和 str(i) 必须可以匹配, 即 exp(j)要么和str(i)相同, 要么是.(表示任意字符)
     *  ==> 即   if(exp(j) == str(i) || exp(j) == '.')
     *      那么 向后递归, 考察 f(i+1, j+1)
     *      否则 return false
     *
     * (3) exp(j+1)有字符, 且exp(j+1) == *
     *  ==> 此时 j < exp.length - 1 && exp(j + 1) == *
     *  ==> 此时 exp(j)和exp(j+1)表示 _*, 那么就要看这个整体能否和str(i)匹配
     *      1) exp(j)和exp(j+1)不能和str(i)匹配, 即exp(j)和str(i)不同
     *       ==> 此时 exp(j) != str(i)
     *       ==> 要求 exp(j)和exp(j+1)代表的 _* 表示0个str(i)
     *       ==> 此时才能向后递归, 考察f(i, j+2)
     *      2) exp(j)和exp(j+1)能和str(i)匹配, 即exp(j)要么和str(i)相同, 要么是.(表示任意字符)
     *       ==> 此时 exp(j) == str(i) || exp(j) == '.'
     *       ==> 因为 str(i)之后可能有多个和str(i)相同连续的字符, 而 _* 可以表示多个 _
     *       ==> 所以分情况讨论 _* 表示几个str(i)
     *          - _*匹配0个str(i) ==> 递归 ==> f(i, j+2)
     *          - _*匹配1个str(i) ==> 递归 ==> f(i+1, j+2)
     *          - _*匹配2个str(i) ==> 递归 ==> f(i+2, j+2)
     *          - 只要其中有一次可以匹配, 那么就 return true
     *          - 结束条件: str(i)及其之后连续的字符都匹配完, 依然没有return true, 那么 f(i,j) return false
     *
     */
    public static boolean process(char[] str, char[] exp, int i, int j){
        // base case: 要求exp到末尾时, str也到末尾, 否则str剩下的字符无法匹配
        if (j == exp.length){ // exp[j]没有字符
            return i == str.length;
        }

        // j < exp.length ==> 此时exp[j]有字符, 考察exp[j+1]
        /*
            情况1: exp(j)是最后一个字符, 根本就没有exp(j+1)字符 ==> j == exp.length - 1
                因为情况1不成立, 那么隐含了 j < exp.length - 1
                所以情况2的一个if可以省略
            情况2: exp(j+1)有字符, 且exp(j+1) != '*' ==> exp[j + 1] != '*'
         */
        if (j == exp.length - 1 || exp[j + 1] != '*'){
            return i != str.length
                    && (exp[j] == str[i] || exp[j] == '.')
                    && process(str, exp, i + 1, j + 1);
        }

        // exp[j + 1] == '*' ==> 此时exp[j+1]位置是*
        /*
            情况3.2: exp(j)和exp(j+1)能和str(i)匹配, 即exp(j)要么和str(i)相同, 要么是.(表示任意字符)
            ==> exp(j) == str(i) || exp(j) == '.'
         */
        while (i < str.length && (exp[j] == str[i] || exp[j] == '.')){
            if (process(str, exp, i, j + 2)){
                return true;
            }
            i++;
        }

        /*
            情况3.1: exp(j)和exp(j+1)不能和str(i)匹配, 即exp(j)和str(i)不同
            ==> exp(j) != str(i)
         */
        return process(str, exp, i, j + 2);
    }

    public static boolean isValid(char[] str, char[] exp) {
        // 要求str不含有.和*
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '*' || str[i] == '.') {
                return false;
            }
        }
        // 要求exp的第一个字符不能是*, 且*前面必须有字符, 且两个*不能连续
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == '*' && (i == 0 || exp[i - 1] == '*')) {
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

    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp)); // true
//        System.out.println(isMatchDP(str, exp));

        System.out.println("*******************");

        str = "mississippi";
        exp = "mis*is*p*.";
        System.out.println(isMatch(str, exp)); // false
//        System.out.println(isMatchDP(str, exp));
    }
}
