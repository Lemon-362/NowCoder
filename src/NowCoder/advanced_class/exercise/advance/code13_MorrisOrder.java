package NowCoder.advanced_class.exercise.advance;

/*
	Morris遍历：
		可以保证 时间复杂度O(N)，空间复杂度O(1)
		而一半的二叉树遍历 时间复杂度O(N)，空间复杂度O(h) h：二叉树的高度
		Morris的先序、中序、后序遍历

	一节点没有左孩子：只会到达该节点一次
	一节点有左孩子：会到达两次
		第一次：左子树最右节点==null --> 先序
		第二次：左子树最右节点==cur --> 中序

	后序：在能够到达两次且是第二次到达时，逆序打印左子树右边界。最后单独打印整棵树的右边界
 */
public class code13_MorrisOrder {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;

        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.value + " ");
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    cur = cur.right;
                }
            } else {
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }

    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    System.out.print(cur.value + " ");
                    cur = cur.right;
                }
            } else {
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }

    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    printRightEdge(cur.left);
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        printRightEdge(head);
        System.out.println();
    }

    public static void printRightEdge(Node node) {
        if (node == null) {
            return;
        }

        Node head = reverse(node);
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }

        reverse(head);
    }

    public static Node reverse(Node node) {
        Node cur = node;
        Node pre = null;
        Node next = null;

        while (cur != null) {
            next = cur.right;

            cur.right = pre;

            pre = cur;
            cur = next;
        }

        return pre;
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
        head.right.right = new Node(7);

        printTree(head);

        morrisPre(head); // 4 2 1 3 6 5 7
        morrisIn(head); // 1 2 3 4 5 6 7
        morrisPos(head); // 1 3 2 5 7 6 4
    }
}
