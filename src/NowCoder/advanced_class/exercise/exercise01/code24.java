package NowCoder.advanced_class.exercise.exercise01;

public class code24 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class ReturnData {
        private boolean isB;
        private int depth;

        public ReturnData(boolean isB, int depth) {
            this.isB = isB;
            this.depth = depth;
        }
    }

    public static ReturnData process(Node head){
        // base case
        if (head == null){
            return new ReturnData(true, 0);
        }

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        // 1
        if (!leftData.isB){
            return new ReturnData(false, 0);
        }

        // 2
        if (!rightData.isB){
            return new ReturnData(false, 0);
        }

        // 3
        if (Math.abs(leftData.depth - rightData.depth) > 1){
            return new ReturnData(false, 0);
        }

        return new ReturnData(true, Math.max(leftData.depth, rightData.depth) + 1);
    }

    public static boolean isBalanceTree(Node head){
        return process(head).isB;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalanceTree(head));
    }
}
