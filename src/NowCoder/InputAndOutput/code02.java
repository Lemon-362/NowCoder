package NowCoder.InputAndOutput;

/*
计算a+b：
    输入：第一行包括一个数据组数t(1 <= t <= 100)
         接下来每行包括两个正整数a,b(1 <= a, b <= 10^9)
    输出：a+b的结果

输入：
    2
    1 5
    10 20
输出：
    6
    30
 */
import java.util.*;

public class code02 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int t = sc.nextInt();
            for(int i = 0; i < t; i++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                System.out.println(a+b);
            }
        }
    }
}
