package NowCoder.advanced_class.class_8;

/*
	环形链表的约瑟夫问题：
		输入：头节点head，报m个数
		输出：最后活下来的节点
		如果链表节点数为N，要求时间复杂度O(N)
 */
public class Code_03_JosephusProblem {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node josephusKill1(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		Node last = head;
		while (last.next != head) {
			last = last.next;
		}
		int count = 0;
		while (head != last) {
			if (++count == m) {
				last.next = head.next;
				count = 0;
			} else {
				last = last.next;
			}
			head = last.next;
		}
		return head;
	}

	// O(N)
	public static Node josephusKill2(Node head, int m) {
		if (head == null || head.next == head || m < 1) {
			return head;
		}
		Node cur = head.next;
		int tmp = 1; // tmp -> list size
		while (cur != head) {
			tmp++;
			cur = cur.next;
		}
		tmp = getLive(tmp, m); // tmp -> service node position
		while (--tmp != 0) {
			head = head.next;
		}
		head.next = head;
		return head;
	}

	public static int getLive(int i, int m) { // 长度为i，报到m杀人
		if (i == 1) {
			return 1;
		}
		return (getLive(i - 1, m) + m - 1) % i + 1;
	}

	public static void printCircularList(Node head) {
		if (head == null) {
			return;
		}
		System.out.print("Circular List: " + head.value + " ");
		Node cur = head.next;
		while (cur != head) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println("-> " + head.value);
	}

	// 约瑟夫问题公式
	public static int josephusKill3(Node head, int m){
		int n = 0;

		Node cur = head;
		while (cur.next != head){
			n++;
			cur = cur.next;
		}

		n += 1;

		int res = 0;
		for (int i = 2; i <= n; i++) {
			res = (res + m) % i;
		}

		return res + 1;
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = head1;
		printCircularList(head1); // Circular List: 1 2 3 4 5 -> 1
		head1 = josephusKill1(head1, 3);
		printCircularList(head1); // Circular List: 4 -> 4

		Node head2 = new Node(1);
		head2.next = new Node(2);
		head2.next.next = new Node(3);
		head2.next.next.next = new Node(4);
		head2.next.next.next.next = new Node(5);
		head2.next.next.next.next.next = head2;
		printCircularList(head2);
		head2 = josephusKill2(head2, 3); // Circular List: 1 2 3 4 5 -> 1
		printCircularList(head2); // Circular List: 4 -> 4


		Node head3 = new Node(1);
		head3.next = new Node(2);
		head3.next.next = new Node(3);
		head3.next.next.next = new Node(4);
		head3.next.next.next.next = new Node(5);
		head3.next.next.next.next.next = head3;
		System.out.println(josephusKill3(head3, 3));
	}

}