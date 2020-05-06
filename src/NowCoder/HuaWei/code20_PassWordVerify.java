package NowCoder.HuaWei;

import java.util.Scanner;

/*
    验证密码是否合格：
        1.长度超过8位
        2.包括大小写字母.数字.其它符号,以上四种至少三种
        3.不能有相同长度超2的子串重复
        说明:长度超过2的子串
 */
public class code20_PassWordVerify {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            if(VerifyLen(s) && VerifyCategory(s) && VerifySameSubString(s)){
                System.out.println("OK");
            }else{
                System.out.println("NG");
            }
        }
    }

    // 长度是否超过8位
    public static boolean VerifyLen(String s){
        return s != null && s.length() > 8;
    }

    // 是否包含3种
    public static boolean VerifyCategory(String s){
        int num1 = 0; // 大写字母
        int num2 = 0; // 小写字母
        int num3 = 0; // 数字
        int num4 = 0; // 其他符号
        for(int i = 0; i < s.length(); i++){
            char a = s.charAt(i);
            if(a >= 65 && a <= 90){
                num1 = 1;
            }else if(a >= 97 && a <= 122){
                num2 = 1;
            }else if(a >= 48 && a <= 57){
                num3 = 1;
            }else {
                num4 = 1;
            }
        }
        return (num1 + num2 + num3 + num4) >= 3;
    }

    // 是否有相同长度的子串，不能含有长度超过2的相同子串，也就是说如果有长度为3以上的相同子串都不行
    public static boolean VerifySameSubString(String s){
        for(int i = 0; i < s.length() - 2; i++){
            String s1 = s.substring(i, i + 3);
            // 只需要判断后续的子串是否含有该子串
            if(s.substring(i + 3).contains(s1)){
                return false;
            }
        }
        return true;
    }
}
