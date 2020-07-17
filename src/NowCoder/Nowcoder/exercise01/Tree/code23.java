package NowCoder.Nowcoder.exercise01.Tree;

public class code23 {
    // TODO 判断每个子树是否满足 --> 判断以某个节点为头的子数组是否满足
    public static boolean VerifyArrIsBSTPosOrder(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        return process(arr, 0, arr.length - 1);
    }

    public static boolean process(int[] arr, int l, int r){
        // base case
        if (l == r){
            return true;
        }

        int head = arr[r];
        int right = l;

        for (int i = l; i <= r; i++) {
            if (arr[i] > head){
                right = i;
                break;
            }
        }

        // 1
        if (right == l){
            return true;
        }

        // 2
        for (int i = right; i < r; i++) {
            if (arr[i] < head){
                return false;
            }
        }

        return process(arr, l, right - 1) && process(arr, right, r - 1);
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 4, 3, 2, 1};
        int[] arr2 = {1, 2, 3, 4, 5};
        int[] arr3 = {1, 2, 4, 3, 7, 8, 9, 10, 8, 5};

        System.out.println(VerifyArrIsBSTPosOrder(arr1)); // true
        System.out.println(VerifyArrIsBSTPosOrder(arr2)); // true
        System.out.println(VerifyArrIsBSTPosOrder(arr3)); // true
    }
}
