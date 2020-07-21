package NowCoder.Addition;

import NowCoder.advanced_class.code.class_4.AbstractSelfBalancingBinarySearchTree;

/**
 * 1. BST搜索二叉树
 * (1) 插入:
 *  target > cur ==> 右移
 *  target < cur ==> 左移
 * (2) 删除:
 *  1.要删除的节点只有左孩子或者只有右孩子:
 *      直接让左孩子或者右孩子顶替要删除的节点
 *  2.要删除的节点的左右孩子都在:
 *      拿要删除节点的右子树上最左的节点(后继节点)顶替要删除的节点
 *
 * 2. BST衍生出的几种类型
 * (1) AVL树:
 *  平衡性高度严格,要求任何子树的左右子树的高度差都不超过1
 * (2) 红黑树:
 *  1.头节点,叶节点必为黑
 *  2.相邻两个节点不能出现连续的红,即如果一节点为红,那么他的左右孩子必为黑
 *  3.对于任一节点,其到叶节点的每条路径上黑色的个数必相同
 *  ==> 任一条路经(到叶节点)的长度都不会超过最短路径长度的2倍
 * (3) SB树:
 *  任一节点为头的子树上节点个数 >= 它的子侄节点为头的子树上节点个数
 *
 * 3. 所有树的总结
 * (1) 目的: 为了调整平衡性,使得左右子树节点个数差不多
 * (2) 区别: 调整平衡性的频率不一样
 * (3) 相同: 增删改查的时间复杂度都是O(logN)
 *
 * 4. 调整平衡性的基本动作
 * (1) 左旋(逆时针旋): 头节点变成新头节点的左孩子
 * (2) 右旋(顺时针旋): 头节点变成新头节点的右孩子
 *
 * 5. AVL树的调整
 *  发现不平衡的原理:
 *      AVL树将左右子树的高度信息也存储在当前节点中
 *      在插入的过程中,会不断向上回溯,改变父节点的高度
 *      在回溯修改高度时,如果发现左右子树高度差>=1,则发现不平衡
 *
 *  调整动作:
 *      高度差H = right右子树高度 - left左子树高度
 *  (1) LL型:
 *      - H = -2, 问题节点cur的左子树高度超了
 *      - cur.left.left != null
 *      ==> 右旋一次
 *  (2) LR型:
 *      - H = -2, 问题节点cur的左子树高度超了
 *      - cur.left.left == null
 *      ==> 先对左孩子左旋,再对新头节点右旋
 *  (3) RR型:
 *      - H = 2, 问题节点cur的右子树高度超了
 *      - cur.right.right != null
 *      ==> 左旋一次
 *  (4) RL型:
 *      - H = 2, 问题节点cur的右子树高度超了
 *      - cur.right.right == null
 *      ==> 先对右孩子右旋,再对新头节点左旋
 */
public class AVLTree extends AbstractSelfBalancingBinarySearchTree {
    protected static class AVLNode extends Node {
        public int height; // TODO 多加了一个高度信息

        public AVLNode(int value, Node parent, Node left, Node right) {
            super(value, parent, left, right);
        }
    }

    /**
     *  调整动作:
     *      高度差H = right右子树高度 - left左子树高度
     *  (1) LL型:
     *      - H = -2, 问题节点cur的左子树高度超了
     *      - cur.left.left != null
     *      ==> 右旋一次
     *  (2) LR型:
     *      - H = -2, 问题节点cur的左子树高度超了
     *      - cur.left.left == null
     *      ==> 先对左孩子左旋,再对新头节点右旋
     *  (3) RR型:
     *      - H = 2, 问题节点cur的右子树高度超了
     *      - cur.right.right != null
     *      ==> 左旋一次
     *  (4) RL型:
     *      - H = 2, 问题节点cur的右子树高度超了
     *      - cur.right.right == null
     *      ==> 先对右孩子右旋,再对新头节点左旋
     */
     private void rebalance(AVLNode node) {
        while (node != null) {
            Node parent = node.parent;
            // 父节点左子树和右子树的高度
            int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
            int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
            // 平衡性：左右子树的高度差
            int nodeBalance = rightHeight - leftHeight;
            // rebalance (-2 means left subtree outgrow, 2 means right subtree)
            if (nodeBalance == 2) { // 右子树高度超了
                if (node.right.right != null) { // RR型，左旋一次
                    node = (AVLNode)avlRotateLeft(node);
                    break;
                } else { // RL型
                    node = (AVLNode)doubleRotateRightLeft(node);
                    break;
                }
            } else if (nodeBalance == -2) { // 左子树高度超了
                if (node.left.left != null) { // LL型，右旋一次
                    node = (AVLNode)avlRotateRight(node);
                    break;
                } else { // LR型
                    node = (AVLNode)doubleRotateLeftRight(node);
                    break;
                }
            } else { // 更新高度
                updateHeight(node);
            }

            node = (AVLNode)parent;
        }
    }

    /**
     * 左旋
     */
    private Node avlRotateLeft(Node node) {
        Node temp = super.rotateLeft(node);

        updateHeight((AVLNode)temp.left);
        updateHeight((AVLNode)temp);
        return temp;
    }

    /**
     * 右旋
     */
    private Node avlRotateRight(Node node) {
        Node temp = super.rotateRight(node);

        updateHeight((AVLNode)temp.right);
        updateHeight((AVLNode)temp);
        return temp;
    }

    /**
     * 先对右孩子右旋，再对新头节点左旋
     */
    protected Node doubleRotateRightLeft(Node node) {
        node.right = avlRotateRight(node.right);
        return avlRotateLeft(node);
    }

    /**
     * 先对左孩子左旋，在对新头节点右旋
     */
    protected Node doubleRotateLeftRight(Node node) {
        node.left = avlRotateLeft(node.left);
        return avlRotateRight(node);
    }

    /**
     * 更新高度
     */
    private static final void updateHeight(AVLNode node) {
        int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
        int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
        // 左右子树较大的高度 + 我自己的高度（1）
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }
}
