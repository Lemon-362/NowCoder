package NowCoder.Nowcoder.exercise;

public class code46 {
    public static int lastRemaining(int n, int m){
        if (n < 1 || m < 1){
            return -1;
        }

        int res = 0;

        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3)); // 3

        System.out.println(lastRemaining(10, 17)); // 2
    }
}
