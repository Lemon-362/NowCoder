package NowCoder.HuaWei;

import java.util.Scanner;

/*
    密码转换：
        1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0,
        密码中出现的大写字母则变成小写之后往后移一位，如：X，先变成小写，再往后移一位，不就是y了嘛，简单吧。
        记住，z往后移是a哦。

        输入：YUANzhi1987
        输出：zvbo9441987
 */
public class code21_PassWordConvert {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            char[] s = sc.nextLine().toCharArray();
            StringBuilder res = new StringBuilder();
            for(int i = 0; i < s.length; i++){
                if(s[i] >= 'A' && s[i] <= 'Y'){
                    res.append((char)(s[i] + 33));
                }else if (s[i] >= 'a' && s[i] <= 'c'){
                    res.append('2');
                }else if (s[i] >= 'd' && s[i] <= 'f'){
                    res.append('3');
                }else if (s[i] >= 'g' && s[i] <= 'i'){
                    res.append('4');
                }else if (s[i] >= 'j' && s[i] <= 'l'){
                    res.append('5');
                }else if (s[i] >= 'm' && s[i] <= 'o'){
                    res.append('6');
                }else if (s[i] >= 'p' && s[i] <= 's'){
                    res.append('7');
                }else if (s[i] >= 't' && s[i] <= 'v'){
                    res.append('8');
                }else if (s[i] >= 'w' && s[i] <= 'z'){
                    res.append('9');
                }else if (s[i] >= '0' && s[i] <= '9'){
                    res.append(s[i]);
                }else if(s[i] == 'Z'){
                    res.append('a');
                }
            }
            System.out.println(res.toString());
        }
    }
}
