package NowCoder.Nowcoder.exercise;

import NowCoder.Nowcoder.code22_PrintByLevel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class code22 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static ArrayList<Integer> PrintFromTopToBottomByLevel(Node head){
        if (head == null){
            return null;
        }

        ArrayList<Integer> list = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        list.add(head.value);
        queue.offer(head);

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            Node left = cur.left;
            Node right = cur.right;

            if (left != null){
                list.add(left.value);
                queue.offer(left);
            }
            if (right != null){
                list.add(right.value);
                queue.offer(right);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(3);
        head.right = new Node(2);
        head.left.left = new Node(4);
        head.right.left = new Node(6);

        ArrayList<Integer> list = PrintFromTopToBottomByLevel(head);
        for (Integer num : list) {
            System.out.print(num + " "); // 1 3 2 4 6
        }
    }
}