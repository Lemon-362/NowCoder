package NowCoder.InputAndOutput;

/*
计算一系列数的和：
    输入：数据有多组, 每行表示一组输入数据。
    每行不定有n个整数，空格隔开。(1 <= n <= 100)。
    输出：每组数据输出求和的结果

输入：
    1 2 3
    4 5
    0 0 0 0 0
输出：
    6
    9
    0
 */
import java.util.*;

public class code07 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String[] s = sc.nextLine().split(" ");
            int sum = 0;
            for(int i = 0; i < s.length; i++){
                sum += Integer.parseInt(s[i]);
            }
            System.out.println(sum);
        }
    }
}
