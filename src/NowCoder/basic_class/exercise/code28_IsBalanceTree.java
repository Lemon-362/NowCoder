package NowCoder.basic_class.exercise;

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

public class code28_IsBalanceTree {
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static class ReturnData {
        private boolean isB;
        private int height;

        public ReturnData(boolean isB, int height) {
            this.isB = isB;
            this.height = height;
        }
    }

    public static ReturnData process(Node head){
        if (head == null){
            return new ReturnData(true, 0);
        }

        ReturnData left = process(head.left);
        if (!left.isB){
            return new ReturnData(false, 0);
        }

        ReturnData right = process(head.right);
        if (!right.isB){
            return new ReturnData(false, 0);
        }

        if (Math.abs(left.height - right.height) > 1){
            return new ReturnData(false, 0);
        }

        return new ReturnData(true, Math.max(left.height, right.height) + 1);
    }

    public static boolean isBalance(Node head){
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

        System.out.println(isBalance(head));
    }
}
