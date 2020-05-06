package NowCoder.basic_class.exercise;

/*
	实现一个特殊的栈：MyStack2
		在实现栈基本功能上，再实现返回栈中最小元素的操作
	要求：
	1. pop、push、getMin的时间复杂度都是O(1)
	2. 可以使用现成的栈结构
 */

import java.util.Stack;

public class code12_GetMinStack {
    public static class getMinStack {
        private Stack<Integer> numStack;
        private Stack<Integer> minStack;

        public getMinStack() {
            this.numStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int num){
            if (!this.minStack.isEmpty()){
                if (this.getMin() > num){
                    this.minStack.push(num);
                }else {
                    this.minStack.push(this.minStack.peek());
                }
            }else {
                this.minStack.push(num);
            }
            this.numStack.push(num);
        }

        public Integer getMin(){
            if (this.minStack.isEmpty()){
                System.out.println("栈空");
            }
            return this.minStack.peek();
        }

        public Integer pop(){
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
