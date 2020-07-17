package NowCoder.Nowcoder.Nowcoder;

import java.util.Stack;

/*
    用两个栈来实现队列，完成队列的Push和Pop操作。队列中的元素为int类型。
 */
public class code05_TwoStackToQueue {
    public static class Solution {
        Stack<Integer> stack1 = new Stack<Integer>(); // pushStack
        Stack<Integer> stack2 = new Stack<Integer>(); // popStack

        public void push(int node) {
            this.stack1.push(node);
        }

        public int pop() {
            if(this.stack1.isEmpty() && this.stack2.isEmpty()){
                System.out.println("队列空");
            }else if(this.stack2.isEmpty()){
                while(!this.stack1.isEmpty()){
                    this.stack2.push(this.stack1.pop());
                }
            }
            return this.stack2.pop();
        }
    }
}
