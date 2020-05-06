package NowCoder.InputAndOutput;

/*
计算a+b：
    输入：包括两个正整数a,b(1 <= a, b <= 10^9),输入数据包括多组。
    输出：a+b的结果

输入：
    1 5
    10 20
输出：
    6
    30
 */

import java.util.*;

public class code01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(a + b);
        }
    }
}

