package NowCoder.Nowcoder;

/*
    计算二进制中1的个数：
        输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class code11_NumberOf1 {
    // 将整数 - 1，那么处在最右边的1变为0，最右边的1后面的所有0取反
    // 也就相当于：一个数 - 1 ==》 把最右边的1开始向后所有位取反
    // 那么，将原来的数 和 -1后的数 做与运算，那么就可以得到第一个1，也就是一个1
    // 一直重复，直到n==0
    public static int method(int n) {
        int count = 0;
        while (n != 0) {
            ++count;
            n = n & (n - 1);
        }
        return count;
    }

    public static int method2(int n){
        int res = 0;

        // TODO 前缀树的思路
        for (int i = 31; i >= 0; i--) {
            int path = (n >> i) & 1;
            if (path == 1){
                res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(method(33211)); // 8
        System.out.println(method(5)); // 2
        System.out.println(method2(33211)); // 8
    }
}
