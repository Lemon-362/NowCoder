package NowCoder.Nowcoder.exercise;

import NowCoder.Nowcoder.code59_ZhiPrintTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class code59 {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static List<List<Integer>> ZhiPrint(Node head){
        ArrayList<List<Integer>> res = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        if (head != null){
            queue.offer(head);
        }

        boolean flag = true;

        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                if (flag){
                    list.addLast(cur.value);
                }else {
                    list.addFirst(cur.value);
                }

                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }

            }
            flag = !flag;
            res.add(list);
        }

        return res;
    }

//    public static void printByLevel(Node head){
//        Queue<Node> queue = new LinkedList<>();
//        queue.offer(head);
//
//        while (!queue.isEmpty()){
//            int size = queue.size();
//
//            for (int i = 0; i < size; i++) {
//                Node cur = queue.poll();
//                System.out.print(cur.value + " ");
//
//                if (cur.left != null){
//                    queue.offer(cur.left);
//                }
//                if (cur.right != null){
//                    queue.offer(cur.right);
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.left = new Node(9);
        head.right = new Node(20);
        head.right.left = new Node(15);
        head.right.right = new Node(7);

        List<List<Integer>> lists = ZhiPrint(head);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
//        3
//        20 9
//        15 7
    }
}
