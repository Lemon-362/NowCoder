package NowCoder.advanced_class.exercise.exercise01;

public class code20 {
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
        private Node head;
        private int maxSize;

        public ReturnData(int max, int min, Node head, int size) {
            this.max = max;
            this.min = min;
            this.head = head;
            this.maxSize = size;
        }
    }

    public static ReturnData process(Node head){
        // base case
        if (head == null){
            return new ReturnData(Integer.MIN_VALUE, Integer.MAX_VALUE, null, 0);
        }

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        // 1
        int leftSize = leftData.maxSize;

        // 2
        int rightSize = rightData.maxSize;

        // 3
        int curSize = 0;
        if (leftData.max < head.value && rightData.min > head.value && leftData.head == head.left && rightData.head == head.right){
            curSize = leftSize + rightSize + 1;
        }

        int maxMax = Math.max(head.value, Math.max(leftData.max, rightData.max));
        int minMin = Math.min(head.value, Math.min(leftData.min, rightData.min));
        int maxSize = Math.max(curSize, Math.max(leftSize, rightSize));
        Node maxHead = leftSize > rightSize ? leftData.head : rightData.head;
        if (maxSize == curSize){
            maxHead = head;
        }

        return new ReturnData(maxMax, minMin, maxHead, maxSize);
    }

    public static Node getMaxBST(Node head){
        return process(head).head;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
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

        Node bst = getMaxBST(head);
        printTree(bst);
        System.out.println(process(head).maxSize); // 7
    }
}
