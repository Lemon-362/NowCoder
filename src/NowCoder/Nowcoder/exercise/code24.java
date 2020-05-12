package NowCoder.Nowcoder.exercise;

import NowCoder.Nowcoder.code24_FindPath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class code24 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static LinkedList<List<Integer>> res = new LinkedList<>();
    public static LinkedList<Integer> list = new LinkedList<>();

    public static List<List<Integer>> findPath(Node head, int num){
        process(head, num);
        return res;
    }

    public static void process(Node head, int num){
        // base case
        if (head == null){
            return;
        }

        list.addLast(head.value);

        num -= head.value;

        if (num == 0 && head.left == null && head.right == null){
            res.add(new LinkedList<>(list));
        }

        process(head.left, num);
        process(head.right, num);

        list.removeLast();
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(4);
        head.right = new Node(8);
        head.left.left = new Node(11);
        head.right.left = new Node(13);
        head.right.right = new Node(4);
        head.left.left.left = new Node(7);
        head.left.left.right = new Node(2);
        head.right.right.left = new Node(5);
        head.right.right.right = new Node(1);

        List<List<Integer>> lists = findPath(head, 22);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
//      5 4 11 2
//      5 8 4 5
    }
}