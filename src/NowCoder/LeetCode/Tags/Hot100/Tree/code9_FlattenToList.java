package NowCoder.LeetCode.Tags.Hot100.Tree;

/**
 * 二叉树展开为链表:
 *  给定一个二叉树, 原地将它展开为一个单链表
 *  要求空间复杂度: O(1)
 *        1
 *     2     5    ==>  1-2-3-4-5-6
 *   3  4      6
 *
 *  TODO 左子树一定要置空
 */
public class code9_FlattenToList {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /** TODO 深度优先搜索DFS
     * 方法一: 递归 TODO 先左到右再右到左
     *  process递归函数:
     *  1. 将左子树变成链表
     *  2. 将右子树变成链表
     *  3. 左子树挂在当前节点的右边
     *  4. 将右子树挂在当前节点(包括右边已经挂好的左子树)的右边
     *
     * 空间复杂度: O(N)
     */
    public static void flattenToList1(Node head){
        if (head == null){
            return;
        }

        process(head);
    }

    public static void process(Node head){
        // base case
        if (head == null){
            return;
        }

        // 将左子树变成链表
        process(head.left);

        // 将右子树变成链表
        process(head.right);

        // 保存右子树到临时变量
        Node temp = head.right;

        // 左子树挂在当前节点的右边
        head.right = head.left;

        // TODO 将左子树置空
        head.left = null;

        // 将右子树挂在当前节点(包括右边已经挂好的左子树)的右边
        // 首先要找到当前节点(包括右边已经挂好的左子树)的最右节点
        while (head.right != null){
            head = head.right;
        }
        // 将原来的右子树挂到最右边
        head.right = temp;
    }

    /**
     * 方法二: 非递归版 TODO 先右到左再左到右
     *  TODO 向右遍历
     */
    public static void flattenToList2(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;

        while (cur != null){
            // 如果左子树不为空, 则先把右子树挂到左子树上, 然后将左子树挂到当前节点的右边
            if (cur.left != null) {
                Node left = cur.left;

                while (left.right != null){
                    left = left.right;
                }
                left.right = cur.right;

                cur.right = cur.left;
                // TODO 左子树一定要置空
                cur.left = null;
            }

            cur = cur.right;
        }
    }

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
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(5);
        head.left.left = new Node(3);
        head.left.right = new Node(4);
        head.right.right = new Node(6);

        printTree(head);

        flattenToList1(head);

        printTree(head);
    }
}
