package NowCoder.basic_class.Tags.Tree;

public class IsBalanceTree {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBalance(Node head){
        if (head == null){
            return true;
        }

        return process(head).isB;
    }

    public static class ReturnData {
        private int height;
        private boolean isB;

        public ReturnData(int height, boolean isB) {
            this.height = height;
            this.isB = isB;
        }
    }

    // 深度优先
    public static ReturnData process(Node head){
        // base case
        if (head == null){
            return new ReturnData(0, true);
        }

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        // 1
        if (!leftData.isB){
            return new ReturnData(0, false);
        }

        // 2
        if (!rightData.isB){
            return new ReturnData(0, false);
        }

        // 3
        if (Math.abs(leftData.height - rightData.height) > 1){
            return new ReturnData(0, false);
        }

        return new ReturnData(Math.max(leftData.height, rightData.height) + 1, true);
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
