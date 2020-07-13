package NowCoder.LeetCode.Tags.Hot100.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树(镜像二叉树)
 *
 */
public class code3_ReverseTree {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * TODO 按层遍历, 宽度优先搜索BFS
     *      逐层交换左右节点
     * 方法一: 迭代版本, 队列
     *      逐层遍历, 交换当前层数的左右孩子, 然后将左右孩子再次放入Queue中
     */
    public static Node reverseTree1(Node head){
        if (head == null){
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null){
                queue.offer(cur.right);
            }

            Node temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
        }

        return head;
    }

    /**
     * 方法二: 递归版本
     *  先交换头节点的左右孩子, 剩下的交给递归处理
     *  base case:
     *      head==null, 直接返回null
     */
    public static Node reverseTree2(Node head){
        if (head == null){
            return null;
        }

        Node temp = head.left;
        head.left = head.right;
        head.right = temp;

        reverseTree2(head.left);
        reverseTree2(head.right);

        return head;
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(8);
        head.left = new Node(6);
        head.right = new Node(10);
        head.left.left = new Node(5);
        head.left.right = new Node(7);
        head.right.left = new Node(9);

        printTree(head);
        printTree(reverseTree2(head));
    }

}
