package NowCoder.Nowcoder;

/*
    输入一个整数数组，判断该数组是不是某搜索二叉树的后序遍历的结果。
    如果是则输出Yes,否则输出No。
    假设输入的数组的任意两个数字都互不相同。
 */
public class code23_VerifyArrOfBST {
    // TODO 搜索二叉树：任一子树中，左子树都小于头节点，右子树都大于头节点
    // TODO 后序遍历：最后一个是整个树的头节点


    // TODO 判断每个子树是否满足 --> 判断以某个节点为头的子数组是否满足
    public static boolean method01(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        return process(arr, 0, arr.length - 1);
    }

    // 判断左子树/右子树都可以, 这里是判断左子树
    public static boolean process(int[] arr, int l, int r) {
        // base case
        if (l >= r) { // TODO 有可能在向右递归时 left+1 > r-1, 会出现 l直接大于r 的情况
            return true;
        }

        // 头节点是后序遍历数组的最后一个
        int head = arr[r];

        // 搜索二叉树满足 头节点>左子树, 头节点<右子树
        // TODO 因为后序遍历是 左右中, 所以必须从后往前找第一个小于head的位置left, left往前都是左子树, left往后都是右子树
        // 而在寻找过程中可以保证 left往后都是右子树, 都大于head
        // 所以只要确保 left往前都小于head
        int left = r;
        for (; left >= 0; left--) {
            if (arr[left] < head) {
                break;
            }
        }

        // 可能性1: left == r, left的位置仍是r没有变, 说明head往前都是小于head的, 因此都在左子树, 满足条件
        if (left == r) {
            return true;
        }

        // 可能性2: left < r, 那么就要求left及其之前都小于head, left之后的可以保证都是大于head的
        for (int i = 0; i < left; i++) {
            if (arr[i] > head) {
                return false;
            }
        }

        // TODO 后续遍历: 0 - left(左) - r-1(右) - r(头)
        // 在左子树和右子树上继续判断子树是否满足
        return process(arr, l, left) && process(arr, left + 1, r - 1);
    }

    // 优化：以第一个大于头节点的位置划分左右子树
    public static boolean verify02(int[] arr, int l, int r) {
        if (l >= r) {
            return true;
        }
        int head = arr[r];
        int rightIndex;
        // TODO 要从L开始，而不是从0开始遍历
        for (rightIndex = l; rightIndex < r; rightIndex++) {
            if (arr[rightIndex] > head) {
                break;
            }
        }
        // rightIndex左边是左子树，右边是右子树
        // 判断是否满足搜索二叉树的条件
        // TODO 左子树不用判断，因为在寻找rightIndex的时候，一定满足小于head
//        for (int i = 0; i < rightIndex; i++) {
//            if (arr[i] > head){
//                return false;
//            }
//        }
        for (int j = rightIndex; j < r; j++) {
            if (arr[j] < head) {
                return false;
            }
        }
        // 递归
        return verify02(arr, l, rightIndex - 1) && verify02(arr, rightIndex, r - 1);
    }

    public static boolean helpVerify(int[] sequence, int start, int root) {
        if (start >= root) return true;
        int key = sequence[root];
        int i;
        //找到左右子数的分界点
        for (i = start; i < root; i++)
            if (sequence[i] > key)
                break;
        //在右子树中判断是否含有小于root的值，如果有返回false
        for (int j = i; j < root; j++)
            if (sequence[j] < key)
                return false;
        return helpVerify(sequence, start, i - 1) && helpVerify(sequence, i, root - 1);
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 4, 3, 2, 1};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {1, 2, 4, 3, 7, 8, 9, 10, 8, 5};

        System.out.println(method01(arr1));
        System.out.println(method01(arr2));
        System.out.println(method01(arr3));
    }
}
