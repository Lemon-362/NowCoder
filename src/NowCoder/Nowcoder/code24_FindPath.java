package NowCoder.Nowcoder;

import NowCoder.Nowcoder.exercise.code24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    二叉树中和为某一值的路径：
        输入一颗二叉树的根节点和一个整数，
        打印出二叉树中结点值的和为输入整数的所有路径。
        路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
        (注意: 在返回值的list中，数组长度大的数组靠前)

        TODO 要求从根节点往下开始到叶节点结束, 所以想到 先序遍历(头左右) 的顺序中寻找
 */
public class code24_FindPath {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

//    public static LinkedList<List<Integer>> res = new LinkedList<>();
//    public static LinkedList<Integer> list = new LinkedList<>();
    public static List<List<Integer>> res = new ArrayList<>();
    public static List<Integer> list = new ArrayList<>();

    // TODO 因为只有到底才能判断是否是一条路径, 所以在之前只能每次先加到list中, 然后再判断
    public static List<List<Integer>> findPath(Node head, int num){
        process(head, num);
        return res;
    }

    public static void process(Node head, int num){
        // base case
        if (head == null){
            return;
        }

        // 首先将节点加入到list中
//        list.addLast(head.value);
        list.add(head.value);

        num -= head.value;

        // 判断: num==0, 且该节点是叶节点(到底了)
        if (num == 0 && head.left == null && head.right == null){
//            res.add(new LinkedList<>(list));
            res.add(new ArrayList<>(list));
        }

        // 如果不满足, 则往左/右递归
        process(head.left, num);
        process(head.right, num);

        // TODO 此时, 一条到底的路走完了, 要向上回溯到父节点时, 必须先将当前节点删除
//        list.removeLast();
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(4);
        head.right = new Node(8);
        head.left.left = new Node(11);
        head.right.left = new Node(13);
        head.right.right = new Node(4);
        head.left.left.left = new Node(7);
        head.left.left.right = new Node(2);
        head.right.right.left = new Node(5);
        head.right.right.right = new Node(1);

        List<List<Integer>> lists = findPath(head, 22);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
//      5 4 11 2
//      5 8 4 5
    }

}
