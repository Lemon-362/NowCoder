package NowCoder.LeetCode.Tags.Hot100.String.exercise;

import java.util.Stack;

public class code07 {
    public static int longestValidParentheses1(String s){
        if (s == null){
            return 0;
        }

        char[] str = s.toCharArray();

        int len = 0;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '('){
                for (int j = i + 1; j < str.length; j++) {
                    if ((j - i + 1) % 2 == 0 && str[j] == ')'){
                        if (isValid(str, i, j)){
                            len = Math.max(len, j - i + 1);
                        }
                    }
                }
            }
        }

        return len;
    }

    public static boolean isValid(char[] str, int i, int j){
        Stack<Character> stack = new Stack<>();

        for (int k = i; k <= j; k++) {
            if (str[k] == '('){
                stack.push(')');
            }else {
                if (!stack.isEmpty() && stack.peek() == str[k]){
                    stack.pop();
                }else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static int longestValidParentheses2(String s){
        if (s == null){
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int len = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }else {
                stack.pop();

                if (stack.isEmpty()){
                    stack.push(i);
                }

                len = Math.max(len, i - stack.peek());
            }
        }

        return len;
    }

    public static int longestValidParentheses3(String s){
        if (s == null) {
            return 0;
        }

        int leftNum = 0;
        int rightNum = 0;
        int len = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                leftNum++;
            }else {
                rightNum++;
            }

            if (leftNum == rightNum){
                len = Math.max(len, 2 * leftNum);
            }else if (rightNum > leftNum){
                leftNum = 0;
                rightNum = 0;
            }
        }

        leftNum = 0;
        rightNum = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '('){
                leftNum++;
            }else {
                rightNum++;
            }

            if (leftNum == rightNum){
                len = Math.max(len, 2 * leftNum);
            }else if (leftNum > rightNum){
                leftNum = 0;
                rightNum = 0;
            }
        }

        return len;
    }

    public static void main(String[] args) {
        String s1 = "))(())())";
        String s2 = ")()())";
        String s3 = "(()";

        System.out.println(longestValidParentheses1(s1)); // 6
        System.out.println(longestValidParentheses2(s1)); // 6
        System.out.println(longestValidParentheses3(s1)); // 6

        System.out.println("************************");

        System.out.println(longestValidParentheses1(s2)); // 4
        System.out.println(longestValidParentheses2(s2)); // 4
        System.out.println(longestValidParentheses3(s2)); // 4

        System.out.println("************************");

        System.out.println(longestValidParentheses1(s3)); // 2
        System.out.println(longestValidParentheses2(s3)); // 2
        System.out.println(longestValidParentheses3(s3)); // 2

    }
}
