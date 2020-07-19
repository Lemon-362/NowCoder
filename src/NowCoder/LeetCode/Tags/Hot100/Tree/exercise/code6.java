package NowCoder.LeetCode.Tags.Hot100.Tree.exercise;

public class code6 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getMaxDistance(Node head) {
        if (head == null) {
            return 0;
        }

        return process(head).maxDistance - 1;
    }

    public static class ReturnData {
        private int height;
        private int maxDistance;

        public ReturnData(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    public static ReturnData process(Node head){
        // base case
        if (head == null){
            return new ReturnData(0, 0);
        }

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        // 1
        int leftDistance = leftData.maxDistance;

        // 2
        int rightDistance = rightData.maxDistance;

        // 3
        int curDistance = leftData.height + rightData.height + 1;

        int maxDistance = Math.max(curDistance, Math.max(leftDistance, rightDistance));
        int height = Math.max(leftData.height, rightData.height) + 1;

        return new ReturnData(height, maxDistance);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(8);
        head1.right.left.right = new Node(9);
        head1.right.left.right.right = new Node(10);
        System.out.println(getMaxDistance(head1)); // 7

        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);
        head2.right.left = new Node(4);
        head2.right.right = new Node(5);
        head2.right.left.left = new Node(6);
        head2.right.right.right = new Node(7);
        head2.right.left.left.left = new Node(8);
        head2.right.right.right.right = new Node(9);
        System.out.println(getMaxDistance(head2)); // 6

    }
}
