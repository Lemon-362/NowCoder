package NowCoder.Nowcoder.Nowcoder;

/*
    构建乘积数组：
        给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
        其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
        不能使用除法。

 */
public class code51_ConstructMultiplyArr {
    /*
        先假设n=5，从特殊情况中找规律：
            B[0] = A[1] * A[2] * A[3] * A[4]
            B[1] = A[0] * A[2] * A[3] * A[4]
            B[2] = A[0] * A[1] * A[3] * A[4]
            B[3] = A[0] * A[1] * A[2] * A[4]
            B[4] = A[0] * A[1] * A[2] * A[3]
        可以发现每次都是缺少对角线元素，所以可以按对角线划分成上下两部分：
            1. 下半部：
                B[0] = 1
                B[1] = A[0]
                B[2] = A[0] * A[1]
                B[3] = A[0] * A[1] * A[2]
                B[4] = A[0] * A[1] * A[2] * A[3]
              可以发现是逐渐递增的，所以可以推出递推公式：
                B[0] = 1, i == 0
              i: 1 -- 4
                B[i] = B[i - 1] * A[i - 1], i >= 1
            2. 上半部：
                B[0] = A[1] * A[2] * A[3] * A[4]
                B[1] = A[2] * A[3] * A[4]
                B[2] = A[3] * A[4]
                B[3] = A[4]
                B[4] = 1
              可以发现是逐渐递减的，由于不能使用除法，所以考虑从下往上推，递推公式：
                B[4] = 1, i == 4
              i: 3 -- 0
                res *= A[i + 1]
            3. 两者相乘：B[i] *= res
     */
    public static int[] constructMultiplyArr(int[] A){
        if (A == null || A.length < 1){
            return null;
        }

        int[] B = new int[A.length];

        // 下半部
        B[0] = 1;
        for (int i = 1; i < B.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }

        // 上半部
        int res = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            res *= A[i + 1];
            B[i] *= res;
        }

        return B;

    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};

        int[] B = constructMultiplyArr(A);
        for (int i = 0; i < B.length; i++) {
            System.out.print(B[i] + " "); // 120 60 40 30 24
        }
    }

}
