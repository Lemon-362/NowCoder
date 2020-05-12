package NowCoder.Nowcoder.exercise;

public class code23 {
    // TODO 判断每个子树是否满足 --> 判断以某个节点为头的子数组是否满足
    public static boolean VerifyArrOfBST(int[] arr){
        if (arr == null || arr.length == 0){
            return false;
        }

        return process(arr, 0, arr.length - 1);
    }

    // 判断左子树/右子树都可以, 这里是判断左子树
    public static boolean process(int[] arr, int l, int r){
        // base case
        if (l >= r){ // TODO 有可能在向右递归时 left+1 > r-1, 会出现 l直接大于r 的情况
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
            if (arr[left] < head){
                break;
            }
        }

        // 可能性1: left == r, left的位置仍是r没有变, 说明head往前都是小于head的, 因此都在左子树, 满足条件
        if (left == r){
            return true;
        }

        // 可能性2: left < r, 那么就要求left及其之前都小于head, left之后的可以保证都是大于head的
        for (int i = 0; i < left; i++) {
            if (arr[i] > head){
                return false;
            }
        }

        // TODO 后续遍历: 0 - left(左) - r-1(右) - r(头)
        // 在左子树和右子树上继续判断子树是否满足
        return process(arr, l, left) && process(arr, left + 1, r - 1);
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 4, 3, 2, 1};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {1, 2, 4, 3, 7, 8, 9, 10, 8, 5};

        System.out.println(VerifyArrOfBST(arr1));
        System.out.println(VerifyArrOfBST(arr2));
        System.out.println(VerifyArrOfBST(arr3));
    }
}
