package NowCoder.LeetCode.Hot.exercise;

import java.util.Stack;

public class code11 {
    public static boolean isValid(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '('){
                stack.push(')');
            }else if (ch == '['){
                stack.push(']');
            }else if (ch == '{'){
                stack.push('}');
            }else {
                if (!stack.isEmpty() && ch == stack.peek()){
                    stack.pop();
                }else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([)]";
        String s5 = "{[]}";
        String s6 = "({[]})";

        System.out.println(isValid(s1)); // true
        System.out.println(isValid(s2)); // true
        System.out.println(isValid(s3)); // false
        System.out.println(isValid(s4)); // false
        System.out.println(isValid(s5)); // true
        System.out.println(isValid(s6)); // true
    }
}
