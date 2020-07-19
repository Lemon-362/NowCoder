package NowCoder.Addition;

/**
 * 一个链表，奇数位升序,偶数位降序，让链表整个变成升序的
 *
 *  思路：按照位置拆分成两个链表，对偶数位的链表反转，然后合并两个有序链表
 *
 */
public class LinkedListSort {
        public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node linkedListSort1(Node head){
        if (head == null){
            return null;
        }

        // 偶数位
        Node head1 = null;
        // 奇数位
        Node head2 = null;
        Node cur1 = null;
        Node cur2 = null;
        Node cur = head;

        int index = 1;

        while (cur != null){
            if (index % 2 == 0){
                if (head1 == null){
                    head1 = cur;
                    cur1 = cur;
                }else {
                    cur1.next = cur;
                    cur1 = cur1.next;
                }
            }else {
                if (head2 == null){
                    head2 = cur;
                    cur2 = cur;
                }else {
                    cur2.next = cur;
                    cur2 = cur2.next;
                }
            }

            index++;
            cur = cur.next;
        }

        cur1.next = null;
        cur2.next = null;

        // 对偶数位head1反转链表
        head1 = reverse(head1);

        // 合并两个有序链表
        Node newHead = merge02(head1, head2);

        return newHead;
    }

    public static Node merge02(Node head1, Node head2) {
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

    public static Node reverse(Node head){
        if (head == null) {
            return null;
        }

        Node cur = head;
        Node pre = null;
        Node next = null;

        while (cur != null){
            next = cur.next;

            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return pre;
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

        Node head = new Node(1);
        head.next = new Node(8);
        head.next.next = new Node(3);
        head.next.next.next = new Node(6);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.next.next = new Node(9);

        printList(head); // 1 8 3 6 5 4 7 2 9

        printList(linkedListSort1(head)); // 1 2 3 4 5 6 7 8 9

    }
}
