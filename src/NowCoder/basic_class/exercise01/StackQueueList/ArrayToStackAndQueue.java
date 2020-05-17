package NowCoder.basic_class.exercise01.StackQueueList;

public class ArrayToStackAndQueue {
    public static class ArrayToStack {
        private int[] stack;
        private int index;

        public ArrayToStack(int initSize) {
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
            if (index == 0){
                System.out.println("栈空");
            }

            return stack[--index];
        }

        public Integer peek(){
            if (index == 0){
                System.out.println("栈空");
            }

            return stack[index-1];
        }
    }

    public static class ArrayToQueue {
        private int[] queue;
        private int size;
        private int start;
        private int end;

        public ArrayToQueue(int initSize) {
            this.queue = new int[initSize];
            this.size = 0;
            this.start = 0;
            this.end = 0;
        }

        public void push(int num){
            if (size == queue.length){
                System.out.println("队列满");
            }

            queue[end] = num;

            size++;

            end = end == queue.length - 1 ? 0 : end + 1;
        }

        public Integer poll(){
            if (size == 0){
                System.out.println("队列空");
            }

            int res = queue[start];

            size--;

            start = start == queue.length - 1 ? 0 : start + 1;

            return res;
        }

        public Integer peek(){
            if (size == 0){
                System.out.println("队列空");
            }

            return queue[start];
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayToQueue arrayQueue = new ArrayToQueue(4);
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
        ArrayToStack arrayStack = new ArrayToStack(4);
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
