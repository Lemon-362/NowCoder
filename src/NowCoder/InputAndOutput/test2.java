package InputAndOutput;

import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            long a = sc.nextInt();
            long b = sc.nextInt();
            long m = sc.nextInt();
            long x = sc.nextInt();
            String res = "";
            boolean flag = false;
            int len =0;
            while (len < 100) {
                x = (a * x + b) % m;
                res += x;
                for (int i = 1; i < res.length(); i++) {
                    if (res.length() % i == 0) {
                        if (judge(res.substring(0, i), res)) {
                            flag = true;
                        }
                    }
                }
                len = res.length();
                if (flag) {
                    System.out.println(res.length() / 2);
                    break;
                }
            }
        }
    }

    public static boolean judge(String sub, String s) {
        int len = sub.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != sub.charAt(i % len)) {
                return false;
            }
        }
        return true;
    }
}
