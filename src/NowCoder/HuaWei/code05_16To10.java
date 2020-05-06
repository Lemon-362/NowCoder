package NowCoder.HuaWei;

import java.util.Scanner;

/*
    16进制 转 10进制
        输入 0xA  输出 10
 */
public class code05_16To10 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.next();
            System.out.println(Integer.valueOf(str.substring(2), 16).toString());
        }
    }
}
