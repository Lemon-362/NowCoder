package NowCoder.basic_class.exercise;

/*
	二叉树的序列化和反序列化：
		1. 序列化：把内存中的数据用文件保存
		2. 反序列化：将数据从文件中读取，还原其结构

	序列化：先序遍历，将 打印语句 替换为 每个节点的值都放到str中，并加上'_'，遇到null，用#表示，并加上'_'
	反序列化：用'_'分割，先序遍历的方式反序列化，第一个为头节点，先左再右

	按层序列化：用queue存放节点，先头节点，然后先左再右
	按层反序列化：用queue存放节点，先根据值生成头节点，然后先左再右
 */

import java.util.LinkedList;
import java.util.Queue;

public class code27_SerialAndRecon {
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // 先序遍历序列化
    public static String SerialByPre(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.data + "_";
        res += SerialByPre(head.left);
        res += SerialByPre(head.right);
        return res;
    }

    // 先序遍历反序列化
    public static Node ReconByPre(String str) {
        String[] s = str.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < s.length; i++) {
            queue.offer(s[i]);
        }
        return recon(queue);
    }

    public static Node recon(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.left = recon(queue);
        head.right = recon(queue);
        return head;
    }

    public static String SerialByLevel(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.data + "_";
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                res += head.left.data + "_";
                queue.offer(head.left);
            } else {
                res += "#_";
            }
            if (head.right != null) {
                res += head.right.data + "_";
                queue.offer(head.right);
            } else {
                res += "#_";
            }
        }
        return res;
    }

    public static Node ReconByLevel(String str){
        String[] s = str.split("_");
        int index = 0;
        Queue<Node> queue = new LinkedList<>();
        Node head = generateNode(s[index++]);
        if (head != null){
            queue.offer(head);
        }
        Node node = null;
        while (!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNode(s[index++]);
            node.right = generateNode(s[index++]);
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return head;
    }

    public static Node generateNode(String str){
        if (str.equals("#")){
            return null;
        }
        return new Node(Integer.parseInt(str));
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
        String val = to + head.data + to;
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
