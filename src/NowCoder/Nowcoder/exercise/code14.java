package NowCoder.Nowcoder.exercise;

import java.util.Stack;

public class code14 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node findKthToTail(Node head, int k){
        if (head == null){
            return null;
        }

        Node cur = head;
        int len = 0;

        while (cur != null){
            len++;
            cur = cur.next;
        }

        for (int i = 0; i < len - k; i++) {
            head = head.next;
        }

        return head;
    }

    public static Node findKthToTail01(Node head, int k){
        if (head == null){
            return null;
        }

        Node p1 = head;
        Node p2 = head;

        while (k > 0){
            p1 = p1.next;
            k--;
        }

        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(5);
        head.next.next = new Node(1);
        head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(8);

        Node res1 = findKthToTail(head, 1);
        System.out.println(res1.value);
        res1 = findKthToTail(head, 2);
        System.out.println(res1.value);
        res1 = findKthToTail(head, 3);
        System.out.println(res1.value);
        res1 = findKthToTail(head, 4);
        System.out.println(res1.value);
        res1 = findKthToTail(head, 5);
        System.out.println(res1.value);

        System.out.println("***********************");

        Node res2 = findKthToTail01(head, 1);
        System.out.println(res2.value);
        res2 = findKthToTail01(head, 2);
        System.out.println(res2.value);
        res2 = findKthToTail01(head, 3);
        System.out.println(res2.value);
        res2 = findKthToTail01(head, 4);
        System.out.println(res2.value);
        res2 = findKthToTail01(head, 5);
        System.out.println(res2.value);
    }
}
