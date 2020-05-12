package NowCoder.basic_class.exercise.exercise02;

import NowCoder.basic_class.exercise.code21_IsHuiWenLinkedList;

import java.util.Stack;

public class code21 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isHuiWen01(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;

        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        cur = head;
        boolean res = true;

        while (cur != null) {
            if (cur.value != stack.pop().value) {
                res = false;
                break;
            }
            cur = cur.next;
        }

        return res;
    }

    public static boolean isHuiWen02(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node n1 = head.next;
        Node n2 = head.next.next;

        while (n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }

        Node n3 = null;
        n2 = n1.next;
        n1.next = null;

        while (n2 != null){
            n3 = n2.next;

            n2.next = n1;

            n1 = n2;
            n2 = n3;
        }

        n3 = n1;
        n2 = head;
        boolean res = true;

        while (n2 != null && n3 != null){
            if (n2.value != n3.value){
                res = false;
                break;
            }
            n2 = n2.next;
            n3 = n3.next;
        }

        while (n1 != null){
            n3 = n1.next;

            n1.next = n2;

            n2 = n1;
            n1 = n3;
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
//        System.out.println(isHuiWen01(head));
        System.out.println(isHuiWen02(head));
        print(head);
    }
}
