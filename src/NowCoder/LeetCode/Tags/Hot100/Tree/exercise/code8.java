package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

public class code8 {
    public static int numOfBST(int N){
        if (N <= 0){
            return 0;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            int num = 0;
            for (int j = 1; j <= i; j++) {
                num += (dp[j - 1] * dp[i - j]);
            }
            dp[i] = num;
        }

        return dp[N];
    }

    public static void main(String[] args) {
        System.out.println(numOfBST(3)); // 5
    }

}
