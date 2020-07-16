package NowCoder.LeetCode.Tags.Hot100.String;

import java.util.Stack;

/**
 * 32. 最长有效括号:
 *  给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 *  TODO 括号问题: 用栈来存, 并弹出来抵消一个括号对
 *      另外, 如果只是小括号的话, 只要是成对的, 就一定是合法的
 *           不存在说左右括号个数相同时还是不合法的情况
 */
public class code07_LongestValidParentheses {
    /**
     * 1. 暴力法, 双指针:
     *
     *  第一个指针始终找左括号(, 当找到左括号后, 指针就暂停
     *  然后第二个指针开始从当前位置往后移动
     *  因为括号是成对出现的, 所以只需要每次判断长度是否为偶数即可
     *
     *  当长度满足偶数, 且第二个指针遇到右括号时, 就判断这两个指针间的字符串是否满足要求
     *  如果满足要求, 则更新长度
     *
     *  判断是否满足要求, 可以借助code05_IsValid的思想, 利用栈来判断
     *
     *  时间复杂度: O(N^3)
     *  空间复杂度: O(N)
     *
     */
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
                if (!stack.isEmpty() && stack.peek() == ')'){
                    stack.pop();
                }else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * 2. 栈方法:
     *  栈中存左括号位置，遇到右括号弹出抵消一对
     *
     *  TODO 为了在栈中可以获得长度，栈不存左右括号，而是存其下标位置
     *
     *  TODO 主要思想：用栈存左括号，遇到右括号就弹出，模拟抵消一对括号，每次抵消一对就计算当前长度
     *              为了能够计算最长长度，由于长度总是从左括号开始计算的，
     *              所以栈底始终保持是当前遍历过的字符串中，最后一个没有被匹配的右括号
     *              这样就可以保证，当栈中最后一个左括号弹出后，可以计算其最长长度
     *
     *  1. 遇到（，入栈
     *  2. 遇到），首先弹出栈顶
     *  (1) 如果栈为空，则说明当前位置的）是没有被匹配的，将当前位置入栈
     *  (2) 如果栈不为空，则说明当前位置到栈顶位置后一个位置是一个有效括号长度，计算并更新长度
     *  3. 为了防止边界条件：())的情况
     *      当第一对括号匹配完后，栈为空，下一次无法弹出
     *      所以首先让栈中存-1，表示-1位置是右括号，防止0位置是左括号
     *
     *  时间复杂度: O(N)
     *  空间复杂度: O(N)
     *
     */
    public static int longestValidParentheses2(String s) {
        if (s == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int len = 0;

        for (int i = 0; i < s.length(); i++) {
            String value = s.substring(i, i + 1);

            if (value.equals("(")){
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

    /**
     * 3. 正逆向结合法
     *  括号是成对出现的, 所以遍历时分别记录左右括号出现的个数
     *  当个数相同时, 说明此时到当前位置可能是一组括号, 其长度是左括号个数*2
     *
     *  1. 在从左向右遍历的过程中:
     *      当个数相同时, 后面遇到的第一个右括号就是此时的划分界限
     *      因为如果 左 < 右, 此时一定是不合法的, 而 左==右 时已经更新过长度了
     *      说明前面的都已经成对出现了, 那么多出来的一个 右 在前面是不可能找到 左 和它匹配的, 所以是无效位置
     *      那么, 此时只需要将 左和右 的个数清零
     *      确保了每次只在遇到第一个左括号时才开始计数
     *
     *  2. 在从右向左遍历的过程中:
     *      如果仅仅从左向右遍历, 会遇到 左 > 右 的情况, 此时如果停止的话, 可能会漏掉
     *      其内部可能已经有可以匹配的成对括号出现, 但是由于只在 左==右 才更新长度,
     *      所以会一直不计算其内部的长度, 例如: ()((()), 第三个位置的(会影响到后面的计算
     *
     *      此时, 可以从右向左再遍历一遍
     *      当 左==右 时, 更新长度
     *      当 左 > 右 时, 左右清零, 说明此时遇到的 ( 往后没有 ) 可以和它匹配, 是无效位置
     *
     *  3. 不能在每次更新长度后清零, 因为可能后面仍然是成对的有效括号
     *
     *  时间复杂度: O(N)
     *  空间复杂度: O(1)
     *
     */
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
