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

    public static ArrayList<Integer> list = new ArrayList<>();

    public static ArrayList<Integer> reversePrint2(Node head) {
        // base case
        if (head == null){
            return null;
        }

        reversePrint2(head.next);

        list.add(head.value);

        return list;
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

        ArrayList<Integer> list3 = reversePrint2(head);
        for (Integer num : list3) {
            System.out.print(num + " ");
        }
    }
}
