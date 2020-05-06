package NowCoder.HuaWei;

import java.util.Scanner;

/*
    质数因子：
        功能:输入一个正整数，按照从小到大的顺序输出它的所有质因子（如180的质因子为2 2 3 3 5 ）
             最后一个数后面也要有空格
 */
public class code06_ZhiShuYinZi {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            long num = sc.nextLong();
            for(int i = 2; i <= num; i++){
                while(num != i){
                    if(num % i == 0){
                        System.out.print(i + " ");
                        num /= i;
                    }else {
                        break;
                    }
                }
            }
            System.out.print(num + " ");
        }
    }
}
