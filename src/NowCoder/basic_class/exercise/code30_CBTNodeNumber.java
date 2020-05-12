package NowCoder.basic_class.exercise;

/*
	已知一颗完全二叉树，求其节点的个数：时间复杂度O[(logn)^2]
		结论：满二叉树，高度为L，则其节点个数为 2^L-1
	1. 完全二叉树，遍历左子树边界，可知数的高度 h总
	2. 遍历头节点右孩子的左子树边界，
		1) 若 边界到达h总，则头节点右孩子的左子树必满，高度=h总-1
		2) 若 边界没有到达h总，则头节点右孩子的右子树必满，高度=右孩子的左子树满时的高度-1
	3. 递归求右(1)/左(2)子树的节点个数
 */

public class code30_CBTNodeNumber {
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static int TreeNodeNumber(Node head) {
        if (head == null) {
            return 0;
        }
        int H = getLeftMost(head, 1);
        return process(head, 1, H);
    }

    public static int process(Node node, int level, int h){
        if (node == null){
            return 0;
        }
        if (level == h){
            return 1;
        }
        if (getLeftMost(node.right, level + 1) == h){
            return (1 << (h - level)) + process(node.right, level + 1, h);
        }else {
            return (1 << (h - level - 1)) + process(node.left, level + 1, h);
        }
    }

    public static int getLeftMost(Node node, int level){
        if (node == null){
            return 0;
        }
        while (node != null){
            level++;
            node = node.left;
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
        head.right.right = new Node(7);
        System.out.println(TreeNodeNumber(head)); // 7
    }
}
