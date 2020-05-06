package NowCoder.Nowcoder;

/*
    在旋转数组中找最小值：
        旋转数组：将一个数组最开始的几个数搬到数组末尾
    输入一个非递减的数组的旋转数组，输出此选转数组的最小值
 */
public class code06_FindMinInRotateMatrix {
    // O(logN)
    public static int method(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        // 二分法
        int i = 0;
        int j = arr.length - 1;
        int mid = 0;
        while (i < j) {
            mid = (i + j) >> 1;
            if (arr[i] < arr[j]) {
                return arr[i];
            }
            if (arr[mid] > arr[j]) {
                // m在左排序
                i = mid + 1;
            } else if (arr[mid] < arr[j]) {
                // m在右排序
                j = mid;
            } else {
                // arr[mid] == arr[j]
                j = j - 1;
            }
        }
        return arr[i];
    }

    // O(N)：顺序遍历，找到后一个比前一个小的时候
    public static int method02(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return arr[i + 1];
            }
        }
        return arr[0];
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 0, 1, 1, 1, 1, 1, 2, 3, 4};
        int[] arr2 = {3, 4, 5, 1, 2, 3};
        int[] arr3 = {1, 0, 1, 1, 1};

        System.out.println(method02(arr1)); // 0
        System.out.println(method02(arr2)); // 1
        System.out.println(method02(arr3)); // 0

        System.out.println(method(arr1));
        System.out.println(method(arr2));
        System.out.println(method(arr3));
    }
}
