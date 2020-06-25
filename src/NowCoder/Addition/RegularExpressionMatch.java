package NowCoder.Addition;

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
    public static boolean process(char[] str, char[] exp, int i, int j) {
        // base case
        if (j == exp.length) {
            return i == str.length;
        }

        // j < exp.length ==> exp[j+1]

        // 1 exp[j]是最后一个字符，exp[j+1]没有字符
        if (j == exp.length - 1){
            return i == str.length - 1 && (exp[j] == str[i] || exp[j] == '.');
        }

        // 2 exp[j+1]不是'*'
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

        // 3.1 exp[j+1]是'*'，且exp[j]和exp[j+1]能和str[i]匹配
        while (i < str.length && (exp[j] == str[i] || exp[j] == '.')) {
            if (process(str, exp, i, j + 2)) {
                return true;
            }
            i++;
        }


        // 3.2 exp[j+1]是'*'，但exp[j]和exp[j+1]不能和str[i]匹配
        return process(str, exp, i, j + 2);
    }

    public static boolean isValid(char[] str, char[] exp){
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '*' || str[i] == '.'){
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

    /**
     * 动态规划
     */
    public static boolean isMatchDP(String s, String e){
        if (s == null || e == null){
            return false;
        }

        char[] str = s.toCharArray();
        char[] exp = e.toCharArray();

        if (!isValid(str, exp)){
            return false;
        }

        boolean[][] dp = new boolean[str.length + 1][exp.length + 1];

        // base case
        dp[str.length][exp.length] = true;

        /*
            先看普遍位置所依赖的位置，发现缺少base case条件
            所以要先把base case填完整
            ==> 还需要倒数第二列和最后一行的base case
         */
        // 1 此时的情况1不依赖process其他位置，所以也看成是base case
        // 倒数第二列：表示exp只剩一个字符了，而str可能还有字符
        // 此时要求str也只能剩一个字符，并且str[i] == exp[j] || exp[j] == '.'
        if (exp.length > 0 && str.length > 0) { // 防止越界
            if (exp[exp.length - 1] == str[str.length - 1] || exp[exp.length - 1] == '.'){
                dp[str.length - 1][exp.length - 1] = true;
            }
        }
        // 最后一行：表示str没有字符了，而exp还有字符
        // 此时从后往前推，要求exp的字符满足 _* 成对出现，才能表示成0个字符，可以匹配
        for (int j = exp.length - 2; j >= 0; j = j -2) {
            // 从倒数第三列开始，要求倒数第三列不是*，倒数第二列是*，这样才能变成一对 _*
            // 因为要看是否两个字符是成对出现的_*，所以每次往前移动2格
            // 只要有一次不满足成对的条件，那么再往前都不满足
            // 并且只有在 _字符 的位置才可能是true，*的位置一定是false
            if (exp[j] != '*' && exp[j + 1] == '*'){
                dp[str.length][j] = true;
            }else {
                break;
            }
        }

        // 普遍位置：情况2和情况3依赖process
        for (int i = str.length - 1; i >= 0; i--) {
            for (int j = exp.length - 2; j >= 0; j--) {
                if (exp[j + 1] != '*'){ // 2
                    dp[i][j] = (exp[j] == str[i] || exp[j] == '.')
                            && dp[i + 1][j + 1];
                }else{
                    // 3.2
                    int curI = i;
                    while (curI < str.length && (exp[j] == str[curI] || exp[j] == '.')){
                        if (dp[curI][j + 2]){
                            dp[i][j] = true;
                            // 只要有一次满足，就return true，也就是要break
                            break;
                        }
                        curI++;
                    }

                    // 3.1
                    if (dp[i][j] != true){ // 此时可能 _* 表示0个str[i]，要看f(i, j+2)位置
                        // 并且此时，是从curI往后开始判断
                        dp[i][j] = dp[curI][j + 2];
                    }
                }
            }
        }

        // 目标
        return dp[0][0];
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
