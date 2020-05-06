package NowCoder.InputAndOutput;

/*
计算一系列数的和：
    输入：数据有多组, 每行表示一组输入数据。
         每行的第一个整数为整数的个数n(1 <= n <= 100)。
         接下来n个正整数, 即需要求和的每个正整数。
    输出：每组数据输出求和的结果

输入：
    4 1 2 3 4
    5 1 2 3 4 5
输出：
    10
    15
 */
import java.util.*;

public class code06 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int sum = 0;
            for(int i = 0; i < n; i++){
                sum += sc.nextInt();
            }
            System.out.println(sum);
        }
    }
}
