package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

/*
238. 除自身以外数组的乘积: 剑指Offer code51_ConstructMultiplyArr
    构建乘积数组：
        给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
        其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
        不能使用除法。

 */
public class code09 {

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
        int[] A = {1, 2, 3, 4};

        int[] B = constructMultiplyArr(A);
        for (int i = 0; i < B.length; i++) {
            System.out.print(B[i] + " "); // 24 12 8 6
        }
    }
}
