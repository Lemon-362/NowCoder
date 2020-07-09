package NowCoder.LeetCode.Tags.Hot100.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 617. 合并二叉树
 *  给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *  你需要将他们合并为一个新的二叉树。
 *  合并的规则：
 *  (1) 如果两个节点重叠，那么将他们的值相加作为节点合并后的新值
 *  (2) 否则不为 NULL的节点将直接作为新二叉树的节点
 *
 */
public class code617_MergeTrees {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     *  TODO 按层遍历, 宽度优先搜索BFS
     *      逐层累加对应的节点
     *      方法一: 递归版本
     *  process递归函数: 表示合并当前层的两个节点
     *  (1) base case:
     *      head1==null, 直接返回head2
     *      head2==null, 直接返回head1
     *      head1==null&&head2==null，直接返回null（叶节点末尾）
     *  (2) 合并当前层节点, 变成新的头节点head
     *      head的左孩子和右孩子, 通过process向左和向右递归返回给我, 都交给递归处理
     *      处理结束, 直接返回新的头节点head
     *
     *  时间复杂度: O(N)
     *  空间复杂度: O(h), 最差情况递归到最深处, 高度是h
     */
    public static Node mergeTrees1(Node head1, Node head2){
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        return process(head1, head2);
    }

    // 只考虑当前层数的节点的合并情况, 剩下的左右孩子交给递归处理
    public static Node process(Node head1, Node head2){
        // base case
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        Node head = new Node(head1.value + head2.value);

        head.left = process(head1.left, head2.left);
        head.right = process(head1.right, head2.right);

        return head;
    }

    /**
     *  TODO 方法二: 迭代版本
     *  使用Queue队列实现按层遍历
     *  可以只使用一个队列, 先存head1, 再存head2
     *  这里不创建新的节点, 而是直接把树2加到树1上,
     *
     *  TODO 实际上是按层遍历树2的节点, 树1随着树2变化
     *      所以如果树1的左右孩子为空, 直接将树2的左右孩子加到树1上, 而不需要将树2的节点放入Queue中
     *      如果树1和树2的左右孩子都不为空, 那么就需要都放入Queue中, 在下一轮去计算左右孩子合并后的值
     *
     */
    public static Node mergeTrees2(Node head1, Node head2){
        if (head1 == null && head2 == null){
            return null;
        }else if (head1 == null){
            return head2;
        }else if (head2 == null){
            return head1;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head1);
        queue.offer(head2);

        while (!queue.isEmpty()){
            // 每次取出成对的两个节点
            Node p1 = queue.poll();
            Node p2 = queue.poll();

            // 计算合并后树1的节点的值
            p1.value += p2.value;

            // 按层遍历树2的节点
            if (p2.left != null){
                if (p1.left == null){
                    p1.left = p2.left;
                }else {
                    queue.offer(p1.left);
                    queue.offer(p2.left);
                }
            }

            if (p2.right != null){
                if (p1.right == null){
                    p1.right = p2.right;
                }else {
                    queue.offer(p1.right);
                    queue.offer(p2.right);
                }
            }
        }

        return head1;
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
        Node head1 = new Node(1);
        head1.left = new Node(3);
        head1.right = new Node(2);
        head1.left.left = new Node(5);

        Node head2 = new Node(2);
        head2.left = new Node(1);
        head2.right = new Node(3);
        head2.left.right = new Node(4);
        head2.right.right = new Node(7);

        printTree(head1);
        printTree(head2);

        Node head = mergeTrees1(head1, head2);
        printTree(head);

    }
}
