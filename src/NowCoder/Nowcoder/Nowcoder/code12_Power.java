package NowCoder.Nowcoder.Nowcoder;

/*
    给定一个double类型的浮点数base和int类型的整数exponent。
    求base的exponent次方。

    x^n = x^(n/2) * x^(n/2), n为偶数
          x^((n-1)/2) * x^((n-1)/2) * x, n为奇数
 */
public class code12_Power {
    // Power(x, n) = x^n = (x^2)^(n/2)，讨论n的取值
    // 可参考n！的思路
    public static double Power(double x, int n){
        // 防止n=-2147483648时，在变成正数后越界
        long N = n;
        if(N < 0){
            return 1 / myPower(x, -N);
        }else{
            return myPower(x, N);
//            return myPower02(x, N);
        }
    }

    // 递归法
    private static double myPower(double x, long n){
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return x;
        }
        double res;
        if(n % 2 == 0){
            res = myPower(x, n >> 1);
            return res * res;
        }else{
            res = myPower(x, (n - 1) >> 1); // n / 2 整除
            return x * res * res;
        }
    }

    // 递推法
    public static double myPower02(double base, long n){
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return base;
        }
        double res = 1;
        for(int i = 1; i <= n; i++){
            res *= base;
        }
        return res;
    }

    // 绝对正确的方法
    public static double Power02(double x, int n){
        return Math.pow(x, n);
    }

    public static void main(String[] args) {
        System.out.println(Power(5.2, 5));
        System.out.println(Power02(5.2, 5));
    }
}
