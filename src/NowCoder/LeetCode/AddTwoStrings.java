package NowCoder.LeetCode;

/*
415. 字符串相加:
    给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

 */
public class AddTwoStrings {

    public static String addStrings(String num1, String num2){

        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carry = 0;

        while (i >= 0 || j >= 0){
            int n1 = 0;
            int n2 = 0;

            if (i >= 0){
                n1 = num1.charAt(i) - '0';
            }

            if (j >= 0){
                n2 = num2.charAt(j) - '0';
            }

            int sum = carry + n1 + n2;
            carry = sum / 10;
            sum = sum % 10;

            sb.append(sum);

            i--;
            j--;
        }

        if (carry == 1){
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {

        String num1 = "51189";
        String num2 = "967895";

        System.out.println(Integer.parseInt(num1) + Integer.parseInt(num2));

        System.out.println(addStrings(num1, num2));
    }
}
