package NowCoder.advanced_class.code.class_6;

import java.util.LinkedList;
import java.util.Queue;

/*
    判断搜索二叉树和平衡二叉树：
        BST:Morris中序遍历依次升序
        CBT:按层遍历,依次填满
            1) 有右无左,false
            2) 不违反1)的情况下,当前节点左右孩子不全时,左右孩子下的所有节点必须全为叶节点,不能含有子树
 */
public class Code_04_IsBSTAndCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 搜索二叉树判断:Morris中序遍历时判断
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && pre.value >= cur1.value) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }

    // 完全二叉树判断
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        // leaf表示的是当前节点的判断阶段
        boolean leaf = false; // 当前节点左右孩子不全，则为true
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            Node l = head.left;
            Node r = head.right;
            if (    // 此时leaf阶段开启,说明head(可能是左/右孩子)的父节点不全,此时的l和r是父节点的左/右孩子的左右孩子
                    (leaf && (l != null || r != null)) // head的左右孩子有一个存在，说明head不是叶节点
                    ||
                    (l == null && r != null))  // 有右孩子，没有左孩子
            {
                return false;
            }
            // 宽度优先遍历,按层遍历
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            }
            //  TODO 当遇到 “第一个” 节点的左右孩子不全时，leaf阶段变为true，开启阶段
            // 左右孩子有一个为空，说明当前节点左右孩子不全，leaf阶段开启
            if (l == null || r == null) { // 第一次开启leaf阶段,然后进入下一个循环
                // 在下一个循环内l和r就是当前左/右孩子的左右孩子,只需要判断l和r是否为空
                leaf = true;
            }
        }
        return true;
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
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));

    }
}