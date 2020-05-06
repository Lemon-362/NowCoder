package NowCoder.HuaWei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*
    顺序记录key-value：
        数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，
        即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
        输入：  4              输出： 0 3
                0 1                   1 2
                0 2                   3 4
                1 2
                3 4
 */
public class code08_MapInOrder {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt(); // 个数
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for(int i = 0; i < num; i++){ // 多次记录
                int key = sc.nextInt();
                int value = sc.nextInt();
                if(map.containsKey(key)){
                    map.put(key, map.get(key) + value);
                }else {
                    map.put(key, value);
                }
            }
            // 记录完输出
            for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
