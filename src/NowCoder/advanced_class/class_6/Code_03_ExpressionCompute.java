package NowCoder.advanced_class.class_6;

import java.util.LinkedList;

/*
    表达式计算：
        给定一个字符串str，包含整数、运算符号和括号
        返回计算结果
 */
public class Code_03_ExpressionCompute {

    public static int getValue(String str) {
        return value(str.toCharArray(), 0)[0]; // 从0位置开始
    }

    // 递归f(str, index)
    // 返回长度为2的数组：两个信息
    // arr[0]：计算结果  arr[1]：右括号的位置
    public static int[] value(char[] str, int i) {
        LinkedList<String> que = new LinkedList<String>();
        int pre = 0;
        int[] bra = null;
        while (i < str.length && str[i] != ')') { // 没有到达末尾且没有遇到右括号时，就一直收集数字,(,+,-,*,/
            // 收集多位数
            if (str[i] >= '0' && str[i] <= '9') { // 遇到数字，可能连续遇到多个数字，也就是多位数
                pre = pre * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到运算符号 + - * /
                addNum(que, pre); // 计算并将结果压入栈
                que.addLast(String.valueOf(str[i++])); // 将运算符压入栈
                pre = 0; // 之前的数字要归零，因为是放一个数字和一个符号
            } else { // TODO 遇到左括号(，当前i位置是(，则递归
                bra = value(str, i + 1); // 从i+1位置开始递归
                pre = bra[0]; // 把左括号到右括号里的结果放在arr[0]，并返回给我
                i = bra[1] + 1; // 把右括号位置返回给我，我从右括号后一个位置继续算
            }
        }
        // 计算收集到的结果
        addNum(que, pre);
        return new int[]{getNum(que), i};
    }

    // 没有括号的计算
    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) { // 如果栈顶是+或-,不需要计算
                que.addLast(top);
            } else { // 如果栈顶是*或/,需要计算
                cur = Integer.parseInt(que.pollLast()); // 弹出运算符下一个数字
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        // TODO 计算完后统一将结果再压入栈
        que.addLast(String.valueOf(num));
    }

    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.parseInt(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp)); // -1816

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp)); // 745

        exp = "10-5*3";
        System.out.println(getValue(exp)); // -5

        exp = "-3*4";
        System.out.println(getValue(exp)); //-12

        exp = "3+1*4";
        System.out.println(getValue(exp)); // 7

    }

}
