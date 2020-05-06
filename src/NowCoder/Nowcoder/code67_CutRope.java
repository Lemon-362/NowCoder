package NowCoder.Nowcoder;

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
    public static int cutRope(int target) {
        int[] dp = new int[target + 1];
        dp[2] = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 3; i <= target; i++) {
            for (int j = 1; j <= i - 2; j++) {
                int num = Math.max(j * (i - j), j * dp[i - j]);
                if (stack.isEmpty()){
                    stack.push(num);
                }else if (num >= stack.peek()){
                    stack.push(num);
                }
            }
            dp[i] = stack.pop();
            stack.clear();
        }

        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(cutRope(8));
    }
}
