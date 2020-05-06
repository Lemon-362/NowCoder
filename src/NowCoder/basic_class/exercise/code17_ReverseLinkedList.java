package NowCoder.basic_class.exercise;

/*
	反转单向链表和双向链表
	    next
	    last next
 */

public class code17_ReverseLinkedList {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node reverseSingleLinkedList(Node head) {
        if (head == null){
            return null;
        }
        Node next = null;
        Node pre = null;
        while (head != null){
            next = head.next;

            head.next = pre;

            pre = head;
            head = next;
        }
        return pre;
    }

    public static void printSingleLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static class DoubleNode {
        private int data;
        private DoubleNode next;
        private DoubleNode last;

        public DoubleNode(int data) {
            this.data = data;
        }
    }

    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        if (head == null){
            return null;
        }
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null){
            next = head.next;

            head.next = pre;
            head.last = next;

            pre = head;
            head = next;
        }
        return pre;
    }

    public static void printDoubleLinkedList(DoubleNode head) {
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.data + " ");
            end = head;
            head = head.next;
        }
        System.out.print(" | ");
        while (end != null) {
            System.out.print(end.data + " ");
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
