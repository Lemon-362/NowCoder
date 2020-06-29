package NowCoder.Nowcoder;

/*
    左旋转字符串：
        汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
        对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
        例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 */
public class code43_LeftRotateString {
    public static String LeftRotateString(String str,int n) {
        if(str == null || n > str.length()){
            return str;
        }

        StringBuilder sb = new StringBuilder();

//        for (int i = n; i < str.length(); i++) {
//            sb.append(str.charAt(i));
//        }
//        for (int i = 0; i < n; i++) {
//            sb.append(str.charAt(i));
//        }

        // 利用取余运算，将两次for循环合并
        // 因为对于 n - len 范围内，区域是本身，对于 len+1 - n+len 范围内，取余后是 1 - n
        for (int i = n; i < n + str.length(); i++) {
            sb.append(str.charAt(i % str.length()));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String res = LeftRotateString("lrloseumgh", 6);
        System.out.println(res); // umghlrlose
    }
}
