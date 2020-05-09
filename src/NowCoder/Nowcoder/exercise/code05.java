package NowCoder.Nowcoder.exercise;

import java.util.Stack;

public class code05 {
    public static class stackToQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public stackToQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        public void push(int num) {
            this.pushStack.push(num);
        }

        public Integer poll() {
            if (this.pushStack.isEmpty() && this.popStack.isEmpty()) {
                return null;
            } else if (this.popStack.isEmpty()) {
                while (!this.pushStack.isEmpty()) {
                    this.popStack.push(this.popStack.pop());
                }
            }
            return this.popStack.pop();
        }
    }
}
