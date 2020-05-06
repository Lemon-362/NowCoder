package NowCoder.HuaWei;

import java.util.Scanner;

/*
    反转句子：
        将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
        所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 */
public class code12_ReverseSequence {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String[] s = sc.nextLine().split(" ");
            String res = "";
            for(int i = s.length - 1; i >= 0; i--){
                res += s[i] + " ";
            }
            res.trim();
            System.out.println(res);
        }
    }
}
