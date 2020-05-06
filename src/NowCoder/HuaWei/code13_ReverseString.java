package NowCoder.HuaWei;

import java.util.Scanner;

/*
    反转字符串：
        写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）
        输入：abcd     输出：dcba
 */
public class code13_ReverseString {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            String res = "";
            for(int i = s.length(); i >= 1; i--){
                res += s.substring(i - 1, i);
            }
            System.out.println(res);
        }
    }
}
