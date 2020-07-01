package NowCoder.Nowcoder.exercise;

public class code67 {
    public static int cutRope(int n) {
        if (n <= 1){
            return 0;
        }else if (n <= 3){
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i / 2; j++) {
                max = Math.max(max, dp[j] * dp[i - j]);
            }
            dp[i] = max;
        }

        return dp[n];
    }

    public static int cutRope1(int n) {
        if (n <= 1){
            return 0;
        }else if (n <= 4){
            return n;
        }

        long res = 1;

        while (n >= 5){
            res *= 3;

            res = res % 1000000007;

            n -= 3;
        }

        res *= n;

        return (int) (res % 1000000007);
    }

    public static void main(String[] args) {
        System.out.println(cutRope(8)); // 18
        System.out.println(cutRope(10)); // 36

        System.out.println(cutRope1(120)); // 953271190

    }
}
