package NowCoder.basic_class.basic_class.class_04;

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
public class Code_06_IsBalancedTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class ReturnData {
        public boolean isB;
        public int h;

        public ReturnData(boolean isB, int h) {
            this.isB = isB;
            this.h = h;
        }
    }

    public static ReturnData process(Node head) {
        if (head == null) { // 空树，平衡
            return new ReturnData(true, 0);
        }

        // 向左递归
        ReturnData leftData = process(head.left);
        // 如果左子树不平衡，直接返回
        if (!leftData.isB) {
            return new ReturnData(false, 0);
        }

        // 向右递归
        ReturnData rightData = process(head.right);
        // 如果右子树不平衡，直接返回
        if (!rightData.isB) {
            return new ReturnData(false, 0);
        }

        // 左子树和右子树都平衡，看高度差
        if (Math.abs(leftData.h - rightData.h) > 1) {
            return new ReturnData(false, 0);
        }

        // 向上返回
        return new ReturnData(true, Math.max(leftData.h, rightData.h) + 1);
    }

    public static boolean isB(Node head) {
        return process(head).isB;
    }

//    public static boolean isBalance(Node head) {
//        boolean[] res = new boolean[1];
//        res[0] = true;
//        getHeight(head, 1, res);
//        return res[0];
//    }
//
//    public static int getHeight(Node head, int level, boolean[] res) {
//        if (head == null) {
//            return level;
//        }
//
//        // 向左递归，得到head的左子树高度
//        int lH = getHeight(head.left, level + 1, res);
//        if (!res[0]) {
//            return level;
//        }
//
//        // 向右递归，得到head的右子树高度
//        int rH = getHeight(head.right, level + 1, res);
//        if (!res[0]) {
//            return level;
//        }
//
//        if (Math.abs(lH - rH) > 1) {
//            res[0] = false;
//        }
//        return Math.max(lH, rH);
//    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

//        System.out.println(isBalance(head));
		System.out.println(isB(head));
    }

}
