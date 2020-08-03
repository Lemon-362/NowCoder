package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

public class code10 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxPathSum(Node head) {
        if (head == null) {
            return 0;
        }

        process(head);

        return res;
    }

    public static int res = Integer.MIN_VALUE;

    public static int process(Node head){
        // base case
        if (head == null){
            return 0;
        }

        int left = Math.max(0, process(head.left));
        int right = Math.max(0, process(head.right));

        int maxValue = left + right + head.value;

        res = Math.max(res, maxValue);

        return Math.max(left, right) + head.value;
    }

    public static void main(String[] args) {
        Node head = new Node(-10);
        head.left = new Node(9);
        head.right = new Node(20);
        head.right.left = new Node(15);
        head.right.right = new Node(7);

        System.out.println(maxPathSum(head)); // 42
    }
}
