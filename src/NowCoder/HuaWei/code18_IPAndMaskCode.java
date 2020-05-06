package NowCoder.HuaWei;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
    所有的IP地址划分为 A,B,C,D,E五类
        A类地址1.0.0.0~126.255.255.255;
        B类地址128.0.0.0~191.255.255.255;
        C类地址192.0.0.0~223.255.255.255;
        D类地址224.0.0.0~239.255.255.255；
        E类地址240.0.0.0~255.255.255.255
    私网IP范围是：
        10.0.0.0～10.255.255.255
        172.16.0.0～172.31.255.255
        192.168.0.0～192.168.255.255
    子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
    注意二进制下全是1或者全是0均为非法
    注意：
    1. 类似于【0.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时可以忽略
    2. 私有IP地址和A,B,C,D,E类地址是不冲突的

    输入：
    10.70.44.68~255.254.255.0
    1.0.0.1~255.0.0.0
    192.168.0.2~255.255.255.0
    19..0.~255.255.255.0
    输出：
    1 0 1 0 0 2 1
 */
public class code18_IPAndMaskCode {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int isA = 0;
        int isB = 0;
        int isC = 0;
        int isD = 0;
        int isE = 0;
        int isError = 0;
        int isPrivate = 0;
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] s = str.split("~");

            String str1 = s[0]; // ip
            String str2 = s[1]; // 掩码

            // 先判断是否是错误IP/掩码地址
//            if(!IPVerify(str1) || !MaskCodeVerify(str2)){
//                isError++;
//            }



        }
    }

//    public static boolean IPVerify(String s){
//        if (s == null){
//            return false;
//        }
//
//        Pattern pattern = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$");
//        Matcher matcher = pattern.matcher(s);
//
//        if (matcher.matches()){ // 和正则表达式匹配，每一位置上都是多位数
//            String[] s1 = s.split("\\."); // 划分
//
//        }
//    }
//
//    public static boolean MaskCodeVerify(String s){
//
//    }
}
