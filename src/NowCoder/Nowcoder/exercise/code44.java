package NowCoder.Nowcoder.exercise;

public class code44 {
    public static String reverseSentence(String str){
        if (str == null || str.length() < 1){
            return null;
        }

        String[] s = str.split(" ");

        StringBuffer sb = new StringBuffer();

        for (int i = s.length - 1; i >= 0; i--) {
            sb.append(s[i]);
            if (i > 1){
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "student. a am I";
        System.out.println(reverseSentence(str)); // I am a student.

    }
}
