package NowCoder.Nowcoder.exercise;

import java.util.ArrayList;
import java.util.Stack;

public class code03 {
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static ArrayList<Integer> reversePrint(Node head) {
        if (head == null) {
            return null;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;

        while (cur != null) {
            stack.push(cur);

            cur = cur.next;
        }

        ArrayList<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop().value);
        }

        return res;
    }

    // 翻转单向链表
    public static ArrayList<Integer> reversePrint2(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        Node pre = null;
        Node next = null;

        while (cur != null) {
            next = cur.next;

            cur.next = pre;

            pre = cur;
            cur = next;
        }

        ArrayList<Integer> res = new ArrayList<>();
        while (pre != null) {
            res.add(pre.value);

            pre = pre.next;
        }

        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(5);
        head.next.next = new Node(7);
        head.next.next.next = new Node(9);
        head.next.next.next.next = new Node(11);

        ArrayList<Integer> list = reversePrint(head);
        for (Integer num : list) {
            System.out.print(num + " ");
        }

        System.out.println();

        ArrayList<Integer> list2 = reversePrint2(head);
        for (Integer num : list2) {
            System.out.print(num + " ");
        }
    }
}
