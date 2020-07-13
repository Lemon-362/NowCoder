package NowCoder.LeetCode.Tags.Hot100.Tree;

/**
 * 437. 路径总和
 *  给定一个二叉树，它的每个结点都存放着一个整数值。
 *  找出路径和等于给定数值的路径总数。
 *  路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 */
public class code4_PathSum {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     *  TODO 递归
     *  和剑指24的findPath类似, 但是由于不需要从根节点开始, 所以需要以每个节点为头
     *  那么, 就需要额外使用一个递归, 遍历每个节点为头的子树
     *
     */
    public static int pathSum(Node head, int sum){
        if (head == null) {
            return 0;
        }

        // 以每个节点为头的子树
        int res = process(head, sum);
        res += pathSum(head.left, sum);
        res += pathSum(head.right, sum);

        return res;
    }

    // 在以head为头的子树上找路径, TODO 注意: 路径并不需要以head为头
    public static int process(Node head, int sum) {
        // base case
        if (head == null) {
            return 0;
        }

        sum -= head.value;

        /* TODO
            sum不需要回溯, 重置为0, 因为节点有正有负,
            如果到当前节点刚好为0, 那么就是一条路径,
            此时仍然可以继续往下走, 有可能还会找到一条路径, 包含前面的解
         */
        int res = sum == 0 ? 1 : 0;

        return res + process(head.left, sum) + process(head.right, sum);
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.left = new Node(5);
        head.right = new Node(-3);
        head.left.left = new Node(3);
        head.left.right = new Node(2);
        head.right.right = new Node(11);
        head.left.left.left = new Node(3);
        head.left.left.right = new Node(-2);
        head.left.right.right = new Node(1);

        System.out.println(pathSum(head, 8)); // 3
    }
}
