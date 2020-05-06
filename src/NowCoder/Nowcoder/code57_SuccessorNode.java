package NowCoder.Nowcoder;

/*
    给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
    注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
    也就是找 后继节点：中序遍历顺序的下一个结点
 */
public class code57_SuccessorNode {
    public static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        // 后继节点
        if (pNode == null) {
            return null;
        }
        if (pNode.right != null) {
            return getLeftMost(pNode.right);
        } else {
            TreeLinkNode next = pNode.next;
            while (next != null && next.left != pNode) {
                pNode = next;
                next = pNode.next;
            }
            return next;
        }
    }

    public static TreeLinkNode getLeftMost(TreeLinkNode node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
