package NowCoder.basic_class.basic_class.class_03;

/*
	反转单向链表和双向链表
 */
public class Code_07_ReverseList {
	// TODO 单链表
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next; // 先保存head的next指针
			// next指针反转
			head.next = pre; // 头节点的pre == null，将head的next指针指向pre
			// 更新pre和head
			pre = head; // pre指向当前节点，使得下一个节点可以指向这个
			head = next; // 下一个节点
		}
		// 返回头节点
		return pre;
	}

	// TODO 双向链表
	public static class DoubleNode {
		public int value;
		public DoubleNode last;
		public DoubleNode next;

		public DoubleNode(int data) {
			this.value = data;
		}
	}

	public static DoubleNode reverseList(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while (head != null) {
			// 临时保存next指针
			next = head.next;
			// next和last反转
			head.next = pre;
			head.last = next;
			// 更新pre和head
			pre = head;
			head = next;
		}
		return pre;
	}

	public static void printLinkedList(Node head) {
		System.out.print("Linked List: ");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void printDoubleLinkedList(DoubleNode head) {
		System.out.print("Double Linked List: ");
		DoubleNode end = null;
		while (head != null) {
			System.out.print(head.value + " ");
			end = head;
			head = head.next;
		}
		System.out.print("| ");
		while (end != null) {
			System.out.print(end.value + " ");
			end = end.last;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		printLinkedList(head1); // 1 2 3
		head1 = reverseList(head1);
		printLinkedList(head1); // 3 2 1

		DoubleNode head2 = new DoubleNode(1);
		head2.next = new DoubleNode(2);
		head2.next.last = head2;
		head2.next.next = new DoubleNode(3);
		head2.next.next.last = head2.next;
		head2.next.next.next = new DoubleNode(4);
		head2.next.next.next.last = head2.next.next;
		printDoubleLinkedList(head2); // 1 2 3 4 | 4 3 2 1
		printDoubleLinkedList(reverseList(head2)); // 4 3 2 1 | 1 2 3 4

	}

}
