package NowCoder.Exam.Exam4;

public class test3 {

    public static int getMaxEOR1(int[] arr){
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int len = arr.length;
        int xor = 0;

        // 以每个位置结尾
        for (int i = 0; i < arr.length; i++) {
            // 开始位置从0-i，以start开始，以i位置结尾的所有子数组
            for (int start = 0; start < i; start++) {
                int num = 0;
                // 求以start开始，以i位置结尾的当前子数组的xor
                for (int k = start; k <= i; k++) {
                    num |= arr[k];
                }

                if (xor < num){
                    xor = num;
                    len = Math.min(len, i - start);
                }

//                xor = Math.max(xor, num);
//                len = Math.min(len, i - start);
            }
        }

        return len;
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

//                int best = i == 31 ? path : (1 ^ path);
                int best = 0;
                if (i == 31){
                    best = path;
                }




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

    public static void main(String[] args) {

        int[] arr = {1, 2, 3};

        System.out.println(getMaxEOR1(arr));

    }
}
