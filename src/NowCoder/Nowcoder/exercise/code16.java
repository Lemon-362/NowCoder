package NowCoder.Nowcoder.exercise;

import NowCoder.Nowcoder.code16_MergeTwoLinkedList;

public class code16 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 外排思想
    public static Node merge01(Node head1, Node head2){
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        Node p1 = head1;
        Node p2 = head2;
        Node head = null;
        Node cur = null;

        while (p1 != null && p2 != null){
            if (p1.value < p2.value){
                if (head == null){
                    head = p1;
                    cur = p1;
                }else {
                    cur.next = p1;
                    cur = p1;
                }
                p1 = p1.next;
            }else {
                if (head == null){
                    head = p2;
                    cur = p2;
                }else {
                    cur.next = p2;
                    cur = p2;
                }
                p2 = p2.next;
            }
        }

        if (p1 != null){
            cur.next = p1;
        }

        if (p2 != null){
            cur.next = p2;
        }

        return head;
    }

    // 递归
    public static Node merge02(Node head1, Node head2){
        // base case
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        Node head = null;
        if (head1.value < head2.value){
            head = head1;
            head.next = merge02(head1.next, head2);
        }else {
            head = head2;
            head.next = merge02(head1, head2.next);
        }

        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(3);
        head1.next.next = new Node(10);
        head1.next.next.next = new Node(20);

        Node head2 = new Node(4);
        head2.next = new Node(5);
        head2.next.next = new Node(6);
        head2.next.next.next = new Node(7);

        printList(merge01(head1, head2)); // 1 3 4 5 6 7 10 20
//        printList(merge02(head1, head2));
    }
}
