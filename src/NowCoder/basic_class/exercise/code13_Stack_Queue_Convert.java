package NowCoder.basic_class.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
    1. 仅用队列实现栈
    2. 仅用栈实现队列
 */
public class code13_Stack_Queue_Convert {
    // 队列实现栈
    public static class QueueToStack {
        private Queue<Integer> stack;
        private Queue<Integer> help;

        public QueueToStack() {
            this.stack = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int num){
            this.stack.add(num);
        }

        public Integer pop(){
            if (this.stack.isEmpty()){
                System.out.println("栈空");
            }
            while (this.stack.size() > 1){
                this.help.add(this.stack.poll());
            }
            int res = this.stack.poll();
            swap();
            return res;
        }

        public Integer peek(){
            if (this.stack.isEmpty()){
                System.out.println("栈空");
            }
            while (this.stack.size() > 1){
                this.help.add(this.stack.poll());
            }
            int res = this.stack.poll();
            this.help.add(res);
            swap();
            return res;
        }

        public  void swap(){
            Queue<Integer> tmp = this.stack;
            this.stack = this.help;
            this.help = tmp;
        }
    }

    // 栈实现队列
    public static class StackToQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public StackToQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        public void push(int num){
            this.pushStack.push(num);
        }

        public Integer poll(){
            if (this.pushStack.isEmpty() && this.popStack.isEmpty()){
                System.out.println("队列空");
            }else if (this.popStack.isEmpty()){
                while (!this.pushStack.isEmpty()){
                    this.popStack.push(this.pushStack.pop());
                }
            }
            return this.popStack.pop();
        }

        public Integer peek(){
            if (this.pushStack.isEmpty() && this.popStack.isEmpty()){
                System.out.println("队列空");
            }else if (this.popStack.isEmpty()){
                while (!this.pushStack.isEmpty()){
                    this.popStack.push(this.pushStack.pop());
                }
            }
            return this.popStack.peek();
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

