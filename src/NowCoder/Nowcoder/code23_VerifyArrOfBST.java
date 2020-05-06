package NowCoder.Nowcoder;

/*
    输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
    如果是则输出Yes,否则输出No。
    假设输入的数组的任意两个数字都互不相同。
 */
public class code23_VerifyArrOfBST {
    // 搜索二叉树：任一子树中，左子树都小于头节点，右子树都大于头节点
    // 后序遍历：最后一个是整个树的头节点


    public static boolean method01(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
//        return verify01(arr, 0, arr.length - 1);
        return verify02(arr, 0, arr.length - 1);
    }

    // 从后往前找第一个小于头节点的位置，它往前都是头节点的左子树，往后都是头结点的右子树
    // 以第一个小于头节点的位置划分左右子树
    public static boolean verify01(int[] arr, int l, int r) {
        if (l >= r) {
            return true;
        }
        // 头节点是后序遍历的最后一个
        int head = arr[r];
        // 从后往前找第一个小于头节点的位置
        int leftIndex = r;
        for (int i = r; i >= 0; i--) {
            if (arr[i] < head) {
                leftIndex = i;
                break;
            }
        }
        // TODO 如果leftIndex没变，仍在最后，说明序列是递减序列，一定是true
        if (leftIndex == r) {
            return true;
        }
        // 判断leftIndex左边是不是都小于头节点，右边是不是都大于头节点
        for (int i = 0; i <= leftIndex; i++) {
            if (arr[i] > head) {
                return false;
            }
        }
        for (int i = leftIndex + 1; i < r; i++) {
            if (arr[i] < head) {
                return false;
            }
        }

        // leftIndex往前都是左子树，leftIndex+1 - r-1 都是右子树
        // 递归
        return verify01(arr, l, leftIndex) && verify01(arr, leftIndex + 1, r - 1);
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

    public static boolean helpVerify(int [] sequence, int start, int root){
        if(start >= root)return true;
        int key = sequence[root];
        int i;
        //找到左右子数的分界点
        for(i=start; i < root; i++)
            if(sequence[i] > key)
                break;
        //在右子树中判断是否含有小于root的值，如果有返回false
        for(int j = i; j < root; j++)
            if(sequence[j] < key)
                return false;
        return helpVerify(sequence, start, i-1) && helpVerify(sequence, i, root-1);
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
