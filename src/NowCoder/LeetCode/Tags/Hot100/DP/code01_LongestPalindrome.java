package NowCoder.LeetCode.Tags.Hot100.DP;

/**
 * 5. 最长回文子串：
 * 给定一个字符串 s，找到 s 中最长的回文子串，返回该子串
 */
public class code01_LongestPalindrome {
    /*
    方法一: Manacher算法
        TODO 这里需要返回回文子串，而不是长度
        因此要用if来判断是否是最长长度，并记录此时回文的开始位置
            len = pArr[i] - 1;
            start = (i - len) / 2;
     */
    public static String longestPalindrome1(String s) {
        if (s == null) {
            return null;
        }

        char[] str = manacherString(s);
        int R = -1;
        int C = -1;
        int[] pArr = new int[str.length];
        int start = 0;
        int len = 0;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * C - i]) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }

            if (len < pArr[i] - 1) {
                // TODO pArr回文半径数组记录的是manacherString的整个回文, 实际上是s的回文直径
                // TODO pArr回文半径数组记录的是当前位置最长回文
                len = pArr[i] - 1;
                start = (i - len) / 2;
            }
        }

        return s.substring(start, start + len);
    }

    /*
    方法二: 暴力法 + 双指针法（中心扩散法）
        从某一字符开始向两边扩，逐一比较，不同则后移一个重复操作
          TODO 需要注意：
           中心扩散的方法只适用于奇数长度的回文
           对于偶数长度的回文可以先对字符串进行改造，利用ManacherString的方法
     */
    public static String longestPalindrome2(String s) {
        if (s == null) {
            return null;
        }

        char[] str = manacherString(s);
        int start = 0;
        int len = 0;

        for (int i = 0; i < str.length; i++) {
            int R = 0;
            int l = i;
            int r = i;
            while (l > -1 && r < str.length) {
                if (str[l] == str[r]) {
                    R++;
                } else {
                    break;
                }
                l--;
                r++;
            }

            if (len < R - 1) {
                len = R - 1;
                start = (i - len) / 2;
            }
        }

        return s.substring(start, start + len);
    }

    public static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        char[] res = new char[2 * str.length + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = i % 2 == 0 ? '#' : str[index++];
        }

        return res;
    }

    /*
    方法三: 暴力递归
        TODO 自顶向下, 即大问题不断向内缩, 转化为同类的子问题
            直到有明确的不需要继续递归的条件base case时, 可以直接出结果
            然后一步步返回给大问题
        1. 回文串的特点:
        (1) 如果字符串的首尾字符不相等, 则这个字符串一定不是回文
        (2) 如果字符串的首尾字符相等, 则判断其子问题(子字符串)
            - 如果子问题也是回文, 则这个字符串是回文
            - 如果子问题不是回文, 则这个字符串不是回文

        2. f(i, j): 表示i~j的字符串是否是回文

        3. 分析可能性:
        (1) s[i]==s[j]
            当前字符串的首尾字符相等, 则有可能是回文
            - 如果f(i+1, j-1)返回true, 那么f(i, j)就是回文
                ==> process中, 当f(i+1, j-1)的最大长度等于i+1~j-1的长度时, 说明是此时的最长回文子串
            - 如果f(i+1, j-1)返回false, 那么f(i, j)不是回文
            ==> 此时转换为可能性2
        (2) s[i]!=s[j]
            当前字符串的首尾字符不相等, 则f(i, j)一定不是回文
            最长回文的可能性:
            - 左边缩进一个
            - 右边缩进一个

        4. base case:
        (1) i == j: true
        (2) i > j: false, 因为有可能是i+1和j-1, i和j同时向内缩, 会导致i > j

        5. 递归函数的返回类型
        ==> String

     */
    public static String longestPalindrome3(String s) {
        if (s == null) {
            return null;
        }

        return process(s, 0, s.length() - 1);
    }

    public static String process(String s, int i, int j) {
        // base case
        if (i == j) {
            return s.charAt(i) + "";
        } else if (i > j) {
            return "";
        }

        // 1. 只有当f(i+1, j-1)的最长回文长度等于i+1~j-1自身长度时, 说明f(i+1, j-1)的最长回文就是i+1~j-1
        // 并且此时的s[i]==s[j], 说明加上i和j的位置也是回文
        // 那么最长回文就是此时的i~j
        if (s.charAt(i) == s.charAt(j)
                && process(s, i + 1, j - 1).length() == ((j - 1) - (i + 1) + 1)) {
            return s.substring(i, j + 1);
        } else {
            // 2.1
            String s1 = process(s, i + 1, j);
            // 2.2
            String s2 = process(s, i, j - 1);

            return s1.length() > s2.length() ? s1 : s2;
        }
    }

    /*
    方法四: 动态规划 ==> 暴力递归改成动态规划
        1. 动态规划的本质是自底向上, 从base case往上推
        2. 记录每一个子问题的结果, 根据这个结果来推出大问题

        3. dp[i][j] ==> String[][]二维表

        4. 在dp填表过程中, 要求i<=j, 因此只需要考虑对角线上半部分

        5. 依赖关系(填表顺序)
        - process(s, i + 1, j - 1)
        - process(s, i + 1, j)
        - process(s, i, j - 1)
        ==> dp[i][j]依赖于它左边一格/下面一格/左下一格
        ==> 填表顺序: 一列一列从下往上填表

     */
    public static String longestPalindrome4(String s){
        if (s == null){
            return null;
        }

        String[][] dp = new String[s.length()][s.length()];

        // base case
//        if (i == j) {
//            return s.charAt(i) + "";
//        } else if (i > j) {
//            return "";
//        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == j){
                    dp[i][j] = s.charAt(i) + "";
                }else if (i > j){
                    dp[i][j] = "";
                }
            }
        }

        // 一列一列从下往上填表
        for (int j = 1; j < dp[0].length; j++) {
            for (int i = j - 1; i >= 0; i--) {
//                if (s.charAt(i) == s.charAt(j)
//                        && process(s, i + 1, j - 1).length() == ((j - 1) - (i + 1) + 1)) {
//                    return s.substring(i, j + 1);
                if (s.charAt(i) == s.charAt(j)
                    && dp[i + 1][j - 1].length() == ((j - 1) - (i + 1) + 1)) {
                    dp[i][j] = s.substring(i, j + 1);
                }else {
//                    // 2.1
//                    String s1 = process(s, i + 1, j);
//                    // 2.2
//                    String s2 = process(s, i, j - 1);
//                    return s1.length() > s2.length() ? s1 : s2;
                    String s1 = dp[i + 1][j];
                    String s2 = dp[i][j - 1];

                    dp[i][j] = s1.length() > s2.length() ? s1 : s2;
                }
            }
        }

        // return process(s, 0, s.length() - 1);
        return dp[0][s.length() - 1];
    }

    // dp优化版
    public static String longestPalindrome5(String s) {
        if (s == null) {
            return null;
        }

        char[] str = s.toCharArray();

        boolean[][] dp = new boolean[s.length()][s.length()];

        int start = 0;
        int len = 0;

        // base case
//        if (i == j) {
//            return s.charAt(i) + "";
//        } else if (i > j) {
//            return "";
//        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i >= j){
                    dp[i][j] = true;
                }
            }
        }

        // 一列一列从下往上填表
        for (int j = 1; j < dp[0].length; j++) {
            for (int i = j - 1; i >= 0; i--) {
//                if (s.charAt(i) == s.charAt(j)
//                        && process(s, i + 1, j - 1).length() == ((j - 1) - (i + 1) + 1)) {
//                    return s.substring(i, j + 1);
                if (str[i] == str[j] && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }

                if (dp[i][j] && (j - i + 1) > len){
                    len = j - i + 1;
                    start = i;
                }
            }
        }

        return s.substring(start, start + len);
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        String str2 = "cbbd";
        System.out.println(longestPalindrome1(str1)); // 1234321
        System.out.println(longestPalindrome2(str1)); // 1234321
        System.out.println(longestPalindrome3(str1)); // 1234321
        System.out.println(longestPalindrome4(str1)); // 1234321
        System.out.println(longestPalindrome1(str2)); // bab
        System.out.println(longestPalindrome2(str2)); // bab
        System.out.println(longestPalindrome3(str2)); // bab
        System.out.println(longestPalindrome4(str2)); // bab

    }
}
