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
        int index = newLen - 1;
        sb.setLength(newLen);

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
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == ' ') {
                res.append("%520");
            } else {
                res.append(stringBuffer.charAt(i));
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        String s = "We are family";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(s);

        System.out.println(replaceSpace(stringBuffer));
        System.out.println(replaceSpace2(stringBuffer));
    }
}
