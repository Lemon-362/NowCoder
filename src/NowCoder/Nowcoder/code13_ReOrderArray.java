package NowCoder.Nowcoder;

/*
    输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
    使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
    并保证奇数和奇数，偶数和偶数之间的相对位置不变。（稳定）
 */
public class code13_ReOrderArray {
    // 不稳定，荷兰国旗问题
    public static void method01(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        method01(arr, 0, arr.length - 1);
    }

    public static void method01(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur < more) {
            if (arr[cur] % 2 == 1) { // 奇
                swap(arr, ++less, cur++);
            }else {
                swap(arr, --more, cur);
            }
        }
    }

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 稳定：利用冒泡排序的思想，每次交换相邻位置，这样就可以保证前后顺序不变
    public static void method02(int[] arr){
        if (arr == null){
            return;
        }
        boolean flag = false; // 判断遍历数组后有没有交换过，如果没有交换过直接返回
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] % 2 == 0 && arr[j+1] % 2 == 1){
                    // 只有在前一个是偶数，后一个是奇数时交换
                    swap(arr, j, j+1);
                    flag = true;
                }
            }
            // 遍历完一遍数组，看flag
            if (!flag){
                return;
            }
        }
    }

    public static void printArr(int[] arr){
        if (arr == null){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        method01(arr);
        printArr(arr); // 1 9 3 7 5 6 8 4 2
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        method02(arr1);
        printArr(arr1); // 1 3 5 7 9 2 4 6 8
    }
}
