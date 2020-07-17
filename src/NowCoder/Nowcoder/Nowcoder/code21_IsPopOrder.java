package NowCoder.Nowcoder.Nowcoder;

import java.util.Stack;

/*
    输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
    假设压入栈的所有数字均不相等。
        例如序列1,2,3,4,5是某栈的压入顺序，
        序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
        但4,3,5,1,2就不可能是该压栈序列的弹出序列。（
        注意：这两个序列的长度是相等的）
 */
public class code21_IsPopOrder {
    // 通过辅助栈，先压入一个数，然后peek和弹出序列的第一个比较
    // 如果相同，则从栈中弹出，弹出序列后移
    // 如果不同，则继续压栈
    // 最终，如果辅助栈都弹出了为空，则表示是对应的。
    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 0 || popA.length == 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushA = {1, 2, 3, 4, 5};
        int[] popA = {4, 5, 3, 2, 1};
        int[] popB = {4, 3, 5, 1, 2};

        System.out.println(IsPopOrder(pushA, popA));
        System.out.println(IsPopOrder(pushA, popB));

    }
}
