package NowCoder.Nowcoder.exercise;

public class code07 {
    public static int process(int N){
        // base case
        if (N < 2){
            return N;
        }else {
            return process(N - 1) + process(N - 2);
        }
    }

    public static int fibonacci(int N){
        return process(N);
    }

    public static int fibonacci1(int N){
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[N];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(6)); // 8
        System.out.println(fibonacci1(6));
    }
}
