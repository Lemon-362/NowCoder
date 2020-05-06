package NowCoder.HuaWei;

import java.util.Scanner;

/*
    坐标移动
 */
public class code17_ZuoBiao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] s = sc.next().split(";");
            int i = 0;
            int j = 0;
            for (int k = 0; k < s.length; k++) {
                if (s[k].length() == 3) {
                    char a = s[k].charAt(0);
                    char b = s[k].charAt(1);
                    char c = s[k].charAt(2);
                    if ((b >= 48 && b <= 57) && (c >= 48 && c <= 57)) {
                        int num = Integer.parseInt(s[k].substring(1));
                        if (a == 'A') {
                            i -= num;
                        } else if (a == 'D') {
                            i += num;
                        } else if (a == 'W') {
                            j += num;
                        } else if (a == 'S') {
                            j -= num;
                        }
                    }
                } else if (s[k].length() == 2) {
                    char a = s[k].charAt(0);
                    char b = s[k].charAt(1);
                    if ((b >= 48 && b <= 57)) {
                        int num = Integer.parseInt(s[k].substring(1));
                        if (a == 'A') {
                            i -= num;
                        } else if (a == 'D') {
                            i += num;
                        } else if (a == 'W') {
                            j += num;
                        } else if (a == 'S') {
                            j -= num;
                        }
                    }
                }
            }
            System.out.println(i + "," + j);
        }
    }
}
