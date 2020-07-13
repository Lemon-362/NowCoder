package NowCoder.LeetCode.Tags.Hot100.Tree;

/**
 * 不同的BST搜索二叉树:
 *  给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 */
public class code8_NumOfBST {
    /**
     *  TODO 1...N ==> 动态规划, 依次填表
     *
     * 对于1--N, 可以以每个数作为头节点, 建立出BST
     * 那么, 假设 F(i)表示以i为头节点建立出的BST树的个数
     * 另外, 题目要求1--N为节点组成的BST个数, 则假设 G(N)表示1--N时BST的总数
     *
     * 则有: G(N) = F(1) + F(2) + ... + F(N)
     *
     * 对于F(i)来说, 由于要建立BST树,
     * 所以 1 -- i-1一定是以i为头节点的左孩子
     *     i+1 -- N一定是以i为头节点的右孩子
     * 那么, 可以抽象成 1 -- i-1时BST总数为 G(i-1)
     *                i+1 -- N时BST总数为 G(N-i)
     * 将左右孩子组合后, 以i为头节点的BST个数一共有  G(i-1) * G(N-i)
     *
     * 则有: G(N) = Σ F(i) = Σ G(i-1) * G(N-i), 1<=i<=N
     *
     *
     */
    public static int numOfBST(int N){
        if (N <= 0){
            return 0;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            int num = 0;
            for (int j = 1; j <= i; j++) {
                num += (dp[j - 1] * dp[i - j]);
            }
            dp[i] = num;
        }

        return dp[N];
    }

    public static void main(String[] args) {
        System.out.println(numOfBST(3)); // 5
    }
}
