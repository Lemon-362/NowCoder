package NowCoder.Nowcoder.exercise;

public class code12 {
    public static double myPower(double x, int n) {
        if (n < 0){
            return 1 / process(x, -n);
        }else {
            return process(x, n);
        }
    }

    public static double process(double x, int n){
        if (n == 0){
            return 1;
        }else if (n == 1){
            return x;
        }

        double res = process(x, n / 2);

        if (n % 2 == 0){
            return res * res;
        }else {
            return res * res * x;
        }
    }

    public static void main(String[] args) {
        System.out.println(myPower(5.2, 5)); // 3802.040320000001
    }
}
