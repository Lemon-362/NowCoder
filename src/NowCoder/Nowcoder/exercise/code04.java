package NowCoder.Nowcoder.exercise;

import NowCoder.Nowcoder.code04_ReconTreeByPreAndInOrder;

import java.util.Arrays;

public class code04 {
    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node process(int[] preArr, int[] inArr) {
        // base case
        if (preArr.length == 0 || inArr.length == 0){
            return null;
        }

        Node head = new Node(preArr[0]);
        int mid = 0;
        for (int i = 0; i < inArr.length; i++) {
            if (inArr[i] == preArr[0]){
                mid = i;
                break;
            }
        }

        int[] lPre = Arrays.copyOfRange(preArr, 1, mid + 1);
        int[] lIn = Arrays.copyOfRange(inArr, 0, mid);
        int[] rPre = Arrays.copyOfRange(preArr, mid + 1, preArr.length);
        int[] rIn = Arrays.copyOfRange(inArr, mid + 1, inArr.length);

        head.left = process(lPre, lIn);
        head.right = process(rPre, rIn);

        return head;
    }

    public static Node recon(int[] pre, int[] in){
        return process(pre, in);
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
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        printTree(recon(pre, in));
    }
}
