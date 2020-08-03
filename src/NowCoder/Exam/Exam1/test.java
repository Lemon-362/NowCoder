package NowCoder.Exam.Exam1;

import java.util.Arrays;
import java.util.Scanner;

/*
1.
    非递减数组
    每个人选择数组中的一个数字，将其第一次出现的位置及其左边全部删除
    当某人操作完后数组为空时，此人获胜

    111222，选择2，删除后得到22

2.
    n层柜子
    每一层都有商品，且都有价格
    选m件
    每次挑选，只能选择某一层两端的物品
    获得的最大值

 */

public class test {

    static int n, m;
    static int[] s = new int[110];
    static int[][] sum = new int[110][110];
    static int[][] dem = new int[110][110];
    static int[][] dp = new int[110][11000];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        m = in.nextInt();

        for (int i = 1; i <= n; i++) {
            s[i] = in.nextInt();
            for (int j = 1; j <= s[i]; j++) {
                int x = in.nextInt();
                sum[i][j] = sum[i][j - 1] + x;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= s[i]; j++) {

                int t = 0;
                for (int left = 0; left <= j; left++) {
                    int right = j - left;
                    int temp = sum[i][left] + sum[i][s[i]] - sum[i][s[i] - right];
                    t = Math.max(t, temp);
                }

                dem[i][j] = t;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 0; j--) {
                for (int k = 0; k <= s[i] && k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k] + dem[i][k]);
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
