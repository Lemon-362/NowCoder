package NowCoder.basic_class.Tags.StackQueueList;

import java.util.Stack;

public class IsHuiWenLinkedList {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean method01(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node cur = head;
        Stack<Node> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public static boolean method02(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node n1 = head.next;
        Node n2 = head.next.next;

        while (n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }

        n2 = n1.next;
        n1.next = null;
        Node n3 = null;

        while (n2 != null){
            n3 = n2.next;

            n2.next = n1;

            n1 = n2;
            n2 = n3;
        }

        n3 = n1;
        n2 = head;
        boolean res = true;

        while (n1 != null && n2 != null){
            if (n1.value != n2.value){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        while (n3 != null){
            n2 = n3.next;

            n3.next = n1;

            n1 = n3;
            n3 = n2;
        }

        return res;
    }

    public static void print(Node headNode) {
        while (headNode != null) {
            System.out.print(headNode.value + " ");
            headNode = headNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(3);
        head.next.next = new Node(5);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(1);

        print(head);
//        System.out.println(method01(head));
        System.out.println(method02(head));
        print(head);

    }
}
