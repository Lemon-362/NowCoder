package NowCoder.Nowcoder.exercise;

public class code02 {
    public static String replaceSpace(StringBuffer sb) {
        if (sb == null) {
            return null;
        }

        int spaceNum = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' '){
                spaceNum++;
            }
        }

        int oldLen = sb.length();
        int newLen = oldLen + 3 * spaceNum;
        sb.setLength(newLen);
        int index = newLen - 1;

        for (int i = oldLen - 1; i >= 0; i--) {
            if (sb.charAt(i) == ' '){
                sb.setCharAt(index--, '0');
                sb.setCharAt(index--, '2');
                sb.setCharAt(index--, '5');
                sb.setCharAt(index--, '%');
            }else {
                sb.setCharAt(index--, sb.charAt(i));
            }
        }

        return sb.toString();
    }

    public static String replaceSpace2(StringBuffer stringBuffer) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == ' '){
                sb.append("%520");
            }else {
                sb.append(stringBuffer.charAt(i));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "We are family";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s);

        System.out.println(replaceSpace(stringBuffer));
        System.out.println(replaceSpace2(stringBuffer));
    }
}
