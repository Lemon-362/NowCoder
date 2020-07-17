package NowCoder.Nowcoder.Nowcoder;

/*
    求按从小到大的顺序的第N个丑数:
丑数: 只包含质因子2、3和5的数
例如：6，8  但是14不是，因为它包含质因子7
习惯上将1作为第一个丑数
 */
public class code33_GetUglyNumber {
    public static int[] getUgly(int n) {
        if (n <= 0) {
            return null;
        }

        // 对于某个丑数，他一定是之前的丑数*2或*3或*5得到的
        // 那么只需要找到在*2或*3或*5中最小的那个数，且那个丑数要后移，不能再用了
        int[] res = new int[n];
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        res[0] = 1;

        for (int i = 1; i < n; i++) {
            // 找*2或*3或*5中最小的那个数
            res[i] = Math.min(res[p2] * 2, Math.min(res[p3] * 3, res[p5] * 5));

            // 那个丑数要后移，不能再用了
            if (res[p2] * 2 == res[i]){
                p2++;
            }
            if (res[p3] * 3 == res[i]){
                p3++;
            }
            if (res[p5] * 5 == res[i]){
                p5++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] res = getUgly(16);
        for (int i : res) {
            System.out.print(i + " "); // 1 2 3 4 5 6 8 9 10 12 15 16 18 20 24 25
        }
    }
}
