package NowCoder.HuaWei;

import java.util.HashMap;
import java.util.Scanner;

/*
    输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
    输入：9876673      输出：37689
 */
public class code10_ReverseNumAndDuplicate {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt();
            String str = String.valueOf(num);
            HashMap<String, Integer> map = new HashMap<>();
            String res = "";
            for(int i = str.length(); i >= 1; i--){
                if(!map.containsKey(str.substring(i-1, i))){
                    res += str.substring(i-1, i);
                    map.put(str.substring(i-1, i), 1);
                }
            }
            System.out.println(Integer.parseInt(res));
        }
    }
}
