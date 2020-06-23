package NowCoder.advanced_class.class_4;

/*
    AVL树：在插入时不断调整平衡性，左右高度差严格不超过1，等于2的时候就要调整
TODO 发现不平衡，从下往上调整平衡性：从插入节点的父节点开始，如果不平衡调整，并更新高度，然后往上找父节点继续调整
四种类型：针对问题节点node产生的原因结构
    1. LL型：左子树高度超了，且node.left.left != null --> 右旋一次
    2. RR型：右子树高度超了，且node.right.right != null --> 左旋一次
    3. LR型：左子树高度超了 --> 先对node的左孩子左旋一次，然后对新头节点右旋一次
    4. RL型：右子树高度超了 --> 先对node的右孩子右旋一次，然后对新头节点左旋一次
 */

import NowCoder.advanced_class.exercise.Addition.AbstractBinarySearchTree;

/**
 * Not implemented by zuochengyun
 * 
 * AVL tree implementation.
 * 
 * In computer science, an AVL tree is a self-balancing binary search tree, and
 * it was the first such data structure to be invented.[1] In an AVL tree, the
 * heights of the two child subtrees of any node differ by at most one. Lookup,
 * insertion, and deletion all take O(log n) time in both the average and worst
 * cases, where n is the number of nodes in the tree prior to the operation.
 * Insertions and deletions may require the tree to be rebalanced by one or more
 * tree rotations.
 * 
 * @author Ignas Lelys
 * @created Jun 28, 2011
 * 
 */
// AVL树
public class AVLTree extends AbstractSelfBalancingBinarySearchTree {

    /**
     * @see AbstractBinarySearchTree#insert(int)
     * 
     *      AVL tree insert method also balances tree if needed. Additional
     *      height parameter on node is used to track if one subtree is higher
     *      than other by more than one, if so AVL tree rotations is performed
     *      to regain balance of the tree.
     */
    @Override
    public Node insert(int element) {
        // 插入节点
        Node newNode = super.insert(element);
        // 调整平衡性
        rebalance((AVLNode)newNode);
        return newNode;
    }

    /**
     * @see AbstractBinarySearchTree#delete(int)
     */
    @Override
    public Node delete(int element) {
        Node deleteNode = super.search(element);
        if (deleteNode != null) {
            Node successorNode = super.delete(deleteNode);
            if (successorNode != null) {
                // if replaced from getMinimum(deleteNode.right) then come back there and update heights
                AVLNode minimum = successorNode.right != null ? (AVLNode)getMinimum(successorNode.right) : (AVLNode)successorNode;
                recomputeHeight(minimum);
                rebalance((AVLNode)minimum);
            } else {
                recomputeHeight((AVLNode)deleteNode.parent);
                rebalance((AVLNode)deleteNode.parent);
            }
            return successorNode;
        }
        return null;
    }
    
    /**
     * @see AbstractBinarySearchTree#createNode(int, AbstractBinarySearchTree.Node, AbstractBinarySearchTree.Node, AbstractBinarySearchTree.Node)
     */
    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new AVLNode(value, parent, left, right);
    }

    /**
     * Go up from inserted node, and update height and balance informations if needed.
     * If some node balance reaches 2 or -2 that means that subtree must be rebalanced.
     * 
     * @param node Inserted Node.
     */
    // TODO 发现不平衡，从下往上调整平衡性：从插入节点的父节点开始，如果不平衡调整，并更新高度，然后往上找父节点继续调整
    // 四种类型：针对问题节点node产生的原因结构
    // 1. LL型：左子树高度超了，且node.left.left != null --> 右旋一次
    // 2. RR型：右子树高度超了，且node.right.right != null --> 左旋一次
    // 3. LR型：左子树高度超了 --> 先对node的左孩子左旋一次，然后对新头节点右旋一次
    // 4. RL型：右子树高度超了 --> 先对node的右孩子右旋一次，然后对新头节点左旋一次
    private void rebalance(AVLNode node) {
        while (node != null) {
            // 插入节点的父节点
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
     * Rotates to left side.
     */
    private Node avlRotateLeft(Node node) {
        Node temp = super.rotateLeft(node);
        
        updateHeight((AVLNode)temp.left);
        updateHeight((AVLNode)temp);
        return temp;
    }

    /**
     * Rotates to right side.
     */
    private Node avlRotateRight(Node node) {
        Node temp = super.rotateRight(node);

        updateHeight((AVLNode)temp.right);
        updateHeight((AVLNode)temp);
        return temp;
    }

    /**
     * Take right child and rotate it to the right side first and then rotate
     * node to the left side.
     */
    protected Node doubleRotateRightLeft(Node node) {
        node.right = avlRotateRight(node.right);
        return avlRotateLeft(node);
    }

    /**
     * Take right child and rotate it to the right side first and then rotate
     * node to the left side.
     */
    protected Node doubleRotateLeftRight(Node node) {
        node.left = avlRotateLeft(node.left);
        return avlRotateRight(node);
    }
    
    /**
     * Recomputes height information from the node and up for all of parents. It needs to be done after delete.
     */
    private void recomputeHeight(AVLNode node) {
       while (node != null) {
          node.height = maxHeight((AVLNode)node.left, (AVLNode)node.right) + 1;
          node = (AVLNode)node.parent;
       }
    }
    
    /**
     * Returns higher height of 2 nodes. 
     */
    private int maxHeight(AVLNode node1, AVLNode node2) {
        if (node1 != null && node2 != null) {
            return node1.height > node2.height ? node1.height : node2.height;
        } else if (node1 == null) {
            return node2 != null ? node2.height : -1;
        } else if (node2 == null) {
            return node1 != null ? node1.height : -1;
        }
        return -1;
    }

    /**
     * Updates height and balance of the node.
     * 
     * @param node Node for which height and balance must be updated.
     */
    private static final void updateHeight(AVLNode node) {
        int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
        int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
        // 左右子树较大的高度 + 我自己的高度（1）
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Node of AVL tree has height and balance additional properties. If balance
     * equals 2 (or -2) that node needs to be re balanced. (Height is height of
     * the subtree starting with this node, and balance is difference between
     * left and right nodes heights).
     * 
     * @author Ignas Lelys
     * @created Jun 30, 2011
     * 
     */
    protected static class AVLNode extends Node {
        public int height; // TODO 多加了一个高度信息

        public AVLNode(int value, Node parent, Node left, Node right) {
            super(value, parent, left, right);
        }
    }

}
