package NowCoder.HuaWei;

import java.util.Scanner;

/*
    汽水瓶：
        有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”
        答案是5瓶，方法如下：
        先用9个空瓶子换3瓶汽水，喝掉3瓶满的，
        喝完以后4个空瓶子，用3个再换一瓶，
        喝掉这瓶满的，这时候剩2个空瓶子。
        然后你让老板先借给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。
        如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？

        输入：
            3
            10
            81
            0
        输出：
            1
            5
            40
 */
public class code22_DrinkAndEmpty {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt();
            int drink = 0;
            int empty = 0;
            int count = 0;
            if(num >= 1 && num <= 100){
                if(num == 1){
                    System.out.println(0);
                    break;
                }
                if(num == 2){
                    System.out.println(1);
                    break;
                }
                while(num > 2){
                    drink = num / 3;
                    empty = num % 3;
                    count += drink;
                    num = empty + drink;
                    if(num == 2){
                        count++;
                    }
                }
                System.out.println(count);
            }
        }
    }
}
