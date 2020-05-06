package NowCoder.advanced_class.class_5;

/*
	树的递归3：二叉树的最远距离
		二叉树中，一个节点可以往上走和往下走
		节点A到节点B的距离：A走到B最短路径上的节点个数
		求一个二叉树的最远距离
 */
public class Code_03_MaxDistanceInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	
	public static class ReturnType{
		public int maxDistance;
		public int h;
		
		public ReturnType(int m, int h) {
			this.maxDistance = m;;
			this.h = h;
		}
	}
	
	public static ReturnType process(Node head) {
		// base case
		if(head == null) {
			return new ReturnType(0,0);
		}

		// 先假设黑盒能够返回信息
		ReturnType leftReturnType = process(head.left);
		ReturnType rightReturnType = process(head.right);

		// 三种可能
		int includeHeadDistance = leftReturnType.h + rightReturnType.h + 1; // 3
		int p1 = leftReturnType.maxDistance; // 1
		int p2 = rightReturnType.maxDistance; // 2

		// 解黑盒：包含当前节点的最大距离和深度
		int p = Math.max(p1, p2);
		int resultDistance = Math.max(p, includeHeadDistance);

		int hitself  = Math.max(leftReturnType.h, rightReturnType.h) + 1;

		// 向上返回
		return new ReturnType(resultDistance, hitself);
	}

	// 返回结果
	public static int getMaxDistance(Node head){
		return process(head).maxDistance;
	}

	public static int maxDistance(Node head) {
		int[] record = new int[1];
		return posOrder(head, record);
	}

	public static int posOrder(Node head, int[] record) {
		if (head == null) {
			record[0] = 0;
			return 0;
		}
		int lMax = posOrder(head.left, record);
		int maxfromLeft = record[0];
		int rMax = posOrder(head.right, record);
		int maxFromRight = record[0];
		int curNodeMax = maxfromLeft + maxFromRight + 1;
		record[0] = Math.max(maxfromLeft, maxFromRight) + 1;
		return Math.max(Math.max(lMax, rMax), curNodeMax);
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
		System.out.println(maxDistance(head1));
		System.out.println(getMaxDistance(head1));

		Node head2 = new Node(1);
		head2.left = new Node(2);
		head2.right = new Node(3);
		head2.right.left = new Node(4);
		head2.right.right = new Node(5);
		head2.right.left.left = new Node(6);
		head2.right.right.right = new Node(7);
		head2.right.left.left.left = new Node(8);
		head2.right.right.right.right = new Node(9);
		System.out.println(maxDistance(head2));
		System.out.println(getMaxDistance(head2));

	}

}
