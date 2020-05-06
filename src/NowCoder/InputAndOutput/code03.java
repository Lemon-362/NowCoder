package NowCoder.InputAndOutput;

/*
计算a+b：
    输入：包括两个正整数a,b(1 <= a, b <= 10^9),输入数据有多组, 如果输入为0 0则结束输入
    输出：a+b的结果

输入：
    1 5
    10 20
    0 0
输出：
    6
    30
 */
import java.util.*;

public class code03 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(a == 0 && b == 0){
                break;
            }
            System.out.println(a+b);
        }
    }
}
