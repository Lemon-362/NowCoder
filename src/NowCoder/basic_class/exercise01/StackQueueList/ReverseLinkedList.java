package NowCoder.basic_class.exercise01.StackQueueList;

import NowCoder.basic_class.exercise.code17_ReverseLinkedList;

public class ReverseLinkedList {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node reverseSingleLinkedList(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        Node pre = null;
        while (cur != null) {
            next = cur.next;

            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void printSingleLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static class DoubleNode {
        private int value;
        private DoubleNode next;
        private DoubleNode last;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        DoubleNode cur = head;
        DoubleNode next = null;
        DoubleNode pre = null;
        while (cur != null) {
            next = cur.next;

            cur.next = pre;
            cur.last = next;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void printDoubleLinkedList(DoubleNode head) {
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.next;
        }
        System.out.print(" | ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.last;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printSingleLinkedList(head);
        printSingleLinkedList(reverseSingleLinkedList(head));
//        1 2 3
//        3 2 1

        DoubleNode head1 = new DoubleNode(1);

        head1.next = new DoubleNode(2);
        head1.next.last = head1;

        head1.next.next = new DoubleNode(3);
        head1.next.next.last = head1.next;

        head1.next.next.next = new DoubleNode(4);
        head1.next.next.next.last = head1.next.next;

        printDoubleLinkedList(head1);
        printDoubleLinkedList(reverseDoubleLinkedList(head1));
        // 1 2 3 4 |4 3 2 1
        // 4 3 2 1 |1 2 3 4
    }
}
