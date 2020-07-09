package NowCoder.Hard.exercise;

public class No_code67 {
    public static int cutRope(int n) {
        if (n <= 1){
            return 0;
        }else if (n == 2){
            return 1;
        }else if (n == 3){
            return 2;
        }else if (n == 4){
            return 4;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;

        for (int i = 5; i < dp.length; i++) {
            int num = 0;
            for (int j = 1; j <= i / 2; j++) {
                num = Math.max(num, dp[j] * dp[i - j]);
            }
            dp[i] = num;
        }

        return dp[n];
    }

    public static int cutRope1(int n) {
        if (n <= 1){
            return 0;
        }else if (n == 2){
            return 1;
        }else if (n == 3){
            return 2;
        }else if (n == 4){
            return 4;
        }

        long res = 1;

        while (n >= 5){
            res *= 3;

            res = res % 1000000007;

            n = n - 3;
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
