package NowCoder.basic_class.exercise.exercise02;

public class code39 {
    public static int Factorial(int n){
        // base case
        if (n == 1){
            return 1;
        }

        return n * Factorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(Factorial(5)); // 120
    }
}