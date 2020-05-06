package NowCoder.HuaWei;

import java.util.HashMap;
import java.util.Scanner;

/*
    编写一个函数，计算字符串中含有的不同字符的个数。
    字符在ASCII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。
    输入：abc      输出：3
 */
public class code09_CharNum {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            char[] s= sc.nextLine().toCharArray();
            HashMap<Integer, Character> map = new HashMap<>();
            int count = 0;
            for(int i = 0; i < s.length; i++){
                int num = (int)s[i];
                if(!map.containsKey(num)){
                    count++;
                    map.put(num, s[i]); // key存ASCII码，value存char字符
                }
            }
            System.out.println(count);
        }
    }
}
