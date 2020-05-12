package NowCoder.Nowcoder.exercise;

public class code02 {
    public static String replaceSpace(StringBuffer sb) {
        if (sb == null) {
            return null;
        }

        int spaceNum = 0;

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                spaceNum++;
            }
        }

        int oldLen = sb.length();
        int newLen = oldLen + 2 * spaceNum;
        sb.setLength(newLen);
        int newIndex = newLen - 1;

        for (int oldIndex = oldLen - 1; oldIndex >= 0; oldIndex--) {
            if (sb.charAt(oldIndex) == ' ') {
                sb.setCharAt(newIndex--, '0');
                sb.setCharAt(newIndex--, '2');
                sb.setCharAt(newIndex--, '%');
            } else {
                sb.setCharAt(newIndex--, sb.charAt(oldIndex));
            }
        }

        return sb.toString();
    }

    public static String replaceSpace2(StringBuffer stringBuffer) {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (stringBuffer.charAt(i) == ' ') {
                res.append("%20");
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