package NowCoder.LeetCode.Tags.Hot100.String.exercise;

import java.util.Stack;

public class code07 {
    public static int longestValidParentheses1(String s){
        if (s == null){
            return 0;
        }

        int L = 0;
        int R = 0;
        int len = 0;

        while (L < s.length()){
            if (s.charAt(L) == '('){
                R = L;
                while (R < s.length()){
                    if ((R - L + 1) % 2 == 0 && s.charAt(R) == ')'){
                        if (isValid(s, L, R)){
                            len = Math.max(len, R - L + 1);
                        }
                    }
                    R++;
                }
            }

            L++;
        }

        return len;
    }

    public static boolean isValid(String s, int L, int R){
        Stack<Character> stack = new Stack<>();

        for (int i = L; i <= R; i++) {
            char ch = s.charAt(i);

            if (ch == '('){
                stack.push(')');
            }else {
                if (!stack.isEmpty() && stack.peek() == ch){
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
