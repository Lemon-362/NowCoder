package NowCoder.Nowcoder.exercise;

public class code07 {
    public static int fibonacci(int n) {
        return process(n);
    }

    public static int process(int n){
        // base case
        if (n <= 1){
            return n;
        }else {
            return process(n - 1) + process(n - 2);
        }
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(6)); // 8
    }
}
