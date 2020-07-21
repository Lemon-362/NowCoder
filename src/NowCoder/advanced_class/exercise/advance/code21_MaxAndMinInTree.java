package NowCoder.advanced_class.exercise.advance;

/*
    树的递归2：树的最大值和最小值
        给定一个二叉树的头节点head，返回树的最大值和最小值
 */


public class code21_MaxAndMinInTree {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
        题目要求整个树上的max和min --> 求以每个节点为头的子树上的max和min
        1. 分析可能性:
            1) max和min都在左子树上
            2) max和min都在右子树上
            3) max和min一个在左一个在右 --> 左子树上的max,min要和右子树上的max,min比较
        2. 列出所需信息:
            1) 左子树上的max
            2) 左子树上的min
            3) 右子树上的max
            4) 右子树上的min
        3. 整合成结构相同的信息,包装成黑盒:
            1) 子树上的max
            2) 子树上的min
        4. 改递归
     */
    public static class ReturnData {
        private int max;
        private int min;

        public ReturnData(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    public static ReturnData process(Node head){
        // base case
        if (head == null){
            return new ReturnData(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        int max = Math.max(Math.max(leftData.max, rightData.max), head.value);
        int min = Math.min(Math.min(leftData.min, rightData.min), head.value);

        return new ReturnData(max, min);
    }

    public static void printMaxAndMin(Node head){
        ReturnData returnData = process(head);
        int max = returnData.max;
        int min = returnData.min;

        System.out.println("max: " + max);
        System.out.println("min: " + min);
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
