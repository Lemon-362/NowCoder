package NowCoder.Nowcoder.exercise;

public class code47 {
    public static int sumProblem(int n){
        boolean flag = n > 1 && (n += process(n - 1)) > 1;

        return n;
    }

    public static int process(int n){
        // base case
        if (n == 1){
            return 1;
        }

        return n + process(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(process(9)); // 45
        System.out.println(sumProblem(9));
    }
}
