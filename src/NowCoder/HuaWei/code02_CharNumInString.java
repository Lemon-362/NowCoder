package NowCoder.HuaWei;

import java.util.Scanner;

/*
    计算一个字符在一字符串中出现的个数：
        写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。
        不区分大小写。
        输入：ABCDEF
              A
        输出：1
 */
public class code02_CharNumInString {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().toLowerCase(); // 全小写的字符串
        Character c = sc.nextLine().toLowerCase().charAt(0); // 小写的字符

        int count = 0;
        // 遍历字符串
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == c){
                count++;
            }
        }
        System.out.println(count);
    }
}
