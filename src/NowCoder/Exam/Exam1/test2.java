package NowCoder.Exam.Exam1;

import java.util.Scanner;
import java.util.Stack;

public class test2 {

    public static int process(char[] str){

        Stack<Character> stack = new Stack<>();

        int res = 0;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '('){
                stack.push(str[i]);
            }else if (str[i] == ')'){
                if (!stack.isEmpty() && stack.peek() == '('){
                    res++;
                    stack.pop();
                }
            } else if (str[i] == '-') {
                while (!stack.isEmpty()){
                    stack.pop();
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();

        System.out.println(process(s.toCharArray()));

    }

}
