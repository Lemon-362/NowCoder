package NowCoder.basic_class.exercise.exercise02;

import NowCoder.basic_class.exercise.code11_Array_To_Stack_Queue;

public class code11 {
    public static class ArrayStack {
        private int[] stack;
        private int index;

        public ArrayStack(int initSize) {
            this.stack = new int[initSize];
            this.index = 0;
        }

        public void push(int num){
            if (index == stack.length){
                System.out.println("栈满");
            }
            stack[index++] = num;
        }

        public Integer pop(){
            if (stack.length == 0){
                System.out.println("栈空");
            }
            return stack[--index];
        }

        public Integer peek(){
            if (stack.length == 0){
                System.out.println("栈空");
            }
            return stack[index - 1];
        }
    }

    public static class ArrayQueue {
        private int[] queue;
        private int size;
        private int start;
        private int end;

        public ArrayQueue(int initSize) {
            this.queue = new int[initSize];
            this.start = 0;
            this.size = 0;
            this.end = 0;
        }

        public void push(int num){
            if (size == queue.length){
                System.out.println("队列满");
            }
            size++;
            queue[start] = num;
            start = start == queue.length - 1 ? 0 : start + 1;
        }

        public Integer poll(){
            if (size == 0){
                System.out.println("队列空");
            }
            size--;
            int res = queue[end];
            end = end == queue.length - 1 ? 0 : end + 1;
            return res;
        }

        public Integer peek(){
            if (size == 0){
                System.out.println("队列空");
            }
            return queue[end];
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayQueue arrayQueue = new ArrayQueue(4);
        arrayQueue.push(1);
        arrayQueue.push(4);
        arrayQueue.push(3);
        arrayQueue.push(2);

        Integer peek = arrayQueue.peek();
        System.out.println(peek); // 1
        Integer pop = arrayQueue.poll();
        System.out.println(pop); // 1
        Integer peek1 = arrayQueue.peek();
        System.out.println(peek1); // 4

        System.out.println("=================");
        ArrayStack arrayStack = new ArrayStack(4);
        arrayStack.push(1);
        arrayStack.push(4);
        arrayStack.push(3);
        arrayStack.push(2);

        peek = arrayStack.peek();
        System.out.println(peek); // 2
        pop = arrayStack.pop();
        System.out.println(pop); // 2
        peek1 = arrayStack.peek();
        System.out.println(peek1); // 3
    }
}
