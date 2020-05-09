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
    public static ArrayList<Integer> print(Node head){
        if (head == null){
            return null;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;

        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        ArrayList<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()){
            res.add(stack.pop().value);
        }

        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(5);
        head.next.next = new Node(7);
        head.next.next.next = new Node(9);

        ArrayList<Integer> list = print(head);

        for (Integer num : list) {
            System.out.print(num + " ");
        }
    }
}
