package NowCoder;

import java.util.*;

public class test {
    private static TreeNode p = null;
    private static TreeNode q = null;

    /**
     * 递归的求解
     * 1：p,q都在根节点左子树上，最近公共祖先在左子树上的某个节点
     * 2：p,q都在根节点右子树，最近公共祖先为右子树上的某个节点
     * 3：p,q分别在左右子树上，最近公共祖先为根节点
     */
    private static TreeNode lca(TreeNode root) {
        if(root == null || q == null || p == null) {
            return null;
        }
        if(root == p || root == q) return root;
        TreeNode left = lca(root.left);
        TreeNode right = lca(root.right);
        if(left != null && right != null) return root;
        return left == null ? right : left;
    }

    private static void buildTree(Map<Integer,List<Integer>> treeMap, TreeNode root, int pVal, int qVal) {
        if(root == null) return;
        List<Integer> nodeRecord = treeMap.get(root.val);
        int leftVal = nodeRecord.get(1);
        int rightVal = nodeRecord.get(2);
        TreeNode left = leftVal == 0 ? null : new TreeNode(root, null, null, leftVal);
        TreeNode right = rightVal == 0 ? null : new TreeNode(root, null, null, rightVal);
        root.left = left;
        root.right = right;
        buildTree(treeMap, left, pVal, qVal);
        buildTree(treeMap, right, pVal, qVal);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        public TreeNode(TreeNode parent, TreeNode left, TreeNode right, int val){
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.val = val;
        }
    }

        public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.val + to;
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
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int rootVal = scanner.nextInt();
        Map<Integer, List<Integer>> treeMap = new HashMap();
        for(int i = 0; i < n; i++) {
            List<Integer> nodeList = new ArrayList();
            for(int j = 0; j < 3; j++) {
                nodeList.add(scanner.nextInt());
            }
            treeMap.put(nodeList.get(0), nodeList);
        }
        int pVal = scanner.nextInt();
        int qVal = scanner.nextInt();
        TreeNode root = new TreeNode(null, null, null, rootVal);
        buildTree(treeMap, root, pVal, qVal);

        printTree(root);
    }
}