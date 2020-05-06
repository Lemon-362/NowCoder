package NowCoder.advanced_class.class_5;

/*
	树的递归1：最大搜索二叉子树 --> 任何一个子树,其头节点>左子树的max,头节点<右子树的min
		给定一个二叉树的头节点head，返回最大搜索二叉子树的大小
		也就是说这个二叉树本身可能不是搜索二叉树，需要在其中找到最大的搜索二叉树

	// TODO 树的套路：题目要求整棵树的。。。，那么就通过递归求解以每个节点为头的子树的。。。，答案一定在其中
	// TODO 树的递归流程：
	    1. 先以每个节点为头，找题目要求的条件，列出所有可能性
	    2. 根据可能性，列出所有需要的信息
	    3. 将向左向右递归返回的信息都整合成结构相同的信息，封装成一个类（黑盒）
	    4. 改递归：
	        先假设下级能够返回给我信息（直接根据类写出下级返回信息的代码）
	        然后我要根据下级返回的信息和列出的可能性进行加工，使得后续能够返回所有可能性的值
	        最后根据可能性的值，包装信息，把当前节点作为下级，向上返回（解黑盒）
 */

/*
        求整棵树的最大BST --> 以每个节点为头的子树的最大BST
        1. 可能性分析：
            1）最大BST在该节点的左子树上
            2）最大BST在该节点的右子树上
            3）最大BST是包含该节点的整棵子树
            --> 整个左子树是BST，整个右子树是BST，
                且左子树的头节点是该节点的左孩子，右子树的头节点是该节点的右孩子，
                且头节点>左子树的max，头节点<右子树的min
        2. 列出所需信息：
            1）左子树最大BST的大小
            2）右子树最大BST的大小
            3）左子树最大BST的头节点
               右子树最大BST的头节点
               左子树的max
               右子树的min
               --> 只有当左子树最大BST的头节点是该节点，且头节点>左子树的max时，包含该节点的整个子树才是BST
        3. 整合信息，封装成类：
            1）一棵子树最大BST的大小
            2）一棵子树的头节点
            3）一棵子树上的max
            4）一棵子树上的min
        4. 改递归
     */
public class Code_01_BiggestSubBSTInTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 递归返回的信息
    public static class ReturnType {
        public int size;
        public Node head;
        public int min;
        public int max;

        public ReturnType(int a, Node b, int c, int d) {
            this.size = a;
            this.head = b;
            this.min = c;
            this.max = d;
        }
    }

    // 递归
    public static ReturnType process(Node head) {
        if (head == null) { // 空树的信息
            return new ReturnType(0, null, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // TODO 先直接假设下级能够返回给我信息
        // 左右子树返回信息：黑盒

        // 左子树返回的4个信息
//        Node left = head.left;
        ReturnType leftSubTressInfo = process(head.left);

        // 右子树返回的4个信息
//        Node right = head.right;
        ReturnType rightSubTressInfo = process(head.right);

        // TODO 然后我要根据下级返回的信息和列出的可能性进行加工，使得后续能够返回所有可能性的值
        // 三种可能：以该节点为头的树，如何加工这些信息得到结果

        // 可能（1）：左子树最大BST大小
        int p1 = leftSubTressInfo.size;

        // 可能（2）：右子树最大BST大小
        int p2 = rightSubTressInfo.size;

        // 可能（3）
        int includeItSelf = 0;
        if (leftSubTressInfo.head == head.left  // 左子树最大BST的头部是该节点的左孩子
                && rightSubTressInfo.head == head.right // 右子树最大BST的头部是该节点的右孩子
                // 此时左右子树全是BST，没有多余的节点
                // 只需要判断该头节点加进去，是否满足BST
                && head.value > leftSubTressInfo.max // 该节点 ＞ 左子树max
                && head.value < rightSubTressInfo.min // 该节点 ＜ 右子树min
        ) {
            includeItSelf = leftSubTressInfo.size + 1 + rightSubTressInfo.size; // 左 + 头 + 右
        }

        // TODO 最后根据可能性的值，包装信息，把当前节点作为下级，向上返回
        // 解黑盒：我自己也要向上返回信息

        // 包括该节点的最大BST的大小
        int maxSize = Math.max(Math.max(p1, p2), includeItSelf);

        // 包括该节点的最大BST的头部
        Node maxHead = p1 > p2 ? leftSubTressInfo.head : rightSubTressInfo.head;
        if (maxSize == includeItSelf) { // 如果是可能（3），最大BST的头部就是该节点
            maxHead = head;
        }

        // 包装信息，向上返回
        return new ReturnType(maxSize,
                maxHead,
                Math.min(Math.min(leftSubTressInfo.min, rightSubTressInfo.min), head.value), // min，左、右、该节点
                Math.max(Math.max(leftSubTressInfo.max, rightSubTressInfo.max), head.value));     // max，左、右、该节点
    }

    public static Node biggestSubBST(Node head) {
        int[] record = new int[3]; // 0->size, 1->min, 2->max
        return posOrder(head, record);
    }

    public static Node posOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }
        int value = head.value;
        Node left = head.left;
        Node right = head.right;
        Node lBST = posOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];
        Node rBST = posOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];
        record[1] = Math.min(rMin, Math.min(lMin, value)); // lmin, value, rmin -> min
        record[2] = Math.max(lMax, Math.max(rMax, value)); // lmax, value, rmax -> max
        if (left == lBST && right == rBST && lMax < value && value < rMin) {
            record[0] = lSize + rSize + 1;
            return head;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {

        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

        printTree(head);
//		Node bst = biggestSubBST(head);
//		printTree(bst);
        Node bst = process(head).head;
        printTree(bst);

    }

}
