package NowCoder.Nowcoder.exercise01.Tree;

public class code38 {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int TreeDepth(Node head){
        if (head == null){
            return 0;
        }

        return process(head);
    }

    // 深度优先
    public static int process(Node head){
        if (head == null){
            return 0;
        }

        int leftDepth = process(head.left);
        int rightDepth = process(head.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.left = new Node(9);
        head.right = new Node(20);
        head.right.left = new Node(15);
        head.right.right = new Node(7);
        head.right.left.right = new Node(2);

        System.out.println(TreeDepth(head)); // 4
    }
}
