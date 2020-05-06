package NowCoder.HuaWei;

import java.util.Scanner;

public class code15_10To2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            String s = Integer.toBinaryString(num);
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.substring(i, i + 1).equals("1")) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}

