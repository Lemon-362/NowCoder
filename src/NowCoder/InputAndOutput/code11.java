package NowCoder.InputAndOutput;

/*
精度问题：
    输入：有多组测试用例，每组空格隔开两个整数
    输出：对于每组数据输出一行两个整数的和

输入：
    1 1
输出：
    2
 */
import java.util.*;

public class code11 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            long a = sc.nextLong();
            long b = sc.nextLong();
            System.out.println(a+b);
        }
    }
}
