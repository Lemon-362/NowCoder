package NowCoder.动态规划DP.打家劫舍;

/*
打家劫舍III:
    房子形成的是 二叉树

 */
public class code03_Rob_III {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /*
        二叉树问题: 深度优先 / 广度优先
            本题应该采用深度优先
            对于每个节点都可以抢/不抢
        TODO 深度优先流程:
            先假设下级可以返回信息给我(向左, 向右递归),
            然后借助下级返回的信息, 处理当前层的递归逻辑功能
            最后向上返回当前层的信息

            对于每个节点, 我都有抢/不抢的可能性, 所以process递归应该返回这两种可能
     */
    public static int rob(Node head) {
        if (head == null) {
            return 0;
        }

        int no_rob = process(head)[0];
        int rob = process(head)[1];

        return Math.max(no_rob, rob);
    }

    public static int[] process(Node head) {
        // base case
        if (head == null) {
            return new int[]{0, 0};
        }

        int[] left = process(head.left);
        int[] right = process(head.right);
        // 1: 当前节点抢 ==> 左右孩子都不能抢
        int rob = head.value + left[1] + right[1];
        // 2: 当前节点不抢 ==> 左右孩子可以抢/不抢, 分别对左/右孩子的可能性中max
        int no_rob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{rob, no_rob};
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.left = new Node(4);
        head.right = new Node(5);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.right = new Node(1);

        System.out.println(rob(head)); // 9
    }
}
