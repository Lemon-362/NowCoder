package NowCoder.LeetCode.Tags.Hot100.String;

import java.util.Stack;
import java.util.stream.Stream;

/**
 * 有效的括号：
 *  给定一个只包括 (，)，{，}，[，] 的字符串，判断字符串是否有效。
 *  有效字符串需满足：
 *      左括号必须用相同类型的右括号闭合。
 *      左括号必须以正确的顺序闭合。
 *      注意空字符串可被认为是有效字符串。
 *
 *  TODO 题目没有要求必须满足小括号在中括号内，中括号必须在大括号内
 *       所以题目可以转换为：先进后出原则，也就是栈结构
 *       只要能够匹配成一对，就认为是正确的
 */
public class code05_IsValid {
    /**
     *  思路：TODO 栈的应用
     *    可能出现的情况：()[]{}, {[()]}
     *    可以发现，一定是先判断 )，再判断 ]，再判断 }
     *    所以可以使用栈来模拟，先遇到的要到后面判断，后遇到的要先判断
     *    遇到 ( 就存入对应的 ), 遇到 [ 就存入对应的 ], 遇到 { 就存入对应的 }
     *    然后再往后遇到对应的右括号, 就可以从栈中弹出, 进行抵消
     *
     */
    public static boolean isValid(String s){
        if (s == null || s.length() < 1){
            return true;
        }

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            String value = s.substring(i, i + 1);

            if (value.equals("(")){
                stack.push(")");
            }else if (value.equals("[")){
                stack.push("]");
            }else if (value.equals("{")){
                stack.push("}");
            }else {
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
