package NowCoder.Nowcoder.exercise;

public class code11 {
    public static int numberOf1(int n) {
        int count = 0;

        for (int i = 31; i >= 0; i--) {
            int path = (n >> i) & 1;

            if (path == 1){
                count++;
            }
        }

        return count;
    }

    public static int count1(int n) {
        int count = 0;

        while (n > 0){
            if ((n & 1) == 1){
                count++;
            }

            n = n >> 1;
        }

        return count;
    }

    public static int count2(int n) {
        int count = 0;

        while (n > 0){
            count++;

            n = (n - 1) & n;
        }

        return count;
    }

        /*
    variable-precision SWAR算法:

        0x55555555 = 0101 0101 0101 0101 0101 0101 0101 0101
        0x33333333 = 0011 0011 0011 0011 0011 0011 0011 0011

     */

    public static int count3(int x) {

        // 计算每两位为一组的二进制形式包含1的个数
        x = (x & 0x55555555) + ((x >> 1) & 0x55555555);
        // 计算每四位为一组的二进制形式包含1的个数
        x = (x & 0x33333333) + ((x >> 2) & 0x33333333);
        // 计算每八位为一组的二进制形式包含1的个数
        x = (x & 0x0f0f0f0f) + ((x >> 4) & 0x0f0f0f0f);
        // 第三步已经每8个一组计算出来了, 只需要计算A+B+C+D就是最终结果
        // 为了快速计算, 将x分别左移0位,8位,16位,24位然后相加的结果 ==> x * (0x01010101)
        // 而结果是在高8位相加得到的, 所以最终需要右移24位, 转移到低8位
        x = (x * (0x01010101) >> 24);

        return x;

    }

    public static void main(String[] args) {
        System.out.println(numberOf1(33211)); // 8
        System.out.println(count1(33211));
        System.out.println(count2(33211));
        System.out.println(count3(33211));
    }
}
