package NowCoder.Hard.exercise;

public class basic_code30 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // TODO 前提：完全二叉树
    public static int TreeNodeNumber(Node head) {
        if (head == null) {
            return 0;
        }

        int H = getLeftMost(head, 1);

        return process(head, 1, H);
    }

    public static int process(Node head, int level, int H){
        // base case
        if (level == H){
            return 1;
        }

        if (getLeftMost(head.right, level + 1) == H){
            return (1 << (H - level)) + process(head.right, level + 1, H);
        }else {
            return (1 << (H - level - 1)) + process(head.left, level + 1, H);
        }
    }

    public static int getLeftMost(Node head, int level){
        while (head != null){
            level++;
            head = head.left;
        }

        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
//        head.right.right = new Node(7);
        System.out.println(TreeNodeNumber(head)); // 7
    }
}
