package NowCoder.advanced_class.code.class_5;

/*
    树的递归2：树的最大值和最小值
        给定一个二叉树的头节点head，返回树的最大值和最小值
 */
public class Code_02_MaxAndMinInTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class ReturnData {
        public int max;
        public int min;

        public ReturnData(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static ReturnData process(Node head) {
        // base case
        if (head == null) { // 空节点时，min返回系统最大，max返回系统最小
            return new ReturnData(
                    Integer.MAX_VALUE,
                    Integer.MIN_VALUE
            );
        }
        // 左右子树的max和min
        ReturnData left = process(head.left);
        ReturnData right = process(head.right);
        // 向上返回三者中的max和min
        return new ReturnData(
                Math.min(Math.min(left.min, right.min), head.value),
                Math.max(Math.max(left.max, right.max), head.value)
        );
    }

    public static void printMaxAndMin(Node head) {
        ReturnData data = process(head);
        System.out.println("max : " + data.max);
        System.out.println("min : " + data.min);
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

        printMaxAndMin(head); // 20 0
    }
}
