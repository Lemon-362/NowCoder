package NowCoder.HuaWei;

import java.util.Scanner;

/*
    逆序输出整数的字符串形式：
        输入一个整数，将这个整数以字符串的形式逆序输出
        程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
        输入：1516000      输出：0006151
 */
public class code11_ReverseNumToString {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt();
            char[] s = String.valueOf(num).toCharArray();
            StringBuilder res = new StringBuilder();
            for(int i = s.length - 1; i >= 0; i--){
                res.append(s[i]);
            }
            System.out.println(res.toString());
        }
    }
}
