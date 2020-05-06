package NowCoder.basic_class.basic_class.class_03;

/*
	给定一个头节点和一个值num，调整链表节点，实现左边均是小于num的，中间是等于num的，右边均是大于num的，顺序没有要求。
		1. 将节点放在数组中，用荷兰国旗问题调整，然后重新连接成链表
		——缺点：不稳定（调整前后节点前后顺序不一样）
		2. 稳定：要求调整前后节点前后顺序不变
 */
public class Code_12_SmallerEqualBigger {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node listPartition1(Node head, int pivot) {
		if (head == null) {
			return head;
		}
		Node cur = head;
		int i = 0;
		// 统计节点个数
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		// 根据节点个数，创建Node类型的数组
		Node[] nodeArr = new Node[i];
		i = 0;
		cur = head;
		// 将节点放在数组中
		for (i = 0; i != nodeArr.length; i++) {
			nodeArr[i] = cur;
			cur = cur.next;
		}
		// 荷兰国旗问题
		arrPartition(nodeArr, pivot);
		// 根据排好后的数组，重新连接成链表
		for (i = 1; i != nodeArr.length; i++) {
			nodeArr[i - 1].next = nodeArr[i];
		}
		nodeArr[i - 1].next = null;
		// 返回头节点
		return nodeArr[0];
	}

	public static void arrPartition(Node[] nodeArr, int pivot) {
		int small = -1;
		int big = nodeArr.length;
		int index = 0;
		while (index != big) {
			if (nodeArr[index].value < pivot) {
				swap(nodeArr, ++small, index++);
			} else if (nodeArr[index].value == pivot) {
				index++;
			} else {
				swap(nodeArr, --big, index);
			}
		}
	}

	public static void swap(Node[] nodeArr, int a, int b) {
		Node tmp = nodeArr[a];
		nodeArr[a] = nodeArr[b];
		nodeArr[b] = tmp;
	}

	// 稳定的：拆分成三个链表：less，equal，more，遍历链表，分别连在对应的链表上，然后less->equal->more
	public static Node listPartition2(Node head, int pivot) {
		// 三个链表用两个头节点，一个用于连接后续的节点，一个用于返回
		// less
		Node sH = null; // small head
		Node sT = null; // small tail
		// equal
		Node eH = null; // equal head
		Node eT = null; // equal tail
		// more
		Node bH = null; // big head
		Node bT = null; // big tail
		// 保存next指针
		Node next = null; // save next node
		// every node distributed to three lists
		// 遍历链表，分别将节点接到less，equal，more后面
		// 首先要找到第一个小于，等于，大于num的节点，分别作为三个的头节点
		while (head != null) {
			next = head.next;
			head.next = null; // 每次都要断开next指针
			if (head.value < pivot) { // less
				if (sH == null) { // 头节点
					sH = head;
					sT = head;
				} else { // 后续节点
					sT.next = head;
					sT = head;
				}
			} else if (head.value == pivot) { // equal
				if (eH == null) {
					eH = head;
					eT = head;
				} else {
					eT.next = head;
					eT = head;
				}
			} else { // more
				if (bH == null) {
					bH = head;
					bT = head;
				} else {
					bT.next = head;
					bT = head;
				}
			}
			head = next;
		}
		// small and equal reconnect
		// 连接less，equal，more
		if (sT != null) {
			sT.next = eH;
			// TODO 边界问题
			eT = eT == null ? sT : eT; // eT为空，说明只有一个头节点eH，那么small和equal连接后，头节点为sT
		}
		// all reconnect
		if (eT != null) {
			eT.next = bH;
		}

		if (sH!=null){
			return sH;
		}else {
			if (eH!=null){
				return eH;
			}else {
				return bH;
			}
		}
//		return sH != null ? sH : eH != null ? eH : bH;
	}

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
//		 head1 = listPartition1(head1, 5); // 2 1 5 5 8 9 7
		head1 = listPartition2(head1, 5);
		printLinkedList(head1); // 1 2 5 5 7 9 8

	}

}
