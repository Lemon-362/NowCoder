package NowCoder.advanced_class.class_7;

/*
    子数组的最大异或和：
        给定一个数组，求子数组的最大异或和为多少？
 */
public class Code_01_Max_EOR {

    // 暴力法
    public static int getMaxE1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) { // 以i结尾的
            for (int start = 0; start <= i; start++) { // 0 - i， 1 - i，。。。
                // 求 start - i 的异或和，start从0到i
                int res = 0;
                for (int k = start; k <= i; k++) { // 再遍历每个 start - i，得到异或和
                    res ^= arr[k];
                }
                max = Math.max(max, res);
            }
        }
        return max;
    }

    // 优化：用辅助数组存储到达每个位置的异或和
    public static int getMaxE2(int[] arr) {
        int max = Integer.MIN_VALUE;
        int[] dp = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) { // 以i位置结尾
            // 0-i的xor
            eor ^= arr[i];
            // TODO 这里是针对i=0时,单独比较
            // 用于求0-0位置的xor
            // 因为如果start从0开始,start-1=-1,dp中没有
            max = Math.max(max, eor);
            // 从1位置开始:1-i, 2-i, ..., i-i
            // 已知0-i的xor,dp[i-1]以及之前所有位置结尾的xor
            // 要求start-i的xor,那么就等于 0-i的xor ^ 0-start-1的xor

            // 求1-i,2-i,...,i-i中最大的xor
            for (int start = 1; start <= i; start++) { // start - i的异或和就是 (0 - i) ^ (0 - start-1)
                // start-i的xor
                int startToIEor = eor ^ dp[start - 1];
                max = Math.max(max, startToIEor);
            }

            dp[i] = eor; // dp[i]表示的是以每个位置结尾的 0-i 的异或和
        }

        return max;
    }

    // 前缀树
    public static class Node { // 这里我们只需要记录路径的值,节点的path到达和end结尾不需要记录
        public Node[] nexts = new Node[2]; // 两条路 0 1, 因为都转换成了二进制数,路径上只可能时0或1
    }

    public static class NumTrie {
        private Node head;

        public NumTrie() {
            this.head = new Node(); // 前缀树初始化一定有一个头
        }

        public void add(int num) {
            Node cur = head; // 每次加东西都从头开始
            // 往前缀树里加二进制形式的eor
            for (int move = 31; move >= 0; move--) { // 整数变为二进制数是32位，所以只需遍历32次就可以完成一次添加
                // 提取每一位的数字，path只能为0或1
                int path = ((num >> move) & 1); // num右移move位，再和1与操作
                // 例如：对于符号位第32位，符号位右移31位，再和1与操作，符号位如果是1，得到1，如果是0，得到0

                // 如果没有走向path的路径，则新建节点，并往下走
                // 如果有，则直接往下走
                if (cur.nexts[path] == null){
                    cur.nexts[path] = new Node();
                }
//                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path]; // 沿path往下走
            }
        }

        // 给一个0-i的异或和结果，从黑盒中找出与它异或最大的值
        public int maxXor(int num) {
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                // TODO 依次提取每一位数字
                int path = (num >> move) & 1;
                // 期待的选出的最优路径: (结果)符号位为1,其他从高到低尽量为1
                // 对于符号位,原来是什么就选那个,因为相同异或为0。例如：如果0-i上的符号位是1，希望变为0，所以选择的路应该为1。
                // 而对于其他位，选择取反的数,相同为0,不同为1
                int best = move == 31 ? path : (1 ^ path);
                // 因为最佳情况是 path ^ best = 1 --> 所以best = 1 ^ path

                // 现在已经有希望走的路，要看前缀树中有没有这条路，如果有直接走，如果没有只能走0-i位置上的数的路
                // 因为path只可能是0或1,所以如果没有假设的best,那就一定是取反的路
                best = cur.nexts[best] != null ? best : (1 ^ best); // 实际选出的路径

                // TODO 依次设置好得到的结果的每一位的值
                res |= (path ^ best) << move;
                // path：给出的0-i上的数，和选出的路径 与操作，然后再左移多少位，放到对应的位置上
                // 然后再和之前得到的结果res 或操作
                // 因为是从高到低处理的，低位为0，因此或操作后仍然是(path ^ best) << move的结果
                // 并且高位和0或操作仍然是自己，不会被改变
                // 例如：res = 0110000，已经处理完前3位，现在得到第4位应该是1，左移得到0001000
                // 两者或操作，得到0111000，之前的011不变，第4位变成对应的数

                // 节点往低位走继续处理
                cur = cur.nexts[best];
            }

            return res;
        }
    }

    public static int maxXorSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int eor = 0;
        // 黑盒：已知 0-0，0-1，。。。，0-i-1的异或和结果
        NumTrie numTrie = new NumTrie();
        // 一定要有一个头节点
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            // 求出0-i的异或和
            eor ^= arr[i];
            // 在0-i中找与0-i异或最大的值,这个值就是end-i子数组最大xor
            max = Math.max(max, numTrie.maxXor(eor));
            // numTrie.maxXor(eor)：给一个0-i的结果，从黑盒中找一个与0-i进行异或的结果最大的范围
            // 那个范围的异或和就是结果

            // 往黑盒中加入0-i的结果，下一轮求0-i+1的异或和
            numTrie.add(eor);
        }
        return max;
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

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = maxXorSubArray(arr);
//            int res = getMaxE2(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
