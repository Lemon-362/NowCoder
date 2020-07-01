package NowCoder.Nowcoder.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class code05 {
    public static class StackToQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public StackToQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        public void push(int num) {
            pushStack.push(num);
        }

        public Integer poll() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                return null;
            } else if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }

            return popStack.pop();
        }

        public Integer peek() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                return null;
            } else if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }

            return popStack.peek();
        }
    }

    public static class QueueToStack {
        private Queue<Integer> stack;
        private Queue<Integer> help;

        public QueueToStack() {
            this.stack = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int num) {
            stack.add(num);
        }

        public Integer pop() {
            if (stack.isEmpty() && help.isEmpty()) {
                return null;
            }

            while (stack.size() > 1) {
                help.add(stack.poll());
            }

            int res = stack.poll();

            swap();

            return res;
        }

        public Integer peek() {
            if (stack.isEmpty() && help.isEmpty()) {
                return null;
            }

            while (stack.size() > 1) {
                help.add(stack.poll());
            }

            int res = stack.poll();

            help.add(res);

            swap();

            return res;
        }

        public void swap() {
            Queue<Integer> temp = stack;
            stack = help;
            help = temp;
        }
    }

    public static void main(String[] args) {
        QueueToStack queueToStack = new QueueToStack();
        queueToStack.push(3);
        queueToStack.push(2);
        System.out.println(queueToStack.peek()); // 2
        queueToStack.push(4);
        System.out.println(queueToStack.pop()); // 4

        System.out.println(queueToStack.peek()); // 2
        queueToStack.push(6);
        System.out.println(queueToStack.peek()); // 6
        System.out.println(queueToStack.pop()); // 6
        System.out.println(queueToStack.pop()); // 2

        System.out.println("============================");

        StackToQueue stackToQueue = new StackToQueue();
        stackToQueue.push(3);
        stackToQueue.push(6);
        System.out.println(stackToQueue.peek()); // 3
        stackToQueue.push(2);
        System.out.println(stackToQueue.poll()); // 3

        stackToQueue.push(8);
        System.out.println(stackToQueue.peek()); // 6

        System.out.println(stackToQueue.poll()); // 6
        System.out.println(stackToQueue.poll()); // 2
        stackToQueue.push(1);
        System.out.println(stackToQueue.poll()); // 8
    }
}
