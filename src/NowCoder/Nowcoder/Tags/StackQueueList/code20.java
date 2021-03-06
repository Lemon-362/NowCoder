package NowCoder.Nowcoder.Tags.StackQueueList;

import java.util.Stack;

public class code20 {
    public static class getMinStack {
        private Stack<Integer> numStack;
        private Stack<Integer> minStack;

        public getMinStack() {
            this.numStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int num){
            if (minStack.isEmpty()){
                minStack.push(num);
            }else {
                if (num < this.getMin()){
                    minStack.push(num);
                }else {
                    minStack.push(this.getMin());
                }
            }

            numStack.push(num);
        }

        public Integer getMin(){
            if (numStack.isEmpty() && minStack.isEmpty()){
                return null;
            }else {
                return minStack.peek();
            }
        }

        public Integer pop(){
            if (minStack.isEmpty() && numStack.isEmpty()){
                return null;
            }

            minStack.pop();

            return numStack.pop();
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
