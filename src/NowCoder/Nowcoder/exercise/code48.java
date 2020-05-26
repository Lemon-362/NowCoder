package NowCoder.Nowcoder.exercise;

public class code48 {
    public static int addProblem(int a, int b){

        while (b != 0){

            int B = (a & b) << 1;

            a ^= b;

            b = B;
        }

        return a;
    }

    public static void main(String[] args) {
        System.out.println(addProblem(5, 6)); // 11
    }
}
