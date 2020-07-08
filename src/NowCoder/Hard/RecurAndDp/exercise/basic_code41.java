package NowCoder.Hard.RecurAndDp.exercise;

public class basic_code41 {
    public static void process(char[] s, int index, String res){
        // base case
        if (index == s.length){
            System.out.println(res);
            return;
        }

        process(s, index + 1, res);

        process(s, index + 1, res + s[index]);
    }

    public static void printAllSubSequence(String str){
        if (str == null){
            return;
        }

        process(str.toCharArray(), 0, "");
    }

    public static void main(String[] args) {
        printAllSubSequence("abc"); // null c b bc a ac ab abc
    }
}
