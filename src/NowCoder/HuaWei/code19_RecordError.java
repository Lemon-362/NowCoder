package NowCoder.HuaWei;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/*
    记录错误：
        开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
    处理：
    1、 记录最多8条错误记录，循环记录（或者说最后只输出最后出现的八条错误记录），对相同的错误记录（净文件名称和行号完全匹配）只记录一条，错误计数增加；
    2、 超过16个字符的文件名称，只记录文件的最后有效16个字符；
    3、 输入的文件可能带路径，记录文件名称不能带路径。

    输入： E:\V1R2\product\fpgadrive.c   1325
    输出： fpgadrive.c 1325 1
 */
public class code19_RecordError {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // 只输出最后的8个信息，并且相同错误记录只记录一条，错误计数增加
        // 用Map，且要求顺序输出，用LinkedHashMap，key记录错误信息，value记录错误计数
        Map<String, Integer> map = new LinkedHashMap<>();
        while(sc.hasNext()){
            // 输入带路径的文件名
            String str = sc.next();
            // 输入行号
            int line = sc.nextInt();
            // 对文件名分割，得到纯文件名
            String[] s = str.split("\\\\");
            String s1 = s[s.length - 1];
            // 是否超过16个字符
            if(s1.length() > 16){ // 超过16个字符，只保留最后16个
                s1 = s1.substring(s1.length() - 16);
            }
            // 加上行号，作为key
            String key = s1 + " " + line;
            // 存入map中
            if(map.containsKey(key)){
                map.put(key, map.get(key) + 1);
            }else {
                map.put(key, 1);
            }
        }
        // 输出最后8个信息，从size-8开始
        int count = 0; // 表示遍历到第几个信息
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            count++;
            if(count > (map.size() - 8)){
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
