package NowCoder.basic_class.exercise01.RecurAndDP;

public class PrintAllSubSequences {
    public static void process(char[] s, int i, String res) {
        if (i == s.length) {
            System.out.println(res);
            return;
        }
        process(s, i + 1, res);
        process(s, i + 1, res + s[i]);
    }

    public static void method(String str) {
        char[] s = str.toCharArray();
        process(s, 0, "");
    }

    public static void main(String[] args) {
        String str = "abc";
        method(str);
    }
}
