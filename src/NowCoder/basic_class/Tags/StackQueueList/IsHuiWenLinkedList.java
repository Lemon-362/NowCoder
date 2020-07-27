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

        Stack<Node> stack = new Stack<>();
        Node cur = head;

        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        cur = head;
        while (!stack.isEmpty()){
            if (stack.pop().value != cur.value){
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

        Node p1 = head;
        Node p2 = head.next;

        while (p2.next != null && p2.next.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }

        Node p3 = p1.next;
        p1.next = null;

        while (p3 != null){
            p2 = p3.next;

            p3.next = p1;

            p1 = p3;
            p3 = p2;
        }

        p3 = p1;
        p2 = head;
        boolean res = true;

        while (p1 != null && p2 != null){
            if (p1.value != p2.value){
                res = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        while (p3 != null){
            p1 = p3.next;

            p3.next = p2;

            p2 = p3;
            p3 = p1;
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
        System.out.println(method01(head));
//        System.out.println(method02(head));
        print(head);

    }
}
