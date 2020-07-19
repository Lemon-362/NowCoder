package NowCoder.LeetCode.Tags.Hot100.Tree;

/**
 * 543. 二叉树的最大直径
 *  进阶code22的maxDistance
 *
 *  TODO 注意: 这里求的是直径, 而不是节点的个数(距离)
 *      需要将 距离 - 1 才能得到直径
 */
public class code6_MaxDistance {
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
        private int maxDistance;
        private int depth;

        public ReturnData(int maxDistance, int depth) {
            this.maxDistance = maxDistance;
            this.depth = depth;
        }
    }

    public static ReturnData process(Node head) {
        // base case
        if (head == null) {
            return new ReturnData(0, 0);
        }

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        // 1
        int leftDistance = leftData.maxDistance;

        // 2
        int rightDistance = rightData.maxDistance;

        // 3
        int curDistance = leftData.depth + rightData.depth + 1;

        int maxDistance = Math.max(curDistance, Math.max(leftDistance, rightDistance));
        int depth = Math.max(leftData.depth, rightData.depth) + 1;

        return new ReturnData(maxDistance, depth);
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
