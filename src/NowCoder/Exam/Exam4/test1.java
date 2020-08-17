package NowCoder.Exam.Exam4;

import java.util.Scanner;

public class test1 {

    public static int minLength(String s) {
        if (s == null || s.length() < 1) {
            return -1;
        }

        char[] chas = s.toCharArray();
        int n = s.length();

        int[][] dp = new int[n][n];
        for (int j = 1; j < n; j++) {
            dp[j - 1][j] = (chas[j - 1] == chas[j]) ? 0 : 1;
            for (int i = j - 2; i >= 0; i--) {
                if (chas[i] == chas[j]) dp[i][j] = dp[i + 1][j - 1];
                else dp[i][j] = dp[i + 1][j - 1] + 1;
            }
        }

        return dp[0][s.length() - 1];
    }

    public static int minLength1(String s){

        char[] str = s.toCharArray();
        int i = 0;
        int j = str.length - 1;
        int res = 0;

        while (i < j){
            if (str[i] != str[j]){
                res++;
            }
            i++;
            j--;
        }
        return res;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        String s = "";
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                break;
            }
            s = scanner.next();
        }

        System.out.println(minLength1(s));

    }
}
