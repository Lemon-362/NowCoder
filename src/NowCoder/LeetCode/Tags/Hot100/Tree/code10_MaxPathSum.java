package NowCoder.LeetCode.Tags.Hot100.Tree;

/**
 * 124. 二叉树中的最大路径和
 *  给定一个非空二叉树，返回其最大路径和。
 *  本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。
 *  该路径至少包含一个节点，且不一定经过根节点。
 *
 */
public class code10_MaxPathSum {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxPathSum(Node head){
        if (head == null) {
            return 0;
        }

        process(head);

        return res;
    }

    public static int res = Integer.MIN_VALUE;

    /**
     * TODO process递归函数：在以head为头的子树上，找从当前节点head开始的最大路径和
     *
     *  左右子树给我当前的返回值要和0判断，如果小于0，那么就不应该选择
     *
     *  以head节点为头的子树的最大路径和：head节点+左右子树的最大路径和
     *
     *  向上返回时，找当前head节点的最大路径的一条分支，但是不能是左中右的情况，这样是不能构成子树的
     *
     *  这里的全局变量其实和ReturnData类似，ReturnData也可以看成是一个全局变量
     *
     *  TODO 这里和advance_code20_BiggestSubBSTInTree不同
     *      因为这里要找的是一条路径，而不是子树
     *      如果用code20的方法的话，求出的是一个子树的最大和，而不是一条路径
     *
      */
    public static int process(Node head) {
        // base case
        if (head == null){
            return 0;
        }

        // 向左递归得到的左子树上的最大路径和（一定是包含左孩子的）
        int leftMaxPathSum = Math.max(process(head.left), 0);
        // 向右递归得到的右子树上的最大路径和（一定是包含右孩子的）
        int rightMaxPathSum = Math.max(process(head.right), 0);

        // 以head节点为头的子树的最大路径和：head节点+左右子树的最大路径和
        // TODO 为什么不需要和 中+左/中+右 比较：因为在上面左右的值已经和0比较了，最小就是0，那么中+左/中+右一定是小于中+左+右的
        // 必须要head头节点, 如果不要的话, 就相当于是左右子树的递归了, 而左右子树的递归的最大值在上面已经找到了
        int curMaxPathSum = head.value + leftMaxPathSum + rightMaxPathSum;

        // 更新全局最大值
        res = Math.max(res, curMaxPathSum);

        // 向上返回的是从head开始的最大路径和
        return head.value + Math.max(leftMaxPathSum, rightMaxPathSum);
    }

    public static void main(String[] args){
        Node head = new Node(-10);
        head.left = new Node(9);
        head.right = new Node(20);
        head.right.left = new Node(15);
        head.right.right = new Node(7);

        System.out.println(maxPathSum(head));
    }
}
