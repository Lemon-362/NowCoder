package NowCoder.Nowcoder.Nowcoder;

import java.util.LinkedList;
import java.util.Queue;

/*
    请实现两个函数，分别用来序列化和反序列化二叉树

二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。

二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 */
public class code61_SerialTree {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static String SerialByPre(Node head){
        if (head == null){
            return "#_";
        }

        String res = head.value + "_";
        res += SerialByPre(head.left);
        res += SerialByPre(head.right);

        return res;
    }

    public static Node ReconByPre(String str){
        String[] s = str.split("_");
        Queue<String> queue = new LinkedList<>();

        for (int i = 0; i < s.length; i++) {
            queue.offer(s[i]);
        }

        return recon(queue);
    }

    public static Node recon(Queue<String> queue){
        String value = queue.poll();

        if (value.equals("#")){
            return null;
        }

        Node head = new Node(Integer.parseInt(value));
        head.left = recon(queue);
        head.right = recon(queue);

        return head;
    }

    public static String SerialByLevel(Node head){
        if (head == null){
            return "#_";
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        String res = head.value + "_";

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            Node left = cur.left;
            Node right = cur.right;

            if (left != null){
                res += left.value + "_";
                queue.offer(left);
            }else {
                res += "#_";
            }

            if (right != null){
                res += right.value + "_";
                queue.offer(right);
            }else {
                res += "#_";
            }
        }

        return res;
    }

    public static Node ReconByLevel(String str){
        String[] s = str.split("_");
        int index = 0;
        Node head = generate(s[index++]);
        Queue<Node> queue = new LinkedList<>();

        if (head != null){
            queue.offer(head);
        }

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            cur.left = generate(s[index++]);
            cur.right = generate(s[index++]);

            if (cur.left != null){
                queue.offer(cur.left);
            }
            if (cur.right != null){
                queue.offer(cur.right);
            }
        }

        return head;
    }

    public static Node generate(String str){
        if (str.equals("#")){
            return null;
        }else {
            return new Node(Integer.parseInt(str));
        }
    }

    // for test -- print tree
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
        Node head = null;
        printTree(head);

        String pre = SerialByPre(head);
        System.out.println(pre); // #_
        Node reconHead = ReconByPre(pre);
        printTree(reconHead); // #_

        String level = SerialByLevel(head);
        System.out.println(level);
        reconHead = ReconByLevel(level);
        printTree(reconHead);

        System.out.println("===================");

        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);

        pre = SerialByPre(head);
        System.out.println(pre); // 100_21_37_#_#_#_-42_0_#_#_666_#_#_
        reconHead = ReconByPre(pre);
        printTree(reconHead);

        level = SerialByLevel(head);
        System.out.println(level); // 100_21_-42_37_#_0_666_#_#_#_#_#_#_
        reconHead = ReconByLevel(level);
        printTree(reconHead);
    }
}
