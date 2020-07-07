package NowCoder.advanced_class.exercise;

import java.util.TreeMap;

/*
    前缀树的应用:子数组的最大异或和
        给定一个数组，求子数组的异或和最大为多少？
 */
public class code28_MaxEOR {
    // 1. 暴力法: 以每个位置结尾，在其中找子数组最大异或和 O(N^3)
    public static int getMaxEOR1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int xor = 0;

        // 以每个位置结尾
        for (int i = 0; i < arr.length; i++) {
            // 开始位置从0-i，以start开始，以i位置结尾的所有子数组
            for (int start = 0; start <= i; start++) {
                int num = 0;
                // 求以start开始，以i位置结尾的当前子数组的xor
                for (int k = start; k <= i; k++) {
                    num ^= arr[k];
                }
                xor = Math.max(xor, num);
            }
        }

        return xor;
    }

    // 2. 暴力法上的优化: 记录以每个位置结尾的结果 O(N^2)
    // 利用异或的性质: xor1 ^ xor2 = xor --> xor2 = xor ^ xor1
    public static int getMaxEOR2(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int xor = 0;
        int res = 0;
        int[] dp = new int[arr.length];

        // 以每个位置结尾
        for (int i = 0; i < arr.length; i++) {
            // 以每个位置结尾的xor
            xor ^= arr[i];
            // 当start=0时的子数组
            res = Math.max(xor, res);

            // 以i位置结尾的数组可以分为 0 - start - i 两部分
            // 那么start-i的子数组的xor=dp[i] ^ dp[start-1]
            for (int start = 1; start <= i; start++) {
                int startToI = xor ^ dp[start - 1];
                res = Math.max(startToI, res);
            }

            dp[i] = xor;
        }

        return res;
    }

    // 3. 前缀树: 将每个xor转换为二进制存储 O(N)
    public static class Node {
        private Node[] next;

        public Node() {
            this.next = new Node[2];
        }
    }

    /**
     * 根据2的改进：
     *  已知以i位置结尾的数组的xor
     *  并且以start开头以i结尾是要求的当前数组上的最大xor子数组
     *  那么以i位置结尾的数组可以划分为两部分 0 -- start -- i
     *  那么根据异或的性质：start--i的xor = dp[i] ^ dp[start-1]
     *
     *  现在已知以i位置结尾的数组的xor，希望求出start--i的xor最大
     *  也就要求dp[i] ^ dp[start-1]能够最大 ==> 一个数能够和dp[i]异或出最大
     *
     *  1. dp[i]=0010
     *      对于符号位：希望异或出0，才能为正，否则1的话会更小，如果没有0的路，那么只能走1的路
     *      对于其他位：希望从高到低尽量异或出的结果为1，那么最好情况是能走与dp[i]相反的路，如果没有就走dp[i]的路
     *              优先满足高位能够取反，使得结果高位为1
     *
     *  2. dp[i]=1111
     *      对于符号位：希望能异或出0，那么优先走1的路
     *      对于其他位：优先满足高位能够取反，使得结果高位为1
     *
     *  当找到最合适的路径后，结果就是 dp[i] ^ dp[start-1]
     */
    public static class TrieTree {
        private Node head;

        public TrieTree() {
            this.head = new Node();
        }

        public void add(int num){
            Node cur = head;

            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;

                if (cur.next[path] == null){
                    cur.next[path] = new Node();
                }

                cur = cur.next[path];
            }
        }

        public int getMaxEor(int num){
            Node cur = head;
            int res = 0;

            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;

                int best = i == 31 ? path : (1 ^ path);

                best = cur.next[best] == null ? (1 ^ best) : best;

                res |= (best ^ path) << i;

                cur = cur.next[best];
            }

            return res;
        }
    }

    public static int getMaxEOR3(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }

        TrieTree trieTree = new TrieTree();
        trieTree.add(0);
        int xor = 0;
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];

            res = Math.max(res, trieTree.getMaxEor(xor));

            trieTree.add(xor);
        }

        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int eor = 0;
            for (int j = i; j < arr.length; j++) {
                eor ^= arr[j];
                max = Math.max(max, eor);
            }
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3, -28, -29, 2};
        System.out.println(getMaxEOR1(arr)); // 7
        System.out.println(getMaxEOR2(arr));
        System.out.println(getMaxEOR3(arr));

        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int res = getMaxEOR3(arr1);
            int comp = comparator(arr1);
            if (res != comp) {
                succeed = false;
                printArray(arr1);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
