package NowCoder.LeetCode.Tags.Hot100.String.exercise;

import java.util.Stack;

public class code05 {
    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            String value = s.substring(i, i + 1);

            if (value.equals("(")) {
                stack.push(")");
            } else if (value.equals("[")) {
                stack.push("]");
            } else if (value.equals("{")) {
                stack.push("}");
            } else {
                if (!stack.isEmpty() && stack.peek().equals(value)){
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
