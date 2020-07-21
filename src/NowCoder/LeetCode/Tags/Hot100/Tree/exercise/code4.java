package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

public class code4 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public static int pathSum(Node head, int sum) {
        if (head == null) {
            return 0;
        }

        int res = process(head, sum);
        res += process(head.left, sum);
        res += process(head.right, sum);

        return res;
    }

    // 宽度优先
    public static int process(Node head, int sum){
        // base case
        if (head == null){
            return 0;
        }

        sum -= head.value;

        int res = sum == 0 ? 1 : 0;

        return res + process(head.left, sum) + process(head.right, sum);
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(5);
        head.right = new Node(-3);
        head.left.left = new Node(3);
        head.left.right = new Node(2);
        head.right.right = new Node(11);
        head.left.left.left = new Node(3);
        head.left.left.right = new Node(-2);
        head.left.right.right = new Node(1);

        System.out.println(pathSum(head, 8)); // 3
    }
}
