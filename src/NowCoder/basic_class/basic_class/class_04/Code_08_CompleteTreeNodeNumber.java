package NowCoder.basic_class.basic_class.class_04;

/*
	已知一颗完全二叉树，求其节点的个数：时间复杂度O[(logn)^2]
		结论：满二叉树，高度为L，则其节点个数为 2^L-1
	1. 完全二叉树，遍历左子树边界，可知数的高度 h总
	2. 遍历头节点右孩子的左子树边界，
		1) 若 边界到达h总，则头节点右孩子的左子树必满，高度=h总-1
		2) 若 边界没有到达h总，则头节点右孩子的右子树必满，高度=右孩子的左子树满是的高度-1
	3. 递归求右(1)/左(2)子树的节点个数
 */
public class Code_08_CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	public static int bs(Node node, int l, int h) { // l：当前高度，h：总高度
		if (l == h) { // 递归终止条件
			return 1;
		}
		// 如果从第二层开始算左子树高度，等于h总 --> (1)
		if (mostLeftLevel(node.right, l + 1) == h) { // 右子树左边界，当前高度为l+1
			return (1 << (h - l)) + bs(node.right, l + 1, h); // h-l：h总-当前高度，也就是右孩子左子树高度
		} else {
			return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
		}
	}

	// h总，算的是高度
	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
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
		System.out.println(nodeNum(head));

	}

}
