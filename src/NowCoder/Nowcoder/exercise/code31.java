package NowCoder.Nowcoder.exercise;

public class code31 {
    public static int NumberOf1Between1AndN(int n){
        if (n <= 0){
            return 0;
        }

        return f(n);
    }

    public static int f(int n){
        // base case
        if (n <= 0){
            return 0;
        }

        String s = String.valueOf(n);
        int high = Integer.parseInt(String.valueOf(s.charAt(0)));
        int power = (int) (Math.pow(10, s.length() - 1));
        int last = n - high * power;

        if (high == 1){
            return f(power - 1) + last + 1 + f(last);
        }else {
            return high * f(power - 1) + f(last) + power;
        }
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1Between1AndN(12)); // 5
        System.out.println(NumberOf1Between1AndN(13)); // 6
    }
}
