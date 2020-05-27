package NowCoder.Nowcoder;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Stack;

/*
    剪绳子：
        给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），
        每段绳子的长度记为k[0],k[1],...,k[m]。
        请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
        例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 */
public class code67_CutRope {
    // F（N）：最大乘积
    // 一根绳子可以剪成 i 和 N-i，最大乘积为 i*(N-i)
    // 而对于 N-i 又可以继续剪
    // 因此 F(N) = max{i*(N-i), i*F(N-i)}
    // F(N-i)最多可以变为F(2)，因此i的范围 1 <= i <= N-2
    public static int cutRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;

        for (int i = 3; i < dp.length; i++) {
            for (int j = 1; j <= i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }

    // TODO 大数情况：答案需要取模 1e9+7（1000000007）
    /*
        贪心：
            对于长为n的绳子，剪出的长度可以是：
                1不可以，因为 1 * (n - 1) < n
                2可以
                3可以
                4不可以，因为可以拆成2+2
                5不可以，因为可以拆成2+3/3+2 ==> 2 * (n - 2) 和 3 * (n - 3) ==> 3 * (n - 3)更大
                6不可以，因为也可以拆成2和其他/3和其他
            因此，对于n>=5，最小的长度一定是3
        TODO 实际上就是每次剪成长度为3的，直到不能剪成3为止
     */
    public static int cutRope1(int n){
        if (n == 2){
            return 1;
        }
        if (n == 3){
            return 2;
        }
        if (n == 4){
            return 4;
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
