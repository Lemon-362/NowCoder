package NowCoder.Nowcoder.exercise;

public class code31 {
    public static int NumberOf1Between1AndN(int n) {
        if (n <= 0) {
            return 0;
        }

        return f(n);
    }

    public static int f(int n){
        if (n <= 0){
            return 0;
        }

        char[] s = String.valueOf(n).toCharArray();
        int high = Integer.parseInt(String.valueOf(s[0]));
        int power = (int)(Math.pow(10, s.length - 1));
        int last = n - high * power;

        if (high == 1){
            return f(power - 1) + f(last) + last + 1;
        }else {
            return high * f(power - 1) + power + f(last);
        }
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1Between1AndN(12)); // 5
        System.out.println(NumberOf1Between1AndN(13)); // 6
    }
}
