package NowCoder.advanced_class.exercise.exercise01;

public class code21 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class ReturnData {
        private int max;
        private int min;

        public ReturnData(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    public static ReturnData process(Node head) {
        // base case
        if (head == null){
            return new ReturnData(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        int maxMax = Math.max(head.value, Math.max(leftData.max, rightData.max));
        int minMin = Math.min(head.value, Math.min(leftData.min, rightData.min));

        return new ReturnData(maxMax, minMin);
    }

    public static void printMaxAndMin(Node head){
        ReturnData returnData = process(head);
        int max = returnData.max;
        int min = returnData.min;

        System.out.println("max: " + max);
        System.out.println("min: " + min);
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

        printMaxAndMin(head); // 20 0
    }

}
