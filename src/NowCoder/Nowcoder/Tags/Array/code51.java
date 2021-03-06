package NowCoder.Nowcoder.Tags.Array;

public class code51 {
    public static int[] constructMultiplyArr(int[] A) {
        if (A == null || A.length < 1) {
            return null;
        }

        int[] B = new int[A.length];
        B[0] = 1;

        for (int i = 1; i < B.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }

        int[] C = new int[B.length];
        C[C.length - 1] = 1;
        for (int i = C.length - 2; i >= 0; i--) {
            C[i] = C[i + 1] * A[i + 1];
            B[i] = C[i] * B[i];
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
