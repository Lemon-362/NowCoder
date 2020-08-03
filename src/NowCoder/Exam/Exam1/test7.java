package NowCoder.Exam.Exam1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test7 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();

        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        System.out.println( m.replaceAll("").trim());
    }

}
