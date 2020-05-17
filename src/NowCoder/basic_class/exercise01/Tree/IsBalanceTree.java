package NowCoder.basic_class.exercise01.Tree;

public class IsBalanceTree {
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
        private int height;

        public ReturnData(boolean isB, int height) {
            this.isB = isB;
            this.height = height;
        }
    }

    public static ReturnData process(Node head){
        // base case
        if (head == null){
            return new ReturnData(true, 0);
        }

        ReturnData left = process(head.left);
        ReturnData right = process(head.right);

        // 1
        if (!left.isB){
            return new ReturnData(false, 0);
        }

        // 2
        if (!right.isB){
            return new ReturnData(false, 0);
        }

        // 3
        if (Math.abs(left.height - right.height) > 1){
            return new ReturnData(false, 0);
        }

        int curHeight = Math.max(left.height, right.height) + 1;

        return new ReturnData(true, curHeight);
    }

    public static boolean isBalance(Node head){
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

        System.out.println(isBalance(head));
    }
}
