package NowCoder.basic_class.exercise.exercise02;

import NowCoder.basic_class.exercise.code20_PrintLinkedListCommonPart;

public class code20 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void PrintLinkedListCommonPart(Node head1, Node head2){
        if (head1 == null || head2 == null){
            return;
        }

        Node p1 = head1;
        Node p2 = head2;

        while (p1 != null && p2 != null){
            if (p1.value < p2.value){
                p1 = p1.next;
            }else if (p1.value > p2.value){
                p2 = p2.next;
            }else {
                System.out.print(p1.value + " ");
                p1 = p1.next;
                p2 = p2.next;
            }
        }
    }

    public static void printLinkedList(Node head) {
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
        Node node1 = new Node(1);
        node1.next = new Node(3);
        node1.next.next = new Node(5);

        Node node2 = new Node(2);
        node2.next = new Node(3);
        node2.next.next = new Node(4);
        node2.next.next.next = new Node(5);
        node2.next.next.next.next = new Node(7);

        printLinkedList(node1);
        printLinkedList(node2);
        PrintLinkedListCommonPart(node1, node2);
//        1 3 5
//        2 3 4 5 7
//        3 5
    }
}
