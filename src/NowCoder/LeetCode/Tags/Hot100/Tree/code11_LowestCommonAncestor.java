package NowCoder.LeetCode.Tags.Hot100.Tree;

/**
 * 236. 二叉树的最近公共祖先
 *  给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *  最近公共祖先的定义为：
 *      对于有根树 T 的两个结点 p、q，
 *      最近公共祖先表示为一个结点 x，
 *      满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
 *
 */
public class code11_LowestCommonAncestor {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     *  如果root是o1和o2的最近公共祖先，则有以下几种情况：
     *  1. o1和o2在root的子树上，且分列root的两侧
     *  2. o1==root，o2在root的左/右子树上
     *  3. o2==root，o1在root的左/右子树上
     *
     *  o1和o2不可能在root的同一侧子树上，否则最近公共祖先不可能是root，而是root那一侧向下的孩子
     *
     *  递归：假设以cur为头的子树上求解, 返回o1和o2的公共祖先
     *  TODO 递归函数返回的是公共祖先/找到的指定节点o1/找到的指定节点o2
     *  1. base case:
     *  (1) cur==null, 返回null
     *  (2) cur==o1 || cur==o2, 公共祖先就是cur本身
     *  2. 在左子树上搜索返回的公共祖先为left，在右子树上搜索返回的公共祖先为right
     *  3. 可能性分析：
     *  (1) left和right都为空, 说明以cur为头的子树上没有o1和o2, 返回null
     *  (2) left和right都不为空, 说明o1和o2在以cur为头的子树上, 且一定分布在两侧, 不可能在同一侧
     *      则它们的最近公共祖先是cur, 返回cur
     *  (3) left和right有一个为空, 直接返回不为空的那个
     *  TODO 写递归函数时, 不要考虑具体是怎么实现的, 只要考虑当前函数下如何判断, 向上返回
     */
    public static Node lowestCommonAncestor(Node head, Node p, Node q) {
        // base case
        if (head == null){
            return null;
        }else if (head == p || head == q){
            return head;
        }

        // 假设左右孩子都返回了节点给我，可能是公共节点，也可能是找到的指定节点p或q
        Node left = lowestCommonAncestor(head.left, p, q);
        Node right = lowestCommonAncestor(head.right, p, q);

        if (left != null && right != null){ // 如果左右都不为空，说明pq分别在两侧，那么公共祖先就是head头节点
            return head;
        }else if (left == null && right == null){ // 如果左右都为null，说明pq不在这个子树上
            return null;
        }else { // 如果左右有一个为空，那么直接返回不为空的那个，不为空的可能是找到的p或q，也可能是找到的公共祖先
            return left == null ? right : left;
        }
    }

    public static void main(String[] args){
        Node head = new Node(3);
        Node head1 = new Node(5);
        Node head2 = new Node(1);
        Node head3 = new Node(6);
        Node head4 = new Node(2);
        Node head5 = new Node(0);
        Node head6 = new Node(8);
        Node head7 = new Node(7);
        Node head8 = new Node(4);

        head.left = head1;
        head.right = head2;
        head1.left = head3;
        head1.right = head4;
        head2.left = head5;
        head2.right = head6;
        head4.left = head7;
        head4.right = head8;

        System.out.println(lowestCommonAncestor(head, head1, head2).value); // 3
        System.out.println(lowestCommonAncestor(head, head7, head8).value); // 2

    }
}
