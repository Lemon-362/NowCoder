package NowCoder.LeetCode.Tags.Hot100.String.exercise;

public class code09 {
    public static int minOperations1(String word1, String word2){
        if (word1 == null || word2 == null){
            return 0;
        }

        return process(word1.toCharArray(), word2.toCharArray(), 0, 0);
    }

    public static int process(char[] str1, char[] str2, int i, int j){
        // base case
        if (i == str1.length){
            return str2.length - j;
        }
        if (j == str2.length){
            return str1.length - i;
        }

        // 1
        if (str1[i] == str2[j]){
            return process(str1, str2, i + 1, j + 1);
        } else {
            // 2.1
            int res1 = 1 + process(str1, str2, i, j + 1);
            // 2.2
            int res2 = 1 + process(str1, str2, i + 1, j);
            // 2.3
            int res3 = 1 + process(str1, str2, i + 1, j + 1);

            return Math.min(res1, Math.min(res2, res3));
        }
    }

    public static int minOperations2(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int len1 = str1.length;
        int len2 = str2.length;

        int[][] dp = new int[len1 + 1][len2 + 1];

        // base case
//        if (i == str1.length){
//            return str2.length - j;
//        }
//        if (j == str2.length){
//            return str1.length - i;
//        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[len1][j] = len2 - j;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][len2] = len1 - i;
        }

        // 1
//        if (str1[i] == str2[j]){
//            return process(str1, str2, i + 1, j + 1);
//        } else {
//            // 2.1
//            int res1 = 1 + process(str1, str2, i, j + 1);
//            // 2.2
//            int res2 = 1 + process(str1, str2, i + 1, j);
//            // 2.3
//            int res3 = 1 + process(str1, str2, i + 1, j + 1);
//
//            int res = Math.min(res1, Math.min(res2, res3));
//
//            return res;
//        }
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                // 1
                if (str1[i] == str2[j]){
                    dp[i][j] = dp[i + 1][j + 1];
                }else {
                    int res1 = 1 + dp[i][j + 1];
                    int res2 = 1 + dp[i + 1][j];
                    int res3 = 1 + dp[i + 1][j + 1];

                    dp[i][j] = Math.min(res1, Math.min(res2, res3));
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minOperations1(word1, word2)); // 3
        System.out.println(minOperations2(word1, word2)); // 3

        System.out.println("*************************");

        word1 = "intention";
        word2 = "execution";
        System.out.println(minOperations1(word1, word2)); // 5
        System.out.println(minOperations2(word1, word2)); // 5

        System.out.println("*************************");

        word1 = "dinitrophenylhydrazine";
        word2 = "acetylphenylhydrazine";
        System.out.println(minOperations2(word1, word2)); // 6
    }
}
