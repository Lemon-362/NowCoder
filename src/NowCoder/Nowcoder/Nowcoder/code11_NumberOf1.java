package NowCoder.Nowcoder.Nowcoder;

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

    /**
     * 方法一:
     *  一个数 & 1: 因为1只有最后一位是1,其他位是0, 所以&后其他位一定是0
     *             只要看最后一位(也就是结果)是否为1, 就可以判断该数的最后一位是否为1
     *         ==> 不断重复, 每次看最后一位是否为1, 然后右移一位
     *
     * 方法二:
     *  一个不为0的数, 其二进制至少包含一个1
     *  假设最右边的1在第m位, 它之后全为0,
     *  此时, 该数 - 1: 第m位最右边的1变为0, 它之后的0变为1, 它之前的不变
     *             ==> 第m位及其之后取反, 之前的不变
     *  那么, 将 该数 & (该数 - 1): 第m位之前的不变, 第m位及其之后的变为0
     *            ==> 也就找到了最右边的1
     *            ==> 不断重复, 就可以从右往左找到所有的1
     */
    public static int count1(int n){
        int count = 0;

        while (n != 0){
            if ((n & 1) == 1){
                count++;
            }

            n = n >> 1;
        }

        return count;
    }

    public static int count2(int n){
        int count = 0;

        while (n != 0){
            count++;
            n = n & (n - 1);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(method(33211)); // 8
        System.out.println(method(5)); // 2
        System.out.println(method2(33211)); // 8

        System.out.println("**********************");

        System.out.println(count1(33211));
        System.out.println(count2(33211));
    }
}
