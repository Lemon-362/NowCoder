package NowCoder.Nowcoder.exercise;

import NowCoder.Nowcoder.code17_HasSubTree;

public class code17 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // TODO 子结构: 必须先找到相同节点, 然后再往下比较, 如果直接递归process, 头节点不同直接返回false了
    public static boolean isSubStructure(Node head1, Node head2) {
        if (head1 == null && head2 == null){
            return false;
        }

        if (head1.value == head2.value){
            return process(head1, head2);
        }else {
            return isSubStructure(head1.left, head2) || isSubStructure(head1.right, head2);
        }
    }

    public static boolean process(Node head1, Node head2){
        // base case
        if (head2 == null){
            return true;
        }

        if (head1 == null){
            return false;
        }

        if (head1.value == head2.value){
            return process(head1.left, head2.left) && process(head1.right, head2.right);
        }

        return false;
    }

    // TODO KMP算法可以判断 子树!!! 但不能判断子结构
    public static boolean isSubTreeByKMP(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return false;
        }

        char[] str1 = serial(head1).toCharArray();
        char[] str2 = serial(head2).toCharArray();

        int[] next = getNextArr(str2);

        int p1 = 0;
        int p2 = 0;

        while (p1 < str1.length && p2 < str2.length) {
            if (str1[p1] == str2[p2]) {
                p1++;
                p2++;
            } else {
                if (next[p2] == -1) {
                    p1++;
                } else {
                    p2 = next[p2];
                }
            }
        }

        int len = p2 == str2.length ? p1 - p2 : -1;

        return len != -1;
    }

    public static int[] getNextArr(char[] str) {
        int[] next = new int[str.length];

        next[0] = -1;
        next[1] = 0;
        int p = 2;
        int cn = 0;

        while (p < next.length) {
            if (str[p - 1] == str[cn]) {
                next[p++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[p++] = 0;
            }
        }

        return next;
    }

    public static String serial(Node head) {
        if (head == null) {
            return "#_";
        }

        String res = head.value + "_";
        res += serial(head.left);
        res += serial(head.right);

        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.left.left.left = new Node(6);

        Node head1 = new Node(2);
        head1.right = new Node(5);

        System.out.println(isSubStructure(head, head1)); // true
    }
}
