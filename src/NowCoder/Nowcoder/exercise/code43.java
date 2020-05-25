package NowCoder.Nowcoder.exercise;

public class code43 {
    public static String leftRotateString(String str, int n){
        if (str == null || str.length() < 1 || n < 0){
            return null;
        }

        int len = str.length();

        n = n % len;

        return (str + str).substring(n, n + len);
    }

    public static void main(String[] args) {
        String res = leftRotateString("lrloseumgh", 6);
        System.out.println(res); // umghlrlose
    }
}
