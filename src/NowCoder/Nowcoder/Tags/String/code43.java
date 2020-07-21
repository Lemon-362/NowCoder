package NowCoder.Nowcoder.Tags.String;

public class code43 {
    public static String leftRotateString(String str, int n){
        if (str == null || str.length() < 1 || n < 0){
            return null;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = n; i < n + str.length(); i++) {
            sb.append(str.charAt(i % str.length()));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String res = leftRotateString("lrloseumgh", 6);
        System.out.println(res); // umghlrlose
    }
}
