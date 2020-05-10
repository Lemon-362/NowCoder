package NowCoder.Nowcoder.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class code05 {
    public static class stackToQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;

        public stackToQueue() {
            this.popStack = new Stack<>();
            this.pushStack = new Stack<>();
        }

        public void push(int num){
            this.pushStack.push(num);
        }

        public Integer poll(){
            if (this.pushStack.isEmpty() && this.popStack.isEmpty()){
                return null;
            }else if (this.popStack.isEmpty()){
                this.popStack.push(this.pushStack.pop());
            }
            return this.popStack.pop();
        }
    }

    public static class queueToStack {
        private Queue<Integer> stack;
        private Queue<Integer> help;

        public queueToStack() {
            this.stack = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int num){
            this.stack.add(num);
        }

        public Integer pop(){
            if (this.stack.isEmpty() && this.help.isEmpty()){
                return null;
            }
            while (this.stack.size() > 1){
                this.help.add(this.stack.poll());
            }
            int res = this.stack.poll();
            swap();
            return res;
        }

        public void swap(){
            Queue<Integer> tmp = stack;
            stack = help;
            help = tmp;
        }
    }
}
