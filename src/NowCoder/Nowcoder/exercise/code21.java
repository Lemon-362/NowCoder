package NowCoder.Nowcoder.exercise;

import java.util.Stack;

public class code21 {
    public static boolean IsPopOrder(int[] push, int[] pop){
        if (push.length == 0 || pop.length == 0){
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int index = 0;

        for (int i = 0; i < push.length; i++) {
            stack.push(push[i]);

            while (!stack.isEmpty() && stack.peek() == pop[index]){
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

        System.out.println(IsPopOrder(pushA, popA)); // true
        System.out.println(IsPopOrder(pushA, popB)); // false
    }
}
