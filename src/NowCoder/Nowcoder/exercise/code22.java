package NowCoder.Nowcoder.exercise;

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

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(head.value);

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                if (cur.left != null){
                    list.add(cur.left.value);
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    list.add(cur.right.value);
                    queue.offer(cur.right);
                }
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
