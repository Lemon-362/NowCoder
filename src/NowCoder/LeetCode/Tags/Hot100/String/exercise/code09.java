package NowCoder.LeetCode.Tags.Hot100.String.exercise;

public class code09 {
    public static int minOperations1(String word1, String word2){
        if (word1 == null || word2 == null){
            return 0;
        }

        return process(word1.toCharArray(), word2.toCharArray(), 0, 0);
    }

    public static int process(char[] s1, char[] s2, int i, int j){
        // base case
        if (i == s1.length && j == s2.length){
            return 0;
        }else if (i == s1.length){
            return s2.length - j;
        }else if (j == s2.length){
            return s1.length - i;
        }

        if (s1[i] == s2[j]){
            return process(s1, s2, i + 1, j + 1);
        }else {
            // 1
            int res1 = 1 + process(s1, s2, i + 1, j);
            // 2
            int res2 = 1 + process(s1, s2, i + 1, j + 1);
            // 3
            int res3 = 1 + process(s1, s2, i, j + 1);

            return Math.min(res1, Math.min(res2, res3));
        }
    }

    public static int minOperations2(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int[][] dp = new int[s1.length + 1][s2.length + 1];

        // base case
//        if (i == s1.length && j == s2.length){
//            return 0;
//        }else if (i == s1.length){
//            return s2.length - j;
//        }else if (j == s2.length){
//            return s1.length - i;
//        }
        dp[s1.length][s2.length] = 0;

        for (int j = 0; j < dp[0].length; j++) {
            dp[s1.length][j] = s2.length - j;
        }

        for (int i = 0; i < dp.length; i++) {
            dp[i][s2.length] = s1.length - i;
        }

        for (int i = s1.length - 1; i >= 0; i--) {
            for (int j = s2.length - 1; j >= 0; j--) {
                if (s1[i] == s2[j]){
                    dp[i][j] = dp[i + 1][j + 1];
                }else {
                    int res1 = dp[i + 1][j] + 1;
                    int res2 = dp[i + 1][j + 1] + 1;
                    int res3 = dp[i][j + 1] + 1;

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
