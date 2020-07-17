package NowCoder.Nowcoder.Nowcoder;

import java.util.Stack;

/*
    定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
        注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法
 */
public class code20_GetMinStack {
    private Stack<Integer> numStack;
    private Stack<Integer> minStack;

    public code20_GetMinStack() {
        this.numStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int node) {
        if (this.minStack.isEmpty()) {
            this.minStack.push(node);
        } else {
            if (node <= this.min()) {
                this.minStack.push(node);
            } else {
                this.minStack.push(this.min());
            }
        }
        this.numStack.push(node);
    }

    // TODO 注意：这里题目要求pop的返回值类型是void
    public void pop() {
        this.minStack.pop();
        this.numStack.pop();
    }

    // 题目说了不会在栈为空时调用pop min top方法，所以不需要写边界
    public int top() {
        return this.numStack.peek();
    }

    public int min() {
        return this.minStack.peek();
    }
}
