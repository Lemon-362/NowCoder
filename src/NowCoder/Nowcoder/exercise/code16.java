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

        Node n1 = head1;
        Node n2 = head2;
        Node head = null;
        Node cur = null;

        while (n1 != null && n2 != null){
            if (n1.value < n2.value){
                if (head == null){
                    head = n1;
                    cur = n1;
                }else {
                    cur.next = n1;
                    cur = n1;
                }
                n1 = n1.next;
            }else {
                if (head == null){
                    head = n2;
                    cur = n2;
                }else {
                    cur.next = n2;
                    cur = n2;
                }
                n2 = n2.next;
            }
        }

        if (n1 != null){
            cur.next = n1;
        }
        if (n2 != null){
            cur.next = n2;
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
