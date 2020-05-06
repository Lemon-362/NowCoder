package NowCoder.basic_class.basic_class.class_03;

/*
	两个单链表相交的一系列问题：
		1. 判断是否有环
		2. 判断是否相交
		3. 若相交，返回相交的第一个节点
	综合起来：判断两个单链表是否相交，若相交，返回相交的第一个节点
 */
public class Code_14_FindFirstIntersectNode {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		// 首先判断是否有环，返回相应的节点
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		// 两个链表均无环
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		// 均有环
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop1, head2, loop2);
		}
		// 一个有环，一个无环
		return null;
	}

	// 判断是否有环
	// 有环，返回入环头节点loop1，loop2
	// 无环，返回null
	// 方法：用哈希表 / 用快慢指针
	public static Node getLoopNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		// 直接从head的后一个节点开始
		Node n1 = head.next; // n1 -> slow
		Node n2 = head.next.next; // n2 -> fast
		// 快慢指针，若有环，快慢指针一定会相遇
		// 此时快指针回到开头，快指针走1步，快慢一定会在第一个入环节点相遇
		while (n1 != n2) {
			if (n2.next == null || n2.next.next == null) {
				return null;
			}
			n2 = n2.next.next;
			n1 = n1.next;
		}
		n2 = head; // n2 -> walk again from head
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}

	// 两个无环链表的相交节点：Map / 不用Map
	public static Node noLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node cur1 = head1;
		Node cur2 = head2;
		int n = 0;
		// 遍历链表1和2，得到链表长度和最后一个节点 len1, len2, end1, end2
		// 若end1 != end2, 则不相交
		// 若end1 == end2, 则相交，若len1 > len2, 则让1先走(len2 - len1)步，然后1和2一起走，得到第一个相交节点
		// TODO 这里用next来判断，因为要求长度n，否则n会多一个
		while (cur1.next != null) {
			n++;
			cur1 = cur1.next;
		}
		while (cur2.next != null) {
			n--;
			cur2 = cur2.next;
		}

		if (cur1 != cur2) {
			return null;
		}
		// 先走的那一个链表
		cur1 = n > 0 ? head1 : head2; // 1或2的头节点，n>0就是1
		// 后走的那一个链表
		cur2 = cur1 == head1 ? head2 : head1; // 另一个的头节点
		n = Math.abs(n);
		while (n != 0) {
			n--; // 差值
			cur1 = cur1.next; // 先走
		}
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

	// 两个均有环的链表的相交节点
	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		// 第一个入环节点相同，那么不可能在环内相交，之前必相交 => 无环相交
		if (loop1 == loop2) {
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			// TODO 注意，这里只需要遍历到入环节点
			while (cur1 != loop1) {
				n++;
				cur1 = cur1.next;
			}
			while (cur2 != loop2) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while (n != 0) {
				n--;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		} else { // 入环节点不同，用loop1.next遍历链表，若遇到自己，则不相交，若遇到loop2，则返回当前loop1
			cur1 = loop1.next;
			while (cur1 != loop1) {
				if (cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}

}
