package NowCoder.basic_class.basic_class.class_03;

import java.util.HashMap;

/*
	复制含有随机指针节点的链表：
		链表节点类有
			public Node rand; 可以指向任意一个节点或者null
		1. 使用Hash表：<key, value>
		2. 不使用Hash表
 */
public class Code_13_CopyListWithRandom {

	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int data) {
			this.value = data;
		}
	}

	// 使用哈希表：存放的是 节点--复制节点 的对应关系
	public static Node copyListWithRand1(Node head) {
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			// key -> cur节点
			// value -> 根据cur节点的值，新创一个节点
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			// 因为map中的节点的next和rand指针都没有指向谁
			// 根据原本的节点的next和rand可以设置map中的节点的指针
			map.get(cur).next = map.get(cur.next);
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		// 返回哈希表中存放的头节点
		return map.get(head);
	}

	// 不使用哈希表：O(1) 节点后连接复制节点
	public static Node copyListWithRand2(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		// copy node and link to every node
		while (cur != null) {
			next = cur.next;
			// 节点 -> 复制节点
			cur.next = new Node(cur.value);
			// TODO 复制节点 -> next节点 不光是当前节点后移，复制节点也要后移
			cur.next.next = next;
			cur = next;
		}
		// 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> null
		cur = head;
		Node curCopy = null;
		// set copy node rand 设置rand节点
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			// cur.rand.next：因为当前链表格式是 cur -> cur'，所以要把复制节点的rand指向复制节点，也就是cur.rand.next
			curCopy.rand = cur.rand != null ? cur.rand.next : null;
			cur = next;
		}
		// 复制的头节点
		Node res = head.next;
		cur = head;
		// split分离
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			cur.next = next;
			curCopy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
	}

	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

	}

}
