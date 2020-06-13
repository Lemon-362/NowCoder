package NowCoder.advanced_class.exercise.exercise01;

public class code13 {
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

    public static Node reverse(Node head) {
        Node cur = head;
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

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);

//        printTree(head);

        morrisPre(head); // 4 2 1 3 6 5 7
        morrisIn(head); // 1 2 3 4 5 6 7
        morrisPos(head); // 1 3 2 5 7 6 4
    }
}
