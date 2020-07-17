package NowCoder.Nowcoder.Nowcoder;

import java.util.Arrays;
import java.util.Comparator;

/*
    输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
    例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。

    TODO 字典序最小的排序规则 --> 掌握证明（传递性）
 */
public class code32_PrintMinNum {
    public static String PrintMinNumber(int[] numbers) {
        if (numbers == null) {
            return null;
        }
        String[] str = new String[numbers.length];
        for (int i = 0; i < str.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(str, new myComparator());
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : str) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public static class myComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9};
        System.out.println(PrintMinNumber(numbers)); // 3033459
    }
}
