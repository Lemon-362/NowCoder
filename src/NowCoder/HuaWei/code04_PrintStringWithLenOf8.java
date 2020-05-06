package NowCoder.HuaWei;

import java.util.Scanner;

public class code04_PrintStringWithLenOf8 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            // 输入一个就输出一个
            String str = sc.nextLine();
            // 用StringBuilder操作方便
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            int zeroLen = 8 - str.length() % 8;// 长度超过8，往后面加0直到是8的整数倍
            if(zeroLen > 0 && zeroLen < 8){ // 如果长度刚好是8，就不需要加0
                while(zeroLen > 0){
                    sb.append("0");
                    zeroLen--;
                }
            }
            String str1 = sb.toString();
            while(str1.length() > 0){
                System.out.println(str1.substring(0, 8)); // 打印str的前8个
                str1 = str1.substring(8); // str截取除了前8个的后面所有字符
            }
        }

        // 法二
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()) {
//            // 输入一个就输出一个
//            String str = sc.nextLine();
//            // 假设长度超过8，先打印长度为8的字符串
//            while (str.length() > 8) {
//                System.out.println(str.substring(0, 8)); // 打印str的前8个
//                str = str.substring(8); // str截取除了前8个的后面所有字符
//            }
//            // 此时，str长度不足8，补0
//            int zeroLen = 8 - str.length();
//            if (zeroLen > 0 && zeroLen < 8) {
//                for (int i = 0; i < zeroLen; i++) {
//                    str += "0";
//                }
//            }
//            System.out.println(str);
//        }
    }
}
