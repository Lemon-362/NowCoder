package NowCoder.advanced_class.exercise.exercise01;

public class code28 {
    public static int getMaxEOR1(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }

        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int start = 0; start <= i; start++) {
                int xor = 0;
                for (int j = start; j <= i; j++) {
                    xor ^= arr[j];
                }
                res = Math.max(res, xor);
            }
        }

        return res;
    }

    public static int getMaxEOR2(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }

        int res = 0;
        int xor = 0;
        int[] dp = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            res = Math.max(res, xor);

            for (int start = 1; start <= i; start++) {
                int startToI = xor ^ dp[start - 1];
                res = Math.max(res, startToI);
            }

            dp[i] = xor;
        }

        return res;
    }

    public static class Node {
        private Node[] next;

        public Node() {
            this.next = new Node[2];
        }
    }

    public static class TrieTree {
        private Node head;

        public TrieTree() {
            this.head = new Node();
        }

        public void addNum(int num){
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
        trieTree.addNum(0);
        int res = Integer.MIN_VALUE;
        int xor = 0;

        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];

            res = Math.max(res, trieTree.getMaxEor(xor));

            trieTree.addNum(xor);
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
