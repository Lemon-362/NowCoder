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
        private int h;

        public ReturnData(boolean isB, int h) {
            this.isB = isB;
            this.h = h;
        }
    }

    public static boolean isBalance(Node head) {
        return process(head).isB;
    }

    public static ReturnData process(Node head) {
        if (head == null) {
            return new ReturnData(true, 0);
        }

        ReturnData left = process(head.left);
        if (!left.isB) {
            return new ReturnData(false, 0);
        }

        ReturnData right = process(head.right);
        if (!right.isB) {
            return new ReturnData(false, 0);
        }

        if (Math.abs(left.h - right.h) > 1) {
            return new ReturnData(false, 0);
        }

        return new ReturnData(true, Math.max(left.h, right.h) + 1);
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
