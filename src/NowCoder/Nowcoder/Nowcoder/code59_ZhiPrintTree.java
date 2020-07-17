package NowCoder.Nowcoder.Nowcoder;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    之字形打印二叉树：
        第一行按照从左到右的顺序打印，
        第二层按照从右至左的顺序打印，
        第三行按照从左到右的顺序打印，
        其他行以此类推。
 */
public class code59_ZhiPrintTree {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static ArrayList<List<Integer>> ZhiPrint(Node head){
        if (head == null){
            return null;
        }

        ArrayList<List<Integer>> res = new ArrayList<>();
        boolean flag = true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()){
            // 按层遍历
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();

            // 在一层上按顺序放入list
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

            res.add(list);
            flag = !flag;
        }

        return res;
    }

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
