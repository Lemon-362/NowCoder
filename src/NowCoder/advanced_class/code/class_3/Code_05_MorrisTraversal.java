package NowCoder.advanced_class.code.class_3;

/*
	Morris遍历：
		可以保证 时间复杂度O(N)，空间复杂度O(1)
		而一半的二叉树遍历 时间复杂度O(N)，空间复杂度O(h) h：二叉树的高度
		Morris的先序、中序、后序遍历

	一节点没有左孩子：只会到达该节点一次
	一节点有左孩子：会到达两次
		第一次：左子树最右节点==null --> 先序
		第二次：左子树最右节点==cur --> 中序

	后序：在能够到达两次且是第二次到达时，逆序打印左子树右边界。最后单独打印整棵树的右边界
 */
public class Code_05_MorrisTraversal {
	// 普通的先序中序后序遍历
	public static void process(Node head) {
		if(head == null) {
			return;
		}
		
		// 1
		//System.out.println(head.value);
		
		
		process(head.left);
		
		// 2
		//System.out.println(head.value);
		
		
		process(head.right);
		
		// 3
		//System.out.println(head.value);
	}


	public static class Node {
		private int value;
		private Node left;
		private Node right;

		public Node(int value) {
			this.value = value;
		}
	}

	public static void morrisPre(Node head){
		if (head == null){
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null){
			mostRight = cur.left;
			if (mostRight != null){ // cur有左孩子
				while (mostRight.right != null && mostRight.right != cur){ // 找左子树上最右的节点（后继节点）
					mostRight = mostRight.right;
				}
				if (mostRight.right == null){ // 最右节点指向null，第一次到达cur
					mostRight.right = cur; // 第一次到达cur
					// TODO 先序：打印时刻放在第一次到达cur
					System.out.print(cur.value + " ");
					cur = cur.left;
				}else { // 最右节点指向cur，第二次到达cur
					mostRight.right = null;
					cur = cur.right;
				}
			}else { // cur没有左孩子，只到达一次cur
				System.out.print(cur.value + " ");
				cur = cur.right;
			}
		}
		System.out.println();
	}

	public static void morrisIn(Node head){
		if (head == null){
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null){
			mostRight = cur.left;
			if (mostRight != null){ // cur有左孩子
				while (mostRight.right != null && mostRight.right != cur){ // 找左子树上最右的节点
					mostRight = mostRight.right;
				}
				if (mostRight.right == null){ // 最右节点指向null，第一次到达cur
					mostRight.right = cur; // 第一次到达cur
					cur = cur.left;
				}else { // 最右节点指向cur，第二次到达cur
					mostRight.right = null;
					// TODO 中序：打印时刻放在第二次到达cur
					System.out.print(cur.value + " ");
					cur = cur.right;
				}
			}else { // cur没有左孩子，只到达一次cur
				System.out.print(cur.value + " ");
				cur = cur.right;
			}
		}
		System.out.println();
	}

	public static void morrisPos(Node head){
		if (head == null){
			return;
		}
		Node cur = head;
		Node mostRight = null;
		while (cur != null){
			mostRight = cur.left;
			if (mostRight != null){ // cur有左孩子
				while (mostRight.right != null && mostRight.right != cur){ // 找左子树上最右的节点
					mostRight = mostRight.right;
				}
				if (mostRight.right == null){ // 最右节点指向null，第一次到达cur
					mostRight.right = cur; // 第一次到达cur
					cur = cur.left;
				}else { // 最右节点指向cur，第二次到达cur
					mostRight.right = null;
					// TODO 后序：能够到达两次，且是第二次到达时，逆序打印左子树右边界
					printRightEdge(cur.left);
					cur = cur.right;
				}
			}else { // cur没有左孩子，只到达一次cur
				cur = cur.right;
			}
		}
		// TODO 逆序打印整棵树右边界
		printRightEdge(head);
		System.out.println();
	}

	// 逆序打印
	public static void printRightEdge(Node node){
		Node head = reverseRight(node);
		Node cur = head;
		while (cur != null){
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		// 再逆序回来
		reverseRight(head);
	}

	// 对右子树逆序
	public static Node reverseRight(Node node){
		Node pre = null;
		Node next = null;
		while (node != null){
			next = node.right;

			node.right = pre;

			pre = node;
			node = next;
		}
		return pre;
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
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
		printTree(head);
		morrisIn(head); // 1 2 3 4 5 6 7
		morrisPre(head); // 4 2 1 3 6 5 7
		morrisPos(head); // 1 3 2 5 7 6 4
		printTree(head);

	}

}
