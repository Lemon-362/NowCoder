package NowCoder.LeetCode.Tags.Hot100.Tree;

/**
 * 538. 将搜索二叉树变成累加树
 *  给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
 *  使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 */
public class code538_ConvertBSTToAddTree {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * TODO BST的性质: BST的中序遍历依次升序, 可以划分为 < = >(左 中 右)的区域
     *  那么根据题意, 对于当前节点, 就是要累加中序遍历中当前节点(=)以及后边的所有节点(>)
     *  然后修改当前节点的值即可
     *  那么, 就需要反向中序遍历, 划分成 > = <(右 中 左)的区域, 每次在遍历时累加遍历到的节点
     *  当到达当前节点(=)时, 修改当前节点的值
     *
     *  方法一: 反向中序遍历的递归版本
     *   原来中序遍历的递归版本:
     *      process(head.left);
     *      head;
     *      process(head.right);
     *
     *   TODO 累加和sum必须是一个全局变量, 不能定义在递归函数中, 不然每次都会被清零
     */
    public static int sum = 0;

    public static Node convertBST1(Node head){
        if (head == null) {
            return null;
        }

        convertBST1(head.right);

        sum += head.value;
        head.value = sum;

        convertBST1(head.left);

        return head;
    }

    /**
     * 方法二: 非递归版本, Morris遍历, 需要改成反向的中序遍历
     *
     *  TODO 想要反向中序遍历, 也就是按照 右中左 的顺序遍历
     *      那么需要先遍历当前节点的右子树, 也就说要将原来的Morris遍历的mostRight变成mostLeft
     *      利用叶节点的左指针来实现
     */
    public static Node convertBST2(Node head){
        if (head == null) {
            return null;
        }

        int sum = 0;

        Node cur = head;
        Node mostLeft = null;

        while (cur != null){
            mostLeft = cur.right;
            if (mostLeft != null){
                while (mostLeft.left != null && mostLeft.left != cur){
                    mostLeft = mostLeft.left;
                }
                if (mostLeft.left == null){
                    mostLeft.left = cur;

                    cur = cur.right;
                }else {
                    mostLeft.left = null;

                    sum += cur.value;
                    cur.value = sum;

                    cur = cur.left;
                }
            }else {
                sum += cur.value;
                cur.value = sum;

                cur = cur.left;
            }
        }

        return head;
    }


    public static void printInOrder(Node head){
        if (head == null){
            return;
        }

        printInOrder(head.left);
        System.out.print(head.value + " ");
        printInOrder(head.right);

    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(7);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.right.left = new Node(6);
        head.right.right = new Node(8);

        printInOrder(head); // 2 3 4 5 6 7 8
        System.out.println();

        Node head1 = convertBST2(head);
        printInOrder(head1); // 35 33 30 26 21 15 8

    }
}
