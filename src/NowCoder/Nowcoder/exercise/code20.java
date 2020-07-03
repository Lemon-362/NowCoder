package NowCoder.Nowcoder.exercise;

import NowCoder.basic_class.exercise.code12_GetMinStack;

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
                if (this.getMin() < num){
                    minStack.push(this.getMin());
                }else {
                    minStack.push(num);
                }
            }

            numStack.push(num);
        }

        public Integer getMin(){
            if (minStack.isEmpty()){
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
