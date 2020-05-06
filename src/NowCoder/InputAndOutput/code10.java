package NowCoder.InputAndOutput;

/*
对输入的字符串进行排序后输出：
    输入：多个测试用例，每个测试用例一行。
         每行通过,隔开，有n个字符，n＜100
    输出：对于每组用例输出一行排序后的字符串，用','隔开，无结尾空格

输入：
    a,c,bb
    f,dddd
    nowcoder
输出：
    a,bb,c
    dddd,f
    nowcoder
 */
import java.util.*;

public class code10 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String[] s = sc.nextLine().split(",");
            Arrays.sort(s);
            for(int i = 0; i < s.length; i++){
                if(i == s.length - 1){
                    System.out.print(s[i]);
                }else{
                    System.out.print(s[i] + ",");
                }
            }
            System.out.println();
        }
    }
}
