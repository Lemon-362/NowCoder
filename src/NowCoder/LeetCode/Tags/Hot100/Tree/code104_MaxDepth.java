package NowCoder.LeetCode.Tags.Hot100.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 *
 */
public class code104_MaxDepth {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     *  TODO 方法一: 深度优先搜索DFS, 递归, 写ReturnData
     *
     *  由于ReturnData只有一个值maxDepth, 所以可以省略
     */
    public static int maxDepth1(Node head){
        if (head == null){
            return 0;
        }

        return process(head);
    }

    public static int process(Node head){
        // base case
        if (head == null){
            return 0;
        }

        int leftDepth = process(head.left);
        int rightDepth = process(head.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * TODO 方法二: 宽度优先搜索BFS, 按层遍历, 一直遍历到最深的一层
     *  但是这里必须使用size在每一次while循环中遍历当前层的节点
     */
    public static int maxDepth2(Node head){
        if (head == null){
            return 0;
        }

        int depth = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()){
            int size = queue.size();
            // 每一次while循环, 表示遍历到一层上, 记录层数
            depth++;

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                if (cur.left != null){
                    queue.offer(cur.left);
                }

                if (cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }

        return depth;
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
        head.left.left.left = new Node(1);

        printTree(head);

        System.out.println(maxDepth2(head)); // 4
    }

}
