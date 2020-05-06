package NowCoder.InputAndOutput;

/*
对输入的字符串进行排序后输出：
    输入：多个测试用例，每个测试用例一行。
         每行通过空格隔开，有n个字符，n＜100
    输出：对于每组测试用例，输出一行排序过的字符串，每个字符串通过空格隔开

输入：
    a c bb
    f dddd
    nowcoder
输出：
    a bb c
    dddd f
    nowcoder
 */
import java.util.*;

public class code09 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String[] s = sc.nextLine().split(" ");
            Arrays.sort(s);
            for(String str : s){
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
