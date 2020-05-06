package NowCoder.HuaWei;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class code14_ZiDianXuSort {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt();
            String[] s = new String[num];
            for(int i = 0; i < num; i++){
                s[i] = sc.next();
            }
            Arrays.sort(s, new myComparator());
            for(int i = 0; i < num; i++){
                System.out.println(s[i]);
            }
        }
    }

    public static class myComparator implements Comparator<String> {
        public int compare(String o1, String o2){
            return o1.compareTo(o2);
        }
    }
}
