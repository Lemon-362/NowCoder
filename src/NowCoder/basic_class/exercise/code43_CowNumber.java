package NowCoder.basic_class.exercise;

/*
	母牛每年生一只母牛,新出生的母牛三年后也能每年生一只母牛,假设不会死亡。
	求N年后，母牛的数量。
	TODO 类似于 斐波那契数列 剑指Offer code07 08
 */

public class code43_CowNumber {
    public static int CowNumber(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n == 2 || n == 3){
            return n;
        }
        return CowNumber(n-1) + CowNumber(n-3);
    }

    public static int method(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n == 2 || n == 3){
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1;
        int tmp2;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;

            res += prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(CowNumber(n)); // 1873
        System.out.println(method(n));
    }
}
