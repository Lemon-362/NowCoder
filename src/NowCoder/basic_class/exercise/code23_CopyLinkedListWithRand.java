package NowCoder.basic_class.exercise;

import NowCoder.basic_class.basic_class.class_03.Code_13_CopyListWithRandom;

import java.util.HashMap;

/*
	复制含有随机指针节点的链表：
		链表节点类有
			public Node rand; 可以指向任意一个节点或者null
		1. 使用Hash表：<key, value>
		2. 不使用Hash表   当前节点后紧跟复制节点，先设置rand，然后断开当前和复制，设置next
 */
public class code23_CopyLinkedListWithRand {
    public static class Node {
        private int data;
        private Node next;
        private Node rand;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node copyWithHashMap(Node head) {
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            hashMap.put(cur, new Node(cur.data));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            hashMap.get(cur).next = hashMap.get(cur.next);
            hashMap.get(cur).rand = hashMap.get(cur.rand);
            cur = cur.next;
        }
        return hashMap.get(head);
    }

    public static Node copyWithoutHashMap(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;

            cur.next = new Node(cur.data);
            cur.next.next = next;

            cur = next;
        }
        // 先rand再next
        cur = head;
        while (cur != null){
            next = cur.next.next;

            cur.next.rand = cur.rand == null ? null : cur.rand.next;

            cur = next;
        }
        cur = head;
        Node res = cur.next;
        Node copy = null;
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;

            cur.next = next;
            copy.next = cur.next == null? null : cur.next.next;

            cur = next;
        }
        return res;
    }

    public static void print(Node head) {
        Node cur = head;
        System.out.println("链表：");
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.println("rand：");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;

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

        print(head); // 1 2 3 4 5 6   rand 6 6 5 3 - 4
        System.out.println("==============");
        res1 = copyWithHashMap(head);
        print(res1); // 1 2 3 4 5 6      rand: 6 6 5 3 - 4
        System.out.println("===============");
        res2 = copyWithoutHashMap(head);
        print(res2);
    }
}
