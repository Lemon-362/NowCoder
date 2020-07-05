package NowCoder.Nowcoder.exercise;

import java.util.HashMap;

public class code25 {
    public static class Node {
        private int value;
        private Node next;
        private Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node copyWithHashMap(Node head){
        if (head == null){
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>();

        Node cur = head;
        while (cur != null){
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        cur = head;
        while (cur != null){
            map.get(cur).rand = map.get(cur.rand);
            map.get(cur).next = map.get(cur.next);
            cur = cur.next;
        }

        return map.get(head);
    }

    public static Node copyWithoutHashMap(Node head){
        if (head == null){
            return null;
        }

        Node cur = head;
        Node next = null;
        while (cur != null){
            next = cur.next;

            cur.next = new Node(cur.value);
            cur.next.next = next;

            cur = next;
        }

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
            copy.next = cur.next == null ? null : cur.next.next;

            cur = next;
        }

        return res;
    }

    public static void print(Node head) {
        Node cur = head;
        System.out.println("链表：");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.println("rand：");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
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
