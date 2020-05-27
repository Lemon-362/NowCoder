package NowCoder.Nowcoder.exercise;

public class code67 {
    public static int cutRope(int n) {
        if (n < 1) {
            return 0;
        }

        int[] dp = new int[n + 1];
        dp[2] = 1;

        for (int N = 3; N < dp.length; N++) {
            for (int i = 1; i < N; i++) {
                dp[N] = Math.max(dp[N], Math.max(i * (N - i), i * dp[N - i]));
            }
        }

        return dp[n];
    }

    public static int cutRope1(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 4) {
            return 4;
        }

        long res = 1;
        while (n >= 5) {
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
