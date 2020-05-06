package NowCoder.HuaWei;

import java.util.Scanner;

/*
    浮点数四舍五入：
        写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。
        如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
 */
public class code07_4She5Ru {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            float num = sc.nextFloat();
            String str = String.valueOf(num);
            String[] s = str.split("\\."); // 转义字符
            int s1 = Integer.parseInt(s[0]);
            int s2 = Integer.parseInt(String.valueOf(s[1].charAt(0)));
            int a = 0;
            if(s2 >= 5){
                a = 1;
            }
            System.out.println(s1 + a);
        }
    }
}
