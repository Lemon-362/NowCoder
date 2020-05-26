package NowCoder.Nowcoder;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 */
public class code60_PrintTreeByMultiLevel {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static List<List<Integer>> print(Node head){
        if (head == null){
            return null;
        }

        ArrayList<List<Integer>> res = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                list.add(cur.value);

                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }
            }

            res.add(list);
        }

        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.left = new Node(9);
        head.right = new Node(20);
        head.right.left = new Node(15);
        head.right.right = new Node(7);

        List<List<Integer>> lists = print(head);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
//        3
//        9 20
//        15 7
    }
}
