package NowCoder.LeetCode.Hot.exercise;

public class code10 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node removeKthNodeFromEnd(Node head, int k) {
        if (head == null) {
            return null;
        }

        Node newHead = new Node(Integer.MIN_VALUE);
        newHead.next = head;
        Node p1 = newHead;
        Node p2 = newHead;

        while (k >= 0 && p1 != null){
            k--;
            p1 = p1.next;
        }

        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        p2.next = p2.next.next;
        Node res = newHead.next;
        newHead.next = null;

        return res;
    }

    public static void print(Node head){
        if (head == null){
            return;
        }

        Node cur = head;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        print(head);
        Node res = removeKthNodeFromEnd(head, 3);
        print(res);

    }
}
