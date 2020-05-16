package NowCoder.basic_class.exercise.exercise02;

public class code43 {
    public static int CowNumber(int n){
        // base cae
        if (n <= 3){
            return n;
        } else {
            return CowNumber(n - 1) + CowNumber(n - 3);
        }
    }

    public static int CowNumber2(int n){
        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 3];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(CowNumber(20)); // 1873
        System.out.println(CowNumber2(20));
    }
}
