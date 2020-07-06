package NowCoder.Nowcoder.exercise;

public class code44 {
    public static String reverseSentence(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }

        String[] s = str.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = s.length - 1; i >= 0; i--) {
            if (i == 0){
                sb.append(s[i]);
            }else {
                sb.append(s[i]).append(" ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "student. a am I";
        System.out.println(reverseSentence(str)); // I am a student.

    }
}
