package NowCoder.InputAndOutput;

/*
计算一系列数的和：
    输入：数据包括多组。
         每组数据一行,每行的第一个整数为整数的个数n(1 <= n <= 100), n为0的时候结束输入。
         接下来n个正整数,即需要求和的每个正整数
    输出：每组数据输出求和的结果

输入：
    4 1 2 3 4
    5 1 2 3 4 5
    0
输出：
    10
    15

 */
import java.util.*;

public class code04 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int sum = 0;
            if(n == 0){
                break;
            }else {
                for(int i = 0; i < n; i++){
                    sum += sc.nextInt();
                }
                System.out.println(sum);
            }
        }
    }
}
