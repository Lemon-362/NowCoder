package NowCoder.basic_class.exercise01.StackQueueList;

import java.util.Stack;

public class GetMinStack {
    public static class getMinStack {
        private Stack<Integer> numStack;
        private Stack<Integer> minStack;

        public getMinStack() {
            this.numStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int num) {
            if (this.minStack.isEmpty()) {
                this.minStack.push(num);
            } else {
                if (num <= this.getMin()) {
                    this.minStack.push(num);
                } else {
                    this.minStack.push(this.getMin());
                }
            }
            this.numStack.push(num);
        }

        public int getMin() {
            if (this.minStack.isEmpty()) {
                System.out.println("栈空");
            }
            return this.minStack.peek();
        }

        public int pop() {
            if (this.minStack.isEmpty() && this.numStack.isEmpty()) {
                System.out.println("栈空");
            }
            this.minStack.pop();
            return this.numStack.pop();
        }
    }

    public static void main(String[] args) {
        getMinStack getMinStack = new getMinStack();

        getMinStack.push(2);
        System.out.println(getMinStack.getMin()); // 2

        getMinStack.push(1);
        System.out.println(getMinStack.getMin()); // 1

        getMinStack.pop();
        System.out.println(getMinStack.getMin()); // 2

        getMinStack.push(3);
        System.out.println(getMinStack.getMin()); // 2
    }
}
