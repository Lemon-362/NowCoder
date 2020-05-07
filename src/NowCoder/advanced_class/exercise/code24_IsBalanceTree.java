package NowCoder.advanced_class.exercise;

import NowCoder.advanced_class.class_5.Code_05_IsBalancedTree;

/*
	判断是否是平衡二叉树：
		平衡二叉树：每个节点的左右子树的高度差不超过1

	对于某个节点x：1. 左子树是否平衡
				   2. 右子树是否平衡
				   3. 左子树和右子树的高度
				   4. 两者的高度差是否不超过1
	由于递归可以访问一个节点3次，所以
		1. 向左递归，收集1和3（左的高度）信息
		2. 向右递归，收集2和3（右的高度）信息
		3. 返回值应包含两个信息（是否平衡，高度）
 */
public class code24_IsBalanceTree {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
        题目要求判断整个树是否平衡 --> 判断以每个节点为头的子树是否平衡
        1. 分析可能性:
            1) 左子树不平衡
            2) 右子树不平衡
            3) 左子树平衡,右子树也平衡,加上头节点不平衡 --> 左子树的高度和右子树的高度的差大于1
        2. 列出所需信息:
            1) 左子树平衡
            2) 右子树平衡
            3) 左子树高度
            4) 右子树高度
        3. 整合信息:
            1) 平衡
            2) 高度
        4. 改递归
     */
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

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        // 1
        if (!leftData.isB){
            return new ReturnData(false, 0);
        }

        // 2
        if (!rightData.isB){
            return new ReturnData(false, 0);
        }

        // 3
        if (Math.abs(leftData.height - rightData.height) > 1){
            return new ReturnData(false, 0);
        }

        return new ReturnData(true, Math.max(leftData.height, rightData.height) + 1);
    }

    public static boolean isBalanceTree(Node head){
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

        System.out.println(isBalanceTree(head));
    }
}
