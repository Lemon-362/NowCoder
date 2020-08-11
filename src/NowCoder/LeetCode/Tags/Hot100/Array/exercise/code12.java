package NowCoder.LeetCode.Tags.Hot100.Array.exercise;

/*
105. 先序和中序结果构造二叉树
    剑指code04_ReconTreeByPreAndInOrder

 */
public class code12 {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node recon(int[] pre, int[] in) {
        return process(pre, in);
    }

    // 宽度优先，按层遍历
    public static Node process(int[] pre, int[] in) {
        // base case
        if (pre.length == 0 && in.length == 0){
            return null;
        }

        Node head = new Node(pre[0]);

        int mid = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]){
                mid = i;
                break;
            }
        }

        int[] lPre = generateArr(pre, 1, mid);
        int[] lIn = generateArr(in, 0, mid - 1);
        int[] rPre = generateArr(pre, mid + 1, pre.length - 1);
        int[] rIn = generateArr(in, mid + 1, in.length - 1);

        head.left = process(lPre, lIn);
        head.right = process(rPre, rIn);

        return head;
    }

    public static int[] generateArr(int[] arr, int l, int r){
        int[] res = new int[r - l + 1];
        int index = 0;
        for (int i = l; i <= r; i++) {
            res[index++] = arr[i];
        }

        return res;
    }

    // print
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
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};

        printTree(recon(pre, in));
    }

}
