package NowCoder.LeetCode.Tags.Hot100.Tree;

/**
 * 337. 打家劫舍
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 */
public class code12_Rob {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 递归: TODO 深度优先 和进阶code23的MaxHappy类似
     *  process递归函数: 求以head为头的子树的最高金额
     *      首先假设下级能够返回给我process信息, 获得了当前左右子树的最高金额
     *      那么要求以head为头的子树的最高金额的话, 有两种情况
     *      (1) 要head, 那么head的左右孩子不能要
     *      (2) 不要head, 那么head的左右孩子可以要也可以不要
     */
    public static int rob(Node head) {
        if (head == null) {
            return 0;
        }

        int rob = process(head).rob;
        int no_rob = process(head).no_rob;

        return Math.max(rob, no_rob);
    }

    public static class ReturnData {
        private int rob;
        private int no_rob;

        public ReturnData(int rob, int no_rob) {
            this.rob = rob;
            this.no_rob = no_rob;
        }
    }

    public static ReturnData process(Node head) {
        // base case
        if (head == null) {
            return new ReturnData(0, 0);
        }

        // 假设下级能够返回最高金额给我
        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);

        // 可能性1: 我要head的金额, 但是head的左右孩子不能要
        int rob = head.value + leftData.no_rob + rightData.no_rob;
        // 可能性2: 我不要head的金额, 但是head的左右孩子可以要, 也可以不要
        int no_rob = Math.max(leftData.rob, leftData.no_rob)
                + Math.max(rightData.rob, rightData.no_rob);

        return new ReturnData(rob, no_rob);
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
