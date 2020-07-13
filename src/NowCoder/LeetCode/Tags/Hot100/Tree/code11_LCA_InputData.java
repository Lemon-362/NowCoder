package NowCoder.LeetCode.Tags.Hot100.Tree;

import java.util.*;

public class code11_LCA_InputData {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void buildTree(HashMap<Integer, List<Integer>> map, Node head){
        // base case
        if (head == null){
            return;
        }

        // 获得每个节点对应的头+左右孩子的集合list
        List<Integer> list = map.get(head.value);
        // 创建左右孩子, 并连接到head上
        int leftValue = list.get(1);
        int rightValue = list.get(2);
        Node left = leftValue == 0 ? null : new Node(leftValue);
        Node right = rightValue == 0 ? null : new Node(rightValue);

        head.left = left;
        head.right = right;

        // 逐层递归建树
        buildTree(map, left);
        buildTree(map, right);
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

    public static Node lowestCommonAncestor(Node head, Node p, Node q) {
        // base case
        if (head == null){
            return null;
        }else if (head.value == p.value || head.value == q.value){
            return head;
        }

        Node left = lowestCommonAncestor(head.left, p, q);
        Node right = lowestCommonAncestor(head.right, p, q);

        if (left != null && right != null){
            return head;
        }else if (left == null && right == null){
            return null;
        }else {
            return left == null ? right : left;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 节点总数
        int n = sc.nextInt();
        // 根节点
        int rootValue = sc.nextInt();

        // 用Map存储每个节点的左右孩子, key是每个节点, value是每个节点+其左右孩子的集合
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 记录每一行的三个节点
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                list.add(sc.nextInt());
            }
            // 存储每个节点的左右孩子
            map.put(list.get(0), list);
        }

        // 两个给出的节点, 并创建节点p和q
        int pValue = sc.nextInt();
        int qValue = sc.nextInt();
        Node p = new Node(pValue);
        Node q = new Node(qValue);

        // 首先创建根节点
        Node head = new Node(rootValue);
        // 然后根据map建树

        buildTree(map, head);

        System.out.println(lowestCommonAncestor(head, p, q).value);
    }
}
