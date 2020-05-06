package NowCoder.advanced_class.exercise;

import NowCoder.advanced_class.class_5.Code_03_MaxDistanceInTree;

/*
	树的递归3：二叉树的最远距离
		二叉树中，一个节点可以往上走和往下走
		节点A到节点B的距离：A走到B最短路径上的节点个数
		求一个二叉树的最远距离
 */
public class code22_MaxDistanceInTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /*
        题目要求整个树上的最远距离 --> 求以每个节点为头的子树上的最远距离
        1. 分析可能性:
            1) 可能在左子树上
            2) 可能在右子树上
            3) 可能是从左到右的 --> 肯定是从左子树的底到右子树的底
            --> 那么就要求出子树的深度
            三者中取max
        2. 列出所需信息:
            1) 左子树上的最远距离
            2) 右子树上的最远距离
            3) 左子树的深度
            4) 右子树的深度
        3. 整合成相同结构的信息,当作黑盒,向上返回:
            1) 最远距离
            2) 深度
        4. 改递归
     */

    public static class ReturnData {
        private int maxDistance;
        private int depth;

        public ReturnData(int maxDistance, int depth) {
            this.maxDistance = maxDistance;
            this.depth = depth;
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
        int curDistance = leftData.depth + rightData.depth + 1;

        int maxDistance = Math.max(Math.max(leftDistance, rightDistance), curDistance);
        int depth = Math.max(leftData.depth, rightData.depth) + 1;

        return new ReturnData(maxDistance, depth);
    }

    public static int getMaxDistance(Node head){
        return process(head).maxDistance;
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
        System.out.println(getMaxDistance(head1)); // 8

        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);
        head2.right.left = new Node(4);
        head2.right.right = new Node(5);
        head2.right.left.left = new Node(6);
        head2.right.right.right = new Node(7);
        head2.right.left.left.left = new Node(8);
        head2.right.right.right.right = new Node(9);
        System.out.println(getMaxDistance(head2)); // 7

    }
}
