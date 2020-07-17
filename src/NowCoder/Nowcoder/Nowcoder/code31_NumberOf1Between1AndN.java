package NowCoder.Nowcoder.Nowcoder;

/*
    从1到n中1出现的次数

思路：递归
    f(n): 表示1--n中1出现的次数
    n可以划分为 最高位high 和 其他位last 以及 power

考察具体情况:
    1. 1234 (最高位是1)
        high=1, last=234, power=1000
        那么f(1234)可以拆分成 1-999, 1000-1234 中1的个数
        (1) 对于1-999: f(power-1)
        (2) 对于1000-1234: 拆分成 最高位 和 其他位 中1的个数
            a. 最高位: 此时最高位为1, 那么1000-1234中1的个数就应该是 1000, 1001, ..., 1234 所有值的个数
                      也就等于 last+1
            b. 其他位: _000-_234 中1的个数 <==> 1-234中1的个数 ==> f(last)
    综上: f(1234)=f(power-1)+last+1+f(last)

    2. 334 (最高位不是1)
        high=3, last=34, power=100
        那么f(334)可以拆分成 1-99, 100-199, 200-299, 300-334 中1的个数
        (1) 对于1-99: f(power-1)
        (2) 对于100-199: 拆分成 最高位 和 其他位 中1的个数
            a. 最高位: 此时最高位为1, 那么100-199中1的个数就应该是 100, 101, ..., 199 所有值的个数
                      也就等于 power
            b. 其他位: _00-_99 中1的个数 <==> 1-99中1的个数 ==> f(power-1)
        (3) 对于200-299: 由于最高位不是1, 所以只需要计算其他位上1的个数 <==> 1-99中1的个数 ==> f(power-1)
        (4) 对于300-334: 由于最高位不是1, 所以只需要计算其他位上1的个数 <==> 1-34中1的个数 ==> f(last)
    综上: f(334)=f(power-1)+power+f(power-1)+f(power-1)+f(last)=3*f(power-1)+power+f(last)
                   =high*f(power-1)+power+f(last)

 */
public class code31_NumberOf1Between1AndN {
    public static int count(int n){
        if (n <= 0){
            return 0;
        }

        return f(n);
    }

    public static int f(int n){
        // base case
        if (n <= 0){
            return 0;
        }

        // 获得high，power，last
        String s = String.valueOf(n);
        int high = Integer.parseInt(String.valueOf(s.charAt(0)));
        int power = (int) Math.pow(10, s.length() - 1);
        int last = n - high * power;

        if (high == 1){
            return f(power - 1) + last + 1 + f(last);
        }else {
            return high * f(power - 1) + power + f(last);
        }
    }

    public static void main(String[] args) {
        System.out.println(count(12));
        System.out.println(count(13));
    }
}
