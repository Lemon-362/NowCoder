package NowCoder.InputAndOutput;

/*
对输入的字符串进行排序后输出：
    输入：有两行，第一行n
         第二行是n个空格隔开的字符串
    输出：一行排序后的字符串，空格隔开，无结尾空格

输入：
    5
    c d a bb e
输出：
    a bb c d e
 */
import java.util.*;

public class code08 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            String[] s = new String[n];
            for(int i = 0; i < n; i++){
                s[i] = sc.next();
            }
            Arrays.sort(s);
            for(int i = 0; i < n; i++){
                System.out.print(s[i] + " ");
            }
        }
    }
}
