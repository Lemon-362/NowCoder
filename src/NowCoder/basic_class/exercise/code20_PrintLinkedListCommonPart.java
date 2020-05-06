package NowCoder.basic_class.exercise;

/*
	给定两个链表的头节点，打印两个有序链表的公共部分：
		外排的方式，谁小谁动，相等打印
 */
public class code20_PrintLinkedListCommonPart {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void PrintLinkedListCommonPart(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                head1 = head1.next;
            } else if (head1.data > head2.data) {
                head2 = head2.next;
            } else {
                System.out.print(head1.data + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    public static void printLinkedList(Node head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.print(head.data + " ");
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
