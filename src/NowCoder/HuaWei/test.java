package NowCoder.HuaWei;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] s = str.split("~");
            System.out.println(s[0]);
            System.out.println(s[1]);
        }
    }
}
